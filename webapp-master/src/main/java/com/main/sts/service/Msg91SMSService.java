package com.main.sts.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.springframework.stereotype.Service;

@Service("msg91Service")
public class Msg91SMSService implements SMSProvider {

    // Your authentication key
    String authKey = "94972Ang4DFQa561b76a9";
    // Multiple mobiles numbers separated by comma
    // String mobile = "9908599937";
    // Sender ID,While using route4 sender id should be 6 characters long.
    String senderId = "ESYCMT";// "ESYCMT";
    // Your message to send, Add URL encoding here.
    // String message =
    // "Hello, thanks for signing up!, your referral code is ESY20X";
    // define route
    String defaultRoute = "4";

    public boolean sendSMS(SMSInfo sms) {
        String route = null;;
        if (sms.getRoute() == null) {
            route = defaultRoute;
        } else {
            route = sms.getRoute();
        }
        if (sms.isSendEnabled()) {
            return sendSMSToMsg91(authKey, sms.getMobile(), senderId, sms.getMessage(), route);
        } else {
            System.out.println("Not sending message, as configured for testing");
            return false;
        }
    }

    private boolean sendSMSToMsg91(String authKey, String mobile, String senderId, String message, String route) {
        // Prepare Url
        URLConnection myURLConnection = null;
        URL myURL = null;
        BufferedReader reader = null;

        // encoding message
        String encoded_message = URLEncoder.encode(message);

        // Send SMS API
        String mainUrl = "https://control.msg91.com/sendhttp.php?";

        // Prepare parameter string
        StringBuilder sbPostData = new StringBuilder(mainUrl);
        sbPostData.append("authkey=" + authKey);
        sbPostData.append("&mobiles=" + mobile);
        sbPostData.append("&message=" + encoded_message);
        sbPostData.append("&route=" + route);
        sbPostData.append("&sender=" + senderId);

        System.out.println("Sending SMS message to:" + mobile + " on route:" + route);
        // final string
        mainUrl = sbPostData.toString();
        try {
            // prepare connection
            myURL = new URL(mainUrl);
            myURLConnection = myURL.openConnection();
            myURLConnection.connect();
            reader = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
            // reading response
            String response;
            while ((response = reader.readLine()) != null) {
                // print response
                System.out.println(response);
                if (response != null) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // finally close connection
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // Your authentication key
        String authKey = "94972Ang4DFQa561b76a9";
        // Multiple mobiles numbers separated by comma
        String mobile = "9908599937";
        mobile = "8099927902";
        // Sender ID,While using route4 sender id should be 6 characters long.
        String senderId = "ESYCMT";// "ESYCMT";
        // Your message to send, Add URL encoding here.
        String message = "Hello, thanks for signing up!, your referral code is ESY20X";
        message = "Introducing EasyCommute,a Mobile based Bus Shuttle Service,for working professionals starting soon in Hyderabad. Signup at http://bit.ly/EC & get 2 free rides";
        String route = "4";// define route

        Msg91SMSService smsService = new Msg91SMSService();
        smsService.sendSMSToMsg91(authKey, mobile, senderId, message, route);
    }

}
