package com.shinetech.sample.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.shinetech.sample.common.IMemberDetailsService;
import com.shinetech.sample.common.dto.MemberDTO;
import com.shinetech.sample.service.dao.impl.MemberDao;
import com.shinetech.sample.service.domain.Member;

/**
 * Implementation for IMemberDetailsService. 
 * Has an injected Hibernate DAO Layer for persistence 
 * (which saves to in-memory HSQLDB)
 *  
 * @author <a href="mailto:stephen.callaghan@shinetech.com">Stephen Callaghan</a>
 * @version 1.0 18/09/2007
 */
public class MemberDetailsServiceImpl implements IMemberDetailsService
{
    private static final Log LOG = LogFactory.getLog(MemberDetailsServiceImpl.class);

    @Autowired
    @Qualifier("MemberDao")
    protected MemberDao daoMember;
    
    public MemberDetailsServiceImpl()
    {
        LOG.info("Creating Service...");
    }


    public void saveOrUpdate(MemberDTO memberDTO)
    {   
        LOG.info("saveOrUpdate() " + memberDTO);
        Member member = new Member();
        member.setFromMemberDTO(memberDTO);
        daoMember.saveOrUpdate(member);
    }


    public MemberDTO get(long memberNumber)
    {
        LOG.info("get() : " + memberNumber);
        Member member = daoMember.get(memberNumber);
        if (member != null)
        {
            return member.toMemberDTO();    
        }
        return null;
    }
    
    public void delete(long id)
    {   
        LOG.info("delete() " + id);
        Member member = daoMember.get(id);
        if (member == null)
        {
            throw new IllegalStateException("Member ID does not exist in database : " + id);
        }
        daoMember.delete(member);
    }

    public long countAll()
    {   
        LOG.info("countAll() ");
        return daoMember.countAll();
    }
}