/*
 * Copyright 2008 Shine Technologies 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 * 
 * @author Stephen Callaghan
 */
package com.shinetech.server;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.shinetech.client.IMemberDetailsGWTService;
import com.shinetech.sample.common.IMemberDetailsService;
import com.shinetech.sample.common.dto.MemberDTO;

public class SampleServiceImpl extends RemoteServiceServlet implements IMemberDetailsGWTService
{
    private static final Log LOG = LogFactory.getLog(SampleServiceImpl.class);

    private IMemberDetailsService svcMemberDetails;

    public void init(ServletConfig config) throws ServletException
    {
        super.init(config);
        LOG.info("init()");
        //ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "springconfig-remote-services.xml");
        svcMemberDetails = (IMemberDetailsService)context.getBean("memberDetailsService");
    }


    public MemberDTO getMemberDetails(long memberNumber)
    {
        try
        {
            LOG.info("getMemberDetails : " + memberNumber);
            return svcMemberDetails.get(memberNumber);
        }
        catch (Exception e)
        {
            e.printStackTrace(System.out);
            throw new RuntimeException(e);
        }
    }


    public void saveMemberDetails(MemberDTO member)
    {
        LOG.info("saveMemberDetails() : " + member);
        svcMemberDetails.saveOrUpdate(member);
    }

    public void deleteMemberDetails(long id)
    {
        LOG.info("deleteMemberDetails() : " + id);
        svcMemberDetails.delete(id);
    }
    
    public long countAll()
    {
        long count = svcMemberDetails.countAll();
        System.out.println("Count All : " + count);
        return count;
    }
}
