package com.shinetech.sample;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 *
 */
public abstract class SpringTestBase extends TestCase
{
    protected static final Log LOG = LogFactory.getLog(SpringTestBase.class);

    protected static ApplicationContext context;
    protected static SessionFactory sessionFactory;

    /**
     * Constructor
     */
    public SpringTestBase()
    {
        try
        {
            LOG.info("ServiceTestBase()");
            if (context == null)
            {
                LOG.info("Setting Spring Context");
                context = new ClassPathXmlApplicationContext(new String[] { 
                        "springconfig-dao.xml", "springconfig-service.xml"});
                sessionFactory = (SessionFactory) context.getBean("sessionFactory");
            }
        }
        catch (Exception e)
        {
            LOG.error("Exception initialising services", e);
            throw new RuntimeException("Failed to setup() :" + e);
        }
    }

    /**
     * Helper to get Session
     * @return
     */
    protected Session getSession()
    {
        return SessionFactoryUtils.getSession(sessionFactory, true);
    }
    
    /**
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception
    {
        super.setUp();
        Session session = getSession();
        TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(session));
    }


    /**
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception
    {
        SessionHolder sessionHolder = (SessionHolder)TransactionSynchronizationManager
                .unbindResource(sessionFactory);
        SessionFactoryUtils.releaseSession(sessionHolder.getSession(), sessionFactory);
        super.tearDown();
    }
    
}
