package com.nissens.bean;

/**
 * Created by Administrator on 2016/11/30.
 */

public class CategoryPropertyNameRequest extends Request{
private String CategoryID,IsExtern;

    public CategoryPropertyNameRequest(String categoryID, String isExtern) {
        CategoryID = categoryID;
        IsExtern = isExtern;
    }
}
