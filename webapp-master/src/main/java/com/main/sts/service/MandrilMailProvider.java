package com.main.sts.service;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.main.sts.dto.EmailDTO;
import com.main.sts.dto.EmailResponse;
import com.microtripit.mandrillapp.lutung.MandrillApi;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage.Recipient;
import com.microtripit.mandrillapp.lutung.view.MandrillMessageStatus;
import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;

@Service("mandrillEmailService")
public class MandrilMailProvider implements MailProvider {

    private MandrillApi mandrillApi;


    public MandrilMailProvider() {
        init();
    }

    public void init() {
         mandrillApi = new MandrillApi("ryhCFfJshMeKThOO2Ktckg");
    }

    // TODO: work in progress
    public EmailResponse sendEmail(EmailDTO emailObj) {
        MandrillMessage message = new MandrillMessage();

        message.setAutoText(true);
        message.setFromName("EasyCommuteApp");
        message.setFromEmail("announcement@easycommute.co");
        //message.setReplyTo("care@easycommute.co");
        
        message.setSubject(emailObj.getSubject());
        //message.setHtml("<h1>Hi pal!</h1><br />Really, I'm just saying hi!");
        
        if (emailObj.getText() != null) {
            message.setText(emailObj.getText());
        }
        
        if (emailObj.getHtml() != null) {
            message.setHtml(emailObj.getHtml());
        }
        
        // add recipients
        ArrayList<Recipient> recipients = new ArrayList<Recipient>();
        for (String to : emailObj.getTos()) {
            Recipient recipient = new Recipient();
            recipient.setEmail(to);
            //recipient.setName("Rahul");
            recipients.add(recipient);
        }
        message.setTo(recipients);
        message.setPreserveRecipients(true);

        EmailResponse emailResponse = null;
        try {
            MandrillMessageStatus[] messageStatusReports = mandrillApi.messages().send(message, false);
            for (MandrillMessageStatus status : messageStatusReports) {
                System.out.println(status.getId()+"--"+status.getEmail()+"--"+status.getStatus()+"--"+status.getRejectReason());
            }
            MandrillMessageStatus status = messageStatusReports[0];
            emailResponse = new EmailResponse(200, status.getId()+"--"+status.getEmail()+"--"+status.getStatus()+"--"+status.getRejectReason());
        } catch (MandrillApiError e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return emailResponse;
    }
    
    public static void main(String[] args) {
        MandrilMailProvider mailProvider = new MandrilMailProvider();
        EmailDTO emailObj = new EmailDTO();
        emailObj.setSubject("Hellow test");
        emailObj.setHtml("<h1>Hi pal!</h1><br />Really, I'm just saying hi!");
        emailObj.setTo(new String[]{"dynamicrahul2020@gmail.com"});

        EmailResponse resp = mailProvider.sendEmail(emailObj);
        System.out.println(resp);
    }
    
    
    public static void main1(String[] args) {

        MandrilMailProvider mailProvider = new MandrilMailProvider();
        
        // create your message
        MandrillMessage message = new MandrillMessage();
        message.setSubject("Hello World!");
        message.setHtml("<h1>Hi pal!</h1><br />Really, I'm just saying hi!");
        message.setAutoText(true);
        message.setFromEmail("support@easycommute.com");
        message.setFromName("EasyCommute");
        // add recipients
        ArrayList<Recipient> recipients = new ArrayList<Recipient>();
        Recipient recipient = new Recipient();
        recipient.setEmail("dynamicrahul2020@gmail.com");
        recipient.setName("Rahul");
        recipients.add(recipient);
//        recipient = new Recipient();
//        recipient.setEmail("terrybull@yetanotherdomain.com");
//        recipients.add(recipient);
        message.setTo(recipients);
        message.setPreserveRecipients(true);
        ArrayList<String> tags = new ArrayList<String>();
        tags.add("test");
        tags.add("helloworld");
        message.setTags(tags);
        // ... add more message details if you want to!
        // then ... send
        try {
            MandrillMessageStatus[] messageStatusReports = mailProvider.mandrillApi.messages().send(message, false);
            System.out.println(messageStatusReports);
        } catch (MandrillApiError e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
}
