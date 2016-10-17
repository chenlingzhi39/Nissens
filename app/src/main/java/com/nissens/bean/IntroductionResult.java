package com.nissens.bean;

/**
 * Created by PC-20160514 on 2016/10/17.
 */

public class IntroductionResult extends Result{
    private String CompanyIntroduction,BrandBusinessID,BrandBusinessName;

    public String getBrandBusinessID() {
        return BrandBusinessID;
    }

    public void setBrandBusinessID(String brandBusinessID) {
        BrandBusinessID = brandBusinessID;
    }

    public String getBrandBusinessName() {
        return BrandBusinessName;
    }

    public void setBrandBusinessName(String brandBusinessName) {
        BrandBusinessName = brandBusinessName;
    }

    public String getCompanyIntroduction() {
        return CompanyIntroduction;
    }

    public void setCompanyIntroduction(String companyIntroduction) {
        CompanyIntroduction = companyIntroduction;
    }
}
