package com.nissens.bean;

import java.util.ArrayList;

/**
 * Created by PC-20160514 on 2016/9/18.
 */
public class CarResult extends Result{
    private String PageIndex,ItemsPerPage,AllItemsCount,FactoryID;
    private ArrayList<Car> data;

    public String getAllItemsCount() {
        return AllItemsCount;
    }

    public void setAllItemsCount(String allItemsCount) {
        AllItemsCount = allItemsCount;
    }

    public ArrayList<Car> getData() {
        return data;
    }

    public void setData(ArrayList<Car> data) {
        this.data = data;
    }

    public String getFactoryID() {
        return FactoryID;
    }

    public void setFactoryID(String factoryID) {
        FactoryID = factoryID;
    }

    public String getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(String pageIndex) {
        PageIndex = pageIndex;
    }

    public String getItemsPerPage() {
        return ItemsPerPage;
    }

    public void setItemsPerPage(String itemsPerPage) {
        ItemsPerPage = itemsPerPage;
    }
}
