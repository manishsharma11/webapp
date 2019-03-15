package com.main.sts.util;

import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;

public class SendGridExample {
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
    }
    catch (SendGridException e) {
      System.err.println(e);
    }
  }
}