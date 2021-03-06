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
package com.shinetech.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.shinetech.sample.common.dto.MemberDTO;


/**
 * Interface for retrieving and updating member details.
 * 
 * @author <a href="mailto:stephen.callaghan@shinetech.com">Stephen Callaghan</a>
 * @version 1.0 18/09/2007
 */
public interface IMemberDetailsGWTService extends RemoteService
{    
    long countAll();
    MemberDTO getMemberDetails(long memberNumber);
    void saveMemberDetails(MemberDTO member);
    void deleteMemberDetails(long id);

}

