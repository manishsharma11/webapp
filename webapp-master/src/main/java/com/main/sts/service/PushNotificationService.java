package com.main.sts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.android.gcm.server.Result;
import com.main.sts.entities.Commuter;
import com.main.sts.entities.PushMessage;

@Service("pushNotificationService")
public class PushNotificationService {

    @Autowired
    private CommuterService commuterService;

    @Autowired
    private GoogleGCMService googleGCMService;

    public boolean sendPushNotification(int commuter_id, String title, String message) {
        Commuter commuter = commuterService.getCommuterById(commuter_id);
        if (commuter != null) {
            String gcm_regId = commuter.getGcm_reg_id();
            return sendPushNotification(gcm_regId, title, message);
        } else {
            System.out.println("Couldnt fetch commuter information");
        }
        return false;
    }

    public boolean sendPushNotification(String gcm_regId, String title, String message) {
        PushMessage pushMsg = new PushMessage();
        pushMsg.setGcm_regid(gcm_regId);
        pushMsg.setTitle(title);
        pushMsg.setMessage(message);
        Result result = googleGCMService.sendPushNotification(pushMsg);
        if (result != null) {
            System.out.println("Sent push Notification with message id:" + result.getMessageId());
            return true;
        } else {
            return false;
        }
    }

    public boolean sendPushNotificationToAllUsers(String title, String message) {
        boolean status = true;
        List<Commuter> commuters = commuterService.findAll();
        if (commuters != null && commuters.size() > 0) {
            for (Commuter commuter : commuters) {
                status = status && sendPushNotification(commuter.getGcm_reg_id(), title, message);
            }
        }
        return status;
    }

}
