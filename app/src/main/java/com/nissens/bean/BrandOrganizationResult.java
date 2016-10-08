package com.nissens.bean;

import java.util.ArrayList;

/**
 * Created by PC-20160514 on 2016/9/18.
 */
public class BrandOrganizationResult extends Result{
    private String AllItemsCount;

    public String getAllItemsCount() {
        return AllItemsCount;
    }

    public void setAllItemsCount(String allItemsCount) {
        AllItemsCount = allItemsCount;
    }

    private ArrayList<BrandOrganization> data;

    public ArrayList<BrandOrganization> getData() {
        return data;
    }

    public void setData(ArrayList<BrandOrganization> data) {
        this.data = data;
    }
}
