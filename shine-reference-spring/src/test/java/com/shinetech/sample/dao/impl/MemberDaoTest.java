package com.shinetech.sample.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shinetech.sample.SpringTestBase;
import com.shinetech.sample.service.dao.impl.MemberDao;
import com.shinetech.sample.service.domain.Member;


/**
 * @author <a href="mailto:stephen.callaghan@shinetech.com">Stephen Callaghan</a>
 * @version 1.0 28/03/2006
 */
public class MemberDaoTest extends SpringTestBase
{
	private static final Log LOG = LogFactory.getLog(MemberDaoTest.class);
    
    /**
     * Basic CRUD Test
     */
    public void testCRUD()
    {
        try
        {
            LOG.info("testGetSaveDelete()");
            MemberDao dao = (MemberDao) context.getBean("MemberDao");

            long initialNum = dao.countAll();
            LOG.info("Initial Size : " + initialNum);

            LOG.info("Save New Member");
            Member member = new Member();                                                              
            member.setFirstName("Stephen");
            member.setLastName("Callaghan");
            dao.saveOrUpdate(member);
            LOG.info(member);
            
            LOG.info("Check New Size is : " + (initialNum + 1));
            long newNum = dao.countAll();
            
            LOG.info("New Size : " + newNum);
            assertEquals(initialNum + 1, newNum);

            LOG.info("Delete");
            dao.delete(member);
            
            long finalNum = dao.countAll();
            LOG.info("Final  Size : " + finalNum);
            assertEquals(initialNum, finalNum);
        }
        catch (Exception e)
        {
            LOG.error("Exception", e);
            fail(e.toString());
        }
    }

}
