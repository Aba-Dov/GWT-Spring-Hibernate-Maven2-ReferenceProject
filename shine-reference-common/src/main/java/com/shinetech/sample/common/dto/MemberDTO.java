package com.shinetech.sample.common.dto;

import java.io.Serializable;

/**
 * DTO representing a basic Member Object
 * 
 * @author <a href="mailto:stephen.callaghan@shinetech.com">Stephen Callaghan</a>
 * @version 1.0 18/09/2007
 */

public class MemberDTO implements Serializable
{

    static final long serialVersionUID = 8252115675437549444L;

    private long id;
    
    private String firstName;
    private String lastName;

    public long getId() { return id;}
    public void setId(long id) { this.id = id;}
    
    public String getFirstName() { return firstName;}
    public void setFirstName(String firstName) { this.firstName = firstName;}

    public String getLastName() { return lastName;}
    public void setLastName(String lastName) { this.lastName = lastName;}

    private String addressUnitNo;
    private String addressHouseNumber;
    private String addressStreet;
    private String addressCity;
    private String addressPostCode;
    
    public String getAddressUnitNo()
    {
        return addressUnitNo;
    }


    public void setAddressUnitNo(String addressUnitNo)
    {
        this.addressUnitNo = addressUnitNo;
    }


    public String getAddressHouseNumber()
    {
        return addressHouseNumber;
    }


    public void setAddressHouseNumber(String addressHouseNumber)
    {
        this.addressHouseNumber = addressHouseNumber;
    }


    public String getAddressStreet()
    {
        return addressStreet;
    }


    public void setAddressStreet(String addressStreet)
    {
        this.addressStreet = addressStreet;
    }


    public String getAddressCity()
    {
        return addressCity;
    }


    public void setAddressCity(String addressCity)
    {
        this.addressCity = addressCity;
    }


    public String getAddressPostCode()
    {
        return addressPostCode;
    }


    public void setAddressPostCode(String addressPostCode)
    {
        this.addressPostCode = addressPostCode;
    }
}
