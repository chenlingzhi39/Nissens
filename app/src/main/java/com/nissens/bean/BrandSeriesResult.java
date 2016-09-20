package com.nissens.bean;

/**
 * Created by PC-20160514 on 2016/9/18.
 */
public class BrandSeriesResult extends Result{
    private String ParentSeriesID,BrandBusinessID,BrandBusinessName,SeriesID,SeriesName;

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

    public String getParentSeriesID() {
        return ParentSeriesID;
    }

    public void setParentSeriesID(String parentSeriesID) {
        ParentSeriesID = parentSeriesID;
    }

    public String getSeriesID() {
        return SeriesID;
    }

    public void setSeriesID(String seriesID) {
        SeriesID = seriesID;
    }

    public String getSeriesName() {
        return SeriesName;
    }

    public void setSeriesName(String seriesName) {
        SeriesName = seriesName;
    }
}
