package com.nissens.bean;

/**
 * Created by Administrator on 2016/11/30.
 */

public class CategoryPropertyContentRequest extends CategoryPropertyColumnRequest{
private String IsRealQuery;

    public CategoryPropertyContentRequest(String isExtern, String propertyNameID, String isRealQuery) {
        super(isExtern, propertyNameID);
        IsRealQuery = isRealQuery;
    }
}
