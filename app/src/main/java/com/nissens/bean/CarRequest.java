package com.nissens.bean;

/**
 * Created by PC-20160514 on 2016/9/26.
 */
public class CarRequest extends Request{
    private String PageIndex,
            ItemsPerPage,
            FactoryID;

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

    public CarRequest(String factoryID, String itemsPerPage, String pageIndex) {
        FactoryID = factoryID;
        ItemsPerPage = itemsPerPage;
        PageIndex = pageIndex;
    }
}
