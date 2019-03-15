package com.main.sts.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

public class MarketingSMSService {

    public static void main1(String[] args) {
        // String mobile = "8099927902";
        // Sender ID,While using route4 sender id should be 6 characters long.
        String senderId = "ESYCMT";// "ESYCMT";
        // Your message to send, Add URL encoding here.
        String message = "Hello, thanks for signing up!, your referral code is ESY20X";
        message = "EasyCommute an AC Bus Shuttle Service with ConfirmedSeat,Free Wifi(4G),GPS Tracking start from 15Rs. Download app now at http://bit.ly/ECMApp & get 2 free rides";

        String route = "4";// define route

        Msg91SMSService smsService = new Msg91SMSService();

        List<String> ls = null;
        List<Long> numbers = new ArrayList<Long>();
        try {
            numbers.add(9908599937L);
//            ls = FileUtils.readLines(new File("src/main/resources/earlysignup.txt"));
            ls = FileUtils.readLines(new File("src/main/resources/rahgiridaysignup.txt"));
            System.out.println(ls);
            for (String s : ls) {
                // System.out.println(s);
                if (s.length() > 10) {
                    System.out.println("No is gt than 10 characters");
                }
                long l = Long.parseLong(s);
                System.out.println(l);
                numbers.add(l);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (long mobileNumber : numbers) {
            System.out.println(mobileNumber);
             SMSInfo smsInfo = new SMSInfo();
             smsInfo.message = message;
             smsInfo.mobile = "" + mobileNumber;
             smsInfo.sendEnabled = true;
             smsService.sendSMS(smsInfo);
            try {
                Thread.sleep(30000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        Msg91SMSService smsService = new Msg91SMSService();

        long mobileNumber = 9652802818L;
        //String message = "Hi Naresh, we have credited 1 free ride in your account due to referral of Radha Krishna. Happy EasyCommuting! EasyCommute team";
        //String message = "Hi Radha Krishna, we have credited 1 free ride in your account due to your referral of Naresh. Happy EasyCommuting! EasyCommute team";
        String message = "Dear Sudheer, We have added 1 complementary free ride in your account. We sincerely regret for the inconvenience caused to you. EasyCommute team";

        SMSInfo smsInfo = new SMSInfo();
        smsInfo.message = message;
        smsInfo.mobile = "" + mobileNumber;
        smsInfo.sendEnabled = true;
        smsService.sendSMS(smsInfo);
    }

}
