package com.nissens.bean;

import java.util.ArrayList;

/**
 * Created by PC-20160514 on 2016/10/25.
 */

public class ImageResult extends Result {
    String OriginalFactoryID, OriginalFactoryName, AllItemsCount;
    private ArrayList<Image> data;

    public String getAllItemsCount() {
        return AllItemsCount;
    }

    public void setAllItemsCount(String allItemsCount) {
        AllItemsCount = allItemsCount;
    }

    public ArrayList<Image> getData() {
        return data;
    }

    public void setData(ArrayList<Image> data) {
        this.data = data;
    }

    public String getOriginalFactoryID() {
        return OriginalFactoryID;
    }

    public void setOriginalFactoryID(String originalFactoryID) {
        OriginalFactoryID = originalFactoryID;
    }

    public String getOriginalFactoryName() {
        return OriginalFactoryName;
    }

    public void setOriginalFactoryName(String originalFactoryName) {
        OriginalFactoryName = originalFactoryName;
    }
}
