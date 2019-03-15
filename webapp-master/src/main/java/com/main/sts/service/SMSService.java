package com.main.sts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.sts.entities.Commuter;

@Service("smsService")
public class SMSService {

    @Autowired
    private CommuterService commuterService;

    @Autowired
    private Msg91SMSService smsServiceProvider;

    public boolean sendSMS(final String[] ids, final String message) {
//        String[] commuterIds = ids;
//        if (ids != null && ids.length == 1) {
//            String id = ids[0];
//            if (Integer.parseInt(id) == Integer.MIN_VALUE) {
//                ids = commuterService.getActiveAndVerifiedCommuterIds();
//                commuterIds = ids;
//            }
//        }
        
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                for (String id : ids) {
                    Commuter c = commuterService.getCommuterById(Integer.parseInt(id));
                    long number = Long.parseLong(c.getMobile());
                    if (number == 9866694931L || number == 8123829624L || number == 9441501647L
                            || number == 9347347329L || number == 9000990610L) {
                        // ignore this stupid fellow.
                        continue;
                    }
                    if (c.getStatus() == 0) {
                        continue;
                    }
                    System.out.println(number);

                    boolean sent = sendSMS(number, message);
                    System.out.println("sent to:" + number + " with status:" + sent);
                    try {
                        Thread.sleep(6000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Sent to all users: exiting");
            }
        });
        t.start();
        System.out.println("Request for sending SMS submitted to number of people:" + ids.length);
        return true;
    }

    public boolean sendSMS(long number, String message) {
        SMSInfo smsInfo = new SMSInfo();
        smsInfo.message = message;
        smsInfo.mobile = "" + number;
        smsInfo.sendEnabled = true;
        return smsServiceProvider.sendSMS(smsInfo);
    }

}
