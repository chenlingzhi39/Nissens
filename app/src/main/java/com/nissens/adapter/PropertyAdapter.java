package com.nissens.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.nissens.bean.Property;

/**
 * Created by Administrator on 2016/12/2.
 */

public class PropertyAdapter extends RecyclerArrayAdapter<Property> {
    public PropertyAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new PropertyViewHolder(parent);
    }
}
