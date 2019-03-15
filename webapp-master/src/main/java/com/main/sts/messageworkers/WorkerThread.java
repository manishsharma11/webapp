package com.main.sts.messageworkers;

import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;

public class WorkerThread implements Runnable {

    /*
     * @Autowired private HttpPostSmsSender httpPostSmsSender;
     */
    public static Logger logger = Logger.getLogger(WorkerThread.class);
    BlockingQueue<Object> messageQueue;

    public WorkerThread(BlockingQueue<Object> messageQueue) {
        this.messageQueue = messageQueue;
    }

    public void run() {
        // logger.info("worker "+this.dashboardSettings);
        while (MessagesThreadPool.running) {
            try {
                Object object = messageQueue.take();
                if (object instanceof Mail) {
                    Mail mail = (Mail) object;
                    MailSender.sendMail(mail);
                }
                /*
                 * if (object instanceof Sms) { Sms sms = (Sms) object;
                 * //SmsSender.sendSms(sms); //HttpPostSmsSender
                 * httpPostSmsSender=new HttpPostSmsSender();
                 * HttpPostSmsSender.sendSms(sms); }
                 */
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
