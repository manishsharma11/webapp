package com.main.sts.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.google.gson.Gson;
import com.main.sts.entities.PushMessage;

@Service("googleGCMService")
public class GoogleGCMService {

    // Google server api key
                            // "AIzaSyAhQYLryFsjFwY-w6q7Q1WeJvYGBf_DnmM";
    //String GOOGLE_SERVER_KEY = "AIzaSyAhQYLryFsjFwY-w6q7Q1WeJvYGBf_DnmM";
    String GOOGLE_SERVER_KEY ="AIzaSyD9hGyL2OZ9hdLdtwnznI4wsOzS3Fxo-YQ";
    static final String MESSAGE_KEY = "message";

    public Result sendPushNotification(PushMessage pushMessage) {
        String regId = pushMessage.getGcm_regid();
        //String userMessage = pushMessage.getMessage();
        String jsonMessage = buildPushNotificationJSON(pushMessage);
        System.out.println("jsonMessage:"+jsonMessage);
        Sender sender = new Sender(GOOGLE_SERVER_KEY);//SENDER_ID
        Message message = new Message.Builder().timeToLive(3000).delayWhileIdle(true).addData(MESSAGE_KEY, jsonMessage)
                .build();
        System.out.println("regId: " + regId);
        Result result;
        try {
            result = sender.send(message, regId, 1);
            System.out.println("Push message: status"+result);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    //private Gson gson = new Gson();
    
    private String buildPushNotificationJSON(PushMessage pushMessage) {
        String title = pushMessage.getTitle();
        String userMessage = pushMessage.getMessage();
        if (title == null) {
            throw new IllegalArgumentException("Title can't be empty");
        }

        if (userMessage == null) {
            throw new IllegalArgumentException("Message can't be empty");
        }

        String json_format = "{\"title\":\"%s\", \"message\":\"%s\"}";
        String json = String.format(json_format, title, userMessage);
        System.out.println(json);

        return json;
    }

    public static void main(String[] args) {
        // Your message to send, Add URL encoding here.
        String message = "Hello, thanks for signing up!, your referral code is ESY20X";

        String gcm_regid = "";
        GoogleGCMService gcmService = new GoogleGCMService();
        PushMessage pushMsg = new PushMessage();
        pushMsg.setGcm_regid(gcm_regid);
        pushMsg.setTitle("Test");
        pushMsg.setMessage(message);
        Result result = gcmService.sendPushNotification(pushMsg);
        System.out.println(result.getMessageId());
    }

}
