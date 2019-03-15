package com.main.sts;

import javax.annotation.Resource;

import org.junit.Test;

import com.main.sts.messageworkers.Mail;
import com.main.sts.messageworkers.MailSender;

public class MailSenderTest extends BaseTest {

    @Resource
    MailSender mailSender;

    @Test
    public void testSendEmail() {
        Mail email = new Mail("rahul@easycommute.co", "test message", "Hello test message");
        mailSender.sendMail(email);
    }
}
