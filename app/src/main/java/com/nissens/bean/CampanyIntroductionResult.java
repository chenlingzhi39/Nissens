package com.nissens.bean;

/**
 * Created by PC-20160514 on 2016/9/18.
 */
public class CampanyIntroductionResult extends Result{
    private String BrandBusinessID="e23d4773e-6a16-4471-9a46-496e307cae0f",
            BrandBusinessName="尼盛斯",
            CompanyIntroduction;

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
