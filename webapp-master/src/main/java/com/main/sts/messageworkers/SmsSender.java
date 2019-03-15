package com.main.sts.messageworkers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class SmsSender {

    public static Logger log = Logger.getLogger(SmsSender.class);

    String authkey = "";// "1700AMGRNUICF52eb9179";
    String senderId = "";// "slabss";
    String encoded_message = null;
    String route = "default";

    String base_url = "http://login.bulksmsglobal.in/sendhttp.php?";

    public void sendSms(Sms sms) {
        try {
            log.info("Sending sms to [ " + sms.getSendTo() + " ]  Message: [ " + sms.getMessage() + " ]");
            String number = sms.getSendTo();

            // Prepare Url
            URLConnection urlConn = null;
            URL url = null;
            BufferedReader buffreader = null;
            log.info(sms);
            if (sms.getMessage() != null) {
                // encoding message
                encoded_message = URLEncoder.encode(sms.getMessage());

                String finalURL = buildURL(number);
                // prepare connection
                url = new URL(finalURL);
                urlConn = url.openConnection();
                urlConn.connect();
                buffreader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                log.info("Sms sent...");
            } else {
                log.info("Sms failed to  sent");
            }
        } catch (Exception e) {
            log.info("Failed to send sms");
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public String buildURL(String number) {
        // Prepare parameter string
        StringBuilder sbPostData = new StringBuilder(base_url);
        sbPostData.append("authkey=" + authkey);
        sbPostData.append("&mobiles=" + number);
        sbPostData.append("&message=" + encoded_message);
        sbPostData.append("&route=" + route);
        sbPostData.append("&sender=" + senderId);

        // final string
        String finalURL = sbPostData.toString();
        return finalURL;
    }
}
