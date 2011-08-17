package com.shinetech.sample.service.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.shinetech.sample.common.dto.MemberDTO;


/**
 * Hibernate Domain Object for a Member
 * 
 * @author <a href="mailto:stephen.callaghan@shinetech.com">Stephen Callaghan</a>
 */
@Entity
@Table(name = "MEMBER")
public class Member
{
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "FIRST_NAME")
    private String firstName;
    
    @Column(name = "LAST_NAME")
    private String lastName;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    
    @Override
    public String toString()
    {
        return new StringBuffer().append("Id:").append(id)
        .append(", FirstName: ").append(firstName)
        .append(", LastName: ").append(lastName)
        .toString();
    }
    
    /** 
     * Convert Hibernate Domain Object, to Service DTO
     */
    public MemberDTO toMemberDTO()
    {
        MemberDTO dto = new MemberDTO();
        dto.setId(id);
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        return dto;
    }
    
    /** 
     * Convert Hibernate Domain Object, to Service DTO
     */
    public void setFromMemberDTO(MemberDTO dto)
    {
        id = dto.getId();
        firstName = dto.getFirstName();
        lastName = dto.getLastName();
    }
}
