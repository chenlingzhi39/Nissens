package com.nissens.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.nissens.bean.Car;

/**
 * Created by PC-20160514 on 2016/9/26.
 */
public class CarAdapter extends RecyclerArrayAdapter<Car>{
    private boolean has_info;
    public CarAdapter(Context context,boolean has_info) {
        super(context);
        this.has_info=has_info;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new CarViewHolder(parent,has_info);
    }
}
