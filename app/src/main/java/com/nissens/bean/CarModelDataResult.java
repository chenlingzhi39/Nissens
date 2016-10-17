package com.nissens.bean;

import java.util.ArrayList;

/**
 * Created by PC-20160514 on 2016/10/17.
 */

public class CarModelDataResult extends Result {
    ArrayList<Car> data;

    public ArrayList<Car> getData() {
        return data;
    }

    public void setData(ArrayList<Car> data) {
        this.data = data;
    }
}
