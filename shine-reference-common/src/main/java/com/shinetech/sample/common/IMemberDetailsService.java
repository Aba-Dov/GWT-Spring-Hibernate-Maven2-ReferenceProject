package com.shinetech.sample.common;

import com.shinetech.sample.common.dto.MemberDTO;

/**
 * Interface for retrieving and updating member details.
 * 
 * @author <a href="mailto:stephen.callaghan@shinetech.com">Stephen Callaghan</a>
 * @version 1.0 18/09/2007
 */

public interface IMemberDetailsService
{    
    long countAll();
    MemberDTO get(long memberNumber);
    void saveOrUpdate(MemberDTO member);
    void delete(long id);
}

