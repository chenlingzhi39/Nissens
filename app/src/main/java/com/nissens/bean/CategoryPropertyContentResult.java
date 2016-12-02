package com.nissens.bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/30.
 */

public class CategoryPropertyContentResult extends Result{
    private String CategoryID,CategoryPropertyCount,IsExtern;
    private ArrayList<CategoryPropertyContent> data;

    public String getCategoryPropertyCount() {
        return CategoryPropertyCount;
    }

    public String getCategoryID() {
        return CategoryID;
    }

    public ArrayList<CategoryPropertyContent> getData() {
        return data;
    }

    public String getIsExtern() {
        return IsExtern;
    }
}
