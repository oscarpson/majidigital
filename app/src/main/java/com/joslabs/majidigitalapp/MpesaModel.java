package com.joslabs.majidigitalapp;

public class MpesaModel {
    String TransactionType;

    String BillRefNumber;

    String MSISDN;

    String FirstName;

    String MiddleName;

    String BusinessShortCode;

    String OrgAccountBalance;

    String TransAmount;

    String ThirdPartyTransID;

    String id;

    String LastName;

    String TransID;

    String TransTime;

    public String getTransactionType ()
    {
        return TransactionType;
    }

    public void setTransactionType (String TransactionType)
    {
        this.TransactionType = TransactionType;
    }

    public String getBillRefNumber ()
    {
        return BillRefNumber;
    }

    public void setBillRefNumber (String BillRefNumber)
    {
        this.BillRefNumber = BillRefNumber;
    }

    public String getMSISDN ()
    {
        return MSISDN;
    }

    public void setMSISDN (String MSISDN)
    {
        this.MSISDN = MSISDN;
    }

    public String getFirstName ()
    {
        return FirstName;
    }

    public void setFirstName (String FirstName)
    {
        this.FirstName = FirstName;
    }

    public String getMiddleName ()
    {
        return MiddleName;
    }

    public void setMiddleName (String MiddleName)
    {
        this.MiddleName = MiddleName;
    }

    public String getBusinessShortCode ()
    {
        return BusinessShortCode;
    }

    public void setBusinessShortCode (String BusinessShortCode)
    {
        this.BusinessShortCode = BusinessShortCode;
    }

    public String getOrgAccountBalance ()
    {
        return OrgAccountBalance;
    }

    public void setOrgAccountBalance (String OrgAccountBalance)
    {
        this.OrgAccountBalance = OrgAccountBalance;
    }

    public String getTransAmount ()
    {
        return TransAmount;
    }

    public void setTransAmount (String TransAmount)
    {
        this.TransAmount = TransAmount;
    }

    public String getThirdPartyTransID ()
    {
        return ThirdPartyTransID;
    }

    public void setThirdPartyTransID (String ThirdPartyTransID)
    {
        this.ThirdPartyTransID = ThirdPartyTransID;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getLastName ()
    {
        return LastName;
    }

    public void setLastName (String LastName)
    {
        this.LastName = LastName;
    }

    public String getTransID ()
    {
        return TransID;
    }

    public void setTransID (String TransID)
    {
        this.TransID = TransID;
    }

    public String getTransTime ()
    {
        return TransTime;
    }

    public void setTransTime (String TransTime)
    {
        this.TransTime = TransTime;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [TransactionType = "+TransactionType+", BillRefNumber = "+BillRefNumber+", MSISDN = "+MSISDN+", FirstName = "+FirstName+", MiddleName = "+MiddleName+", BusinessShortCode = "+BusinessShortCode+", OrgAccountBalance = "+OrgAccountBalance+", TransAmount = "+TransAmount+", ThirdPartyTransID = "+ThirdPartyTransID+", id = "+id+", LastName = "+LastName+", TransID = "+TransID+", TransTime = "+TransTime+"]";
    }
}
