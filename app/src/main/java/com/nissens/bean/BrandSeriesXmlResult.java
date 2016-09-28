package com.nissens.bean;

import java.util.ArrayList;

/**
 * Created by PC-20160514 on 2016/9/28.
 */
public class BrandSeriesXmlResult extends Result{
private String BrandBusinessID="e23d4773e-6a16-4471-9a46-496e307cae0f";
private ArrayList<BrandSeriesXml> data;

    public String getBrandBusinessID() {
        return BrandBusinessID;
    }

    public void setBrandBusinessID(String brandBusinessID) {
        BrandBusinessID = brandBusinessID;
    }

    public ArrayList<BrandSeriesXml> getData() {
        return data;
    }

    public void setData(ArrayList<BrandSeriesXml> data) {
        this.data = data;
    }
}
