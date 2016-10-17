package com.nissens.adapter;

import android.content.Context;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/10/18.
 */

public class CarTypeAdapter extends RecyclerArrayAdapter<String>{
    public CarTypeAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new CarTypeViewHolder(parent);
    }
}
