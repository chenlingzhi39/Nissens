package com.nissens.bean;

import java.util.ArrayList;

/**
 * Created by PC-20160514 on 2016/9/18.
 */
public class BrandBusinessResult extends Result{
    private String BrandBusinessCount;
    private ArrayList<BrandBusiness> data;

    public String getBrandBusinessCount() {
        return BrandBusinessCount;
    }

    public void setBrandBusinessCount(String brandBusinessCount) {
        BrandBusinessCount = brandBusinessCount;
    }

    public ArrayList<BrandBusiness> getData() {
        return data;
    }

    public void setData(ArrayList<BrandBusiness> data) {
        this.data = data;
    }
}
