package com.nissens.bean;

/**
 * Created by PC-20160514 on 2016/9/26.
 */
public class BrandBusinessRequest extends Request{
    private String BrandBusinessID,BrandBusinessName;

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
}
