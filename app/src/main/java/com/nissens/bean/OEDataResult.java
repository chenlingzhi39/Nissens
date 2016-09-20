package com.nissens.bean;

import java.util.ArrayList;

/**
 * Created by PC-20160514 on 2016/9/18.
 */
public class OEDataResult extends Result{
    private String PageIndex,ItemsPerPage,AllItemsCount;
    private ArrayList<OEData> data;

    public String getItemsPerPage() {
        return ItemsPerPage;
    }

    public void setItemsPerPage(String itemsPerPage) {
        ItemsPerPage = itemsPerPage;
    }

    public String getAllItemsCount() {
        return AllItemsCount;
    }

    public void setAllItemsCount(String allItemsCount) {
        AllItemsCount = allItemsCount;
    }

    public ArrayList<OEData> getData() {
        return data;
    }

    public void setData(ArrayList<OEData> data) {
        this.data = data;
    }

    public String getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(String pageIndex) {
        PageIndex = pageIndex;
    }
}
