package com.nissens.bean;

/**
 * Created by Administrator on 2016/12/2.
 */

public class Property {
    private String column,content;

    public Property(String column, String content) {
        this.column = column;
        this.content = content;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
