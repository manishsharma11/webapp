package com.main.sts;

import junit.framework.Assert;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * 
 * @author ajaidka
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-security.xml"})
//@ContextConfiguration(locations = {"classpath:servlet-context.xml"})
@WebAppConfiguration
public class BaseTest {

    @Ignore
    @Test
    public void testDummy() {
        Assert.assertNull(null);
    }
    
    @Autowired
    private SessionFactory sessionFactory; 
    
    @Before
    public void setUp() throws Exception {
    	TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(sessionFactory.openSession()));
	}

    @After
    public void tearDown() throws Exception {
		SessionHolder sessionHolder = (SessionHolder) TransactionSynchronizationManager.unbindResource(sessionFactory);
		SessionFactoryUtils.closeSession(sessionHolder.getSession());
    }
   
}