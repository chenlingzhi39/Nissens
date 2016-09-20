package com.nissens.bean;

import java.util.ArrayList;

/**
 * Created by PC-20160514 on 2016/9/18.
 */
public class BrandPartResult extends Result{
    private String BrandBusinessName,
            BrandPropertyName,
            PageIndex,
            ItemsPerPage,
            AllItemsCount;
    private ArrayList<BrandBusiness> data;

    public String getAllItemsCount() {
        return AllItemsCount;
    }

    public void setAllItemsCount(String allItemsCount) {
        AllItemsCount = allItemsCount;
    }

    public String getBrandBusinessName() {
        return BrandBusinessName;
    }

    public void setBrandBusinessName(String brandBusinessName) {
        BrandBusinessName = brandBusinessName;
    }

    public String getBrandPropertyName() {
        return BrandPropertyName;
    }

    public void setBrandPropertyName(String brandPropertyName) {
        BrandPropertyName = brandPropertyName;
    }

    public ArrayList<BrandBusiness> getData() {
        return data;
    }

    public void setData(ArrayList<BrandBusiness> data) {
        this.data = data;
    }

    public String getItemsPerPage() {
        return ItemsPerPage;
    }

    public void setItemsPerPage(String itemsPerPage) {
        ItemsPerPage = itemsPerPage;
    }

    public String getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(String pageIndex) {
        PageIndex = pageIndex;
    }
}
