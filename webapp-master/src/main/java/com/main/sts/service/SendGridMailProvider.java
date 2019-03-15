package com.main.sts.service;

import org.springframework.stereotype.Service;

import com.main.sts.dto.EmailDTO;
import com.main.sts.dto.EmailResponse;
import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;

@Service("sendGridEmailService")
public class SendGridMailProvider implements MailProvider {

    private SendGrid sendgrid;

    public SendGridMailProvider() {
        init();
    }

    public void init() {
        //sendgrid = new SendGrid("rahul_jain2002", "rj9414284054");
        sendgrid = new SendGrid("rahul_easycommute", "rj9414284054");
    }

    // TODO: work in progress
    public EmailResponse sendEmail(EmailDTO emailObj) {
        SendGrid.Email email = new SendGrid.Email();
        email.addTo(emailObj.getTos());
        email.setFromName("EasyCommuteApp");
        email.setFrom("support@easycommute.co");
        email.setSubject(emailObj.getSubject());
        email.setReplyTo("care@easycommute.co");

        if (emailObj.getText() != null) {
            email.setText(emailObj.getText());
        }
        
        if (emailObj.getHtml() != null) {
            email.setHtml(emailObj.getHtml());
        }

        EmailResponse emailResponse = null;
        try {
            SendGrid.Response response = sendgrid.send(email);
            System.out.println(response.getMessage());

            emailResponse = new EmailResponse(response.getCode(), response.getMessage());
        } catch (SendGridException e) {
            System.err.println(e);
        }
        return emailResponse;
    }
    
    public static void main(String[] args) {
        SendGrid sendgrid = new SendGrid("rahul_jain2002", "rj9414284054");

        SendGrid.Email email = new SendGrid.Email();
        email.addTo("dynamicrahul2020@gmail.com");
        email.setFromName("EasyCommuteApp");
        email.setFrom("support@easycommute.co");
        email.setSubject("Hello World");
        email.setText("My first email with SendGrid Java!");

        try {
            SendGrid.Response response = sendgrid.send(email);
            System.out.println(response.getMessage());
        } catch (SendGridException e) {
            System.err.println(e);
        }
    }
}
