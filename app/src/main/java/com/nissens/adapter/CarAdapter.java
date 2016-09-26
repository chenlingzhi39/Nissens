package com.nissens.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.nissens.bean.Car;

/**
 * Created by PC-20160514 on 2016/9/26.
 */
public class CarAdapter extends RecyclerArrayAdapter<Car>{
    public CarAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }
}
