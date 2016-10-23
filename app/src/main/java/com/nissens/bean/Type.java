package com.nissens.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PC-20160514 on 2016/9/29.
 */
public class Type implements Serializable{
    private String id,name;
    private List<Type> types;
    private String sortLetters;
    public Type() {
    }

    public Type(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }
}
