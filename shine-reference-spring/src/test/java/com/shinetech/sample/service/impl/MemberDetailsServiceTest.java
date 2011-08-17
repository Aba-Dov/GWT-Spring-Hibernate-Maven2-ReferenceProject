package com.shinetech.sample.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shinetech.sample.SpringTestBase;
import com.shinetech.sample.common.IMemberDetailsService;
import com.shinetech.sample.common.dto.MemberDTO;

/**
 * @author shscalla
 */
public class MemberDetailsServiceTest extends SpringTestBase
{

    private static final Log LOG = LogFactory.getLog(MemberDetailsServiceTest.class);


    public void testRemoteService()
    {
        try
        {
            LOG.info("Test Service Impl");
            IMemberDetailsService svcMemberDetails = (IMemberDetailsService)context
                    .getBean("memberDetailsService");

            LOG.info("Count All : " + svcMemberDetails.countAll());
            
            MemberDTO member = svcMemberDetails.get(1L);
            LOG.info("Member returned : " + member);
        }
        catch (Exception e)
        {
            LOG.error("Exception", e);
            fail(e.toString());
        }
    }
}
