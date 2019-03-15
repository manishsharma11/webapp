package com.main.sts.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.android.gcm.server.Result;
import com.main.sts.dao.sql.NotificationDao;
import com.main.sts.entities.Commuter;
import com.main.sts.entities.Notification;
import com.main.sts.entities.PushMessage;

@Service("notificationService")
public class NotificationService {

    @Autowired
    private CommuterService commuterService;
    
    @Autowired
    private NotificationDao notificationDao;

    @Autowired
    private GoogleGCMService googleGCMService;

    public List<Notification> findAll() {
        return notificationDao.findAll();
    }

    public Notification getNoticationById(int notification_id) {
        String query = "from Notification b where b.id=?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, notification_id);
        return notificationDao.getSingleRecord(Notification.class, query, parameters, 1);
    }

    public List<Notification> getNoticationByCommuterId(int commuter_id) {
        String query = "from Notification b where b.commuter_id=? order by created_at desc";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, commuter_id);
        return notificationDao.getRecords(Notification.class, query, parameters, 20);
    }

    public boolean insertFaq(Notification faq) {
        return notificationDao.insertANotication(faq);
    }

    public boolean publishNotificationToAll(String title, String message) {
        int offset = 0;
        int limit = 20;
        // send until we exhaust all the commuters list in a batch of getting details for 20 persons (limit)
        while (true) {
            List<Commuter> commuters = commuterService.findAllActiveAndVerified(offset, limit);
            if (commuters != null) {
                for (Commuter commuter : commuters) {
                    try {
                        publishNotification(commuter.getCommuter_id(), commuter.getGcm_reg_id(), title, message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                break;
            }
            offset = offset + 10;
        }
        return true;
    }

    public Notification publishNotification(int commuter_id, String title, String message) {
        System.out.println("Notification send111------------------->>>");

        Commuter commuter = commuterService.getCommuterById(commuter_id);
        System.out.println("Notification send222------------------->>>");

        return publishNotification(commuter_id, commuter.getGcm_reg_id(), title, message);
    }

    public Notification publishNotification(int commuter_id, PushMessage pushMessage) {
        Notification notification = new Notification();
        notification.setTitle(pushMessage.getTitle());
        notification.setMessage(pushMessage.getMessage());
        notification.setCommuter_id(commuter_id);
        notification.setCreated_at(new Timestamp(new Date().getTime()));

//        PushMessage pushMessage = new PushMessage();
//        pushMessage.setGcm_regid(gcm_reg_id);
//        pushMessage.setTitle(title);
//        pushMessage.setMessage(message);

        System.out.println("Notification send333------------------->>>");

        Result result = googleGCMService.sendPushNotification(pushMessage);
        notification.setMessage_id(result.getMessageId());

        boolean status = notificationDao.insertANotication(notification);
        if (status) {
            return notification;
        } else {
            return null;
        }
    }
    
    private Notification publishNotification(int commuter_id, String gcm_reg_id, String title, String message) {
        Notification notification = new Notification();
        notification.setTitle(title);
        notification.setMessage(message);
        notification.setCommuter_id(commuter_id);
        notification.setCreated_at(new Timestamp(new Date().getTime()));

        PushMessage pushMessage = new PushMessage();
        pushMessage.setGcm_regid(gcm_reg_id);
        pushMessage.setTitle(title);
        pushMessage.setMessage(message);

        System.out.println("Notification send333------------------->>>");

        Result result = googleGCMService.sendPushNotification(pushMessage);
        notification.setMessage_id(result.getMessageId());

        boolean status = notificationDao.insertANotication(notification);
        if (status) {
            return notification;
        } else {
            return null;
        }
    }

    public boolean updateANotication(int notification_id, String question, String answer) {
        Notification notification = getNoticationById(notification_id);
        // notification.setReferral_code_status(notification_status);
        return notificationDao.updateANotication(notification);
    }

    public boolean deleteNotication(int notification_id) {
        Notification notification = getNoticationById(notification_id);
        return notificationDao.deleteNotication(notification);
    }

    public static void main(String[] args) {
        System.out.println(Long.toHexString(System.currentTimeMillis()));
    }
}
