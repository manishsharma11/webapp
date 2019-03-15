package com.main.sts.messageworkers;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.main.sts.dao.sql.BaseDao;
import com.main.sts.entities.DashboardSettings;
import com.main.sts.service.DashBoardSettingsService;
//import com.main.sts.util.SystemProperties;

@Component
@Scope("singleton")
public class MessagesThreadPool implements InitializingBean {

    public static Logger logger = Logger.getLogger(MessagesThreadPool.class);

    public static volatile boolean running = false;

    BlockingQueue<Object> messageQueue = null;

    @Autowired
    private DashBoardSettingsService dashBoardSettingsService;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private BaseDao baseDao;

    public void afterPropertiesSet() throws Exception {
        DashboardSettings dashboardSettings = dashBoardSettingsService.getDashBoardSettings();
        mailSender.dashboardSettings = dashboardSettings;
        mailSender.setAuth();
        // logger.info(dashboardSettings);
        messageQueue = new ArrayBlockingQueue<Object>(20);
        running = true;
        // BasicConfigurator.configure();
        ExecutorService executor = Executors.newFixedThreadPool(8);
        // for (int i = 0; i < 2; i++) {
        Runnable worker = new WorkerThread(messageQueue);
        executor.execute(worker);
    }

    public void addMessageToQueue(Object object) {
        try {
            messageQueue.put(object);
            if (object instanceof Mail) {
                Mail mail = (Mail) object;
                logger.info("adding to queue..." + mail);
            }
            if (object instanceof Sms) {
                Sms sms = (Sms) object;
                logger.info("adding to queue..." + sms);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
    }

}
