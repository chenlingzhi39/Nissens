package com.nissens.bean;

/**
 * Created by Administrator on 2016/11/30.
 */

public class CategoryPropertyColumnRequest extends Request{
private String PropertyNameID,IsExtern;

    public CategoryPropertyColumnRequest(String isExtern, String propertyNameID) {
        IsExtern = isExtern;
        PropertyNameID = propertyNameID;
    }
}
