package com.nissens.bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/30.
 */

public class CategoryPropertyColumnResult extends Result{
    private String CategoryID,CategoryPropertyCount,IsExtern;
    private ArrayList<CategoryPropertyColumn> data;

    public String getCategoryID() {
        return CategoryID;
    }

    public String getCategoryPropertyCount() {
        return CategoryPropertyCount;
    }

    public ArrayList<CategoryPropertyColumn> getData() {
        return data;
    }

    public String getIsExtern() {
        return IsExtern;
    }
}
