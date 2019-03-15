package com.main.sts.messageworkers;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.main.sts.entities.DashboardSettings;
import com.main.sts.util.MD5PassEncryptionClass;

@Repository
public class MailSender {

    public static Logger logger = Logger.getLogger(MailSender.class);
    
    private MD5PassEncryptionClass passwordEncrypt = new MD5PassEncryptionClass();
    
    Properties props = null;
    
    private static Session session = null;
    
    public static DashboardSettings dashboardSettings;

    public void setAuth() {
        try {
            props = new Properties();
            // logger.info(dashboardSettings);
            String serverHost = dashboardSettings.getSmtp_server_host();
            if (serverHost == null) {
                System.out.println("ERROR: serverHost is null, Can't send emails");
                return;
            }
            props.put("mail.smtp.auth", "true");
            //props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", serverHost);// "smtp.gmail.com"
            props.put("mail.smtp.port", dashboardSettings.getSmtp_port());// "587"
            
//            props.put("mail.smtp.host", serverHost);
//            props.put("mail.smtp.socketFactory.port", dashboardSettings.getSmtp_port());
//            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//            props.put("mail.smtp.auth", "true");
//            props.put("mail.smtp.port", dashboardSettings.getSmtp_port());
//            session = Session.getInstance(props, new javax.mail.Authenticator() {
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication(dashboardSettings.getFrom_email(), passwordEncrypt
//                            .DecryptText(dashboardSettings.getSmtp_password()));
//                }
//            });
            session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(dashboardSettings.getFrom_email(), passwordEncrypt
                            .DecryptText(dashboardSettings.getSmtp_password()));
                }
            });
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void sendMail(Mail mail) {
        try {
            logger.info("Sending mail to [ " + mail.getSendTo() + " ]  Message: [ " + mail.getMessage() + " ]");
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(dashboardSettings.getFrom_email()));

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail.getSendTo()));
            message.setSubject(mail.getSubject());
            message.setContent(mail.getMessage(), "text/html");
            Transport.send(message);
            // Thread.sleep(1000);
            logger.info("Mail sent");
        } catch (Exception e) {
            logger.info("Failed to send mail");
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    // send mail with attachmnt by sami

    public void sendMailThreadWithAttach(final String toAddress, final String subject, final String sendMesg,
            final Session session, final DashboardSettings adminPreferences, final File file, final File csvfile) {

        // System.out.println(adminPreferences);
        this.session = session;
        // this.toAddress1 = toAddress;
        // this.subject1 = subject;
        // this.sendMesg1 = sendMesg;
        this.dashboardSettings = adminPreferences;

        // try {

        // MyThreadPoolExecutor emailThreadPoolExecutor = new
        // MyThreadPoolExecutor();
        // emailThreadPoolExecutor.runTask(new Runnable() {
        // public void run() {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(dashboardSettings.getFrom_email()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
            message.setSubject(subject);

            Message msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress(dashboardSettings.getFrom_email()));
            InternetAddress[] toAddresses = {new InternetAddress(toAddress)};
            msg.setRecipients(Message.RecipientType.TO, toAddresses);
            msg.setSubject(subject);
            msg.setSentDate(new Date());

            // creates message part
            // MimeBodyPart messageBodyPart = new MimeBodyPart();

            // creates multi-part
            Multipart multipart = new MimeMultipart();
            BodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent("<html><b>" + sendMesg + "</b></html>", "text/html");
            // adds attachments
            MimeBodyPart attachPart = new MimeBodyPart();

            attachPart.attachFile(file.getAbsoluteFile());

            multipart.addBodyPart(attachPart);
            multipart.addBodyPart(htmlPart);

            // csv attachment by sami only below code added

            MimeBodyPart csvattach = new MimeBodyPart();
            csvattach.attachFile(csvfile.getAbsoluteFile());
            multipart.addBodyPart(csvattach);

            // ended
            // sets the multi-part as e-mail's content
            msg.setContent(multipart);

            // sends the e-mail
            Transport.send(msg);

            csvfile.delete();
            file.delete();
        } catch (Exception ae) {
            // TODO Auto-generated catch block
            ae.printStackTrace();
        }
        logger.info("sent mail to [ " + toAddress + " ] with report as an attachment [ " + subject + " ]");
    }
    // });

    // end

    public void sendMailWithAttachment(String toAddress, String subject, String sendMesg, File file, File csvfile) {
        // System.out.println(adminPreferences);
        logger.info(MailSender.class + "sendMail with attachment method");
        try {
            // System.out.println("passowrd "+adminPreferences.getPassword());
            // System.out.println("after dec "+passwordEncrypt.DecryptText(adminPreferences.getPassword()));
            // MyThreadPoolExecutor emailThreadPoolExecutor=new
            // MyThreadPoolExecutor();
            sendMailThreadWithAttach(toAddress, subject, sendMesg, session, dashboardSettings, file, csvfile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
