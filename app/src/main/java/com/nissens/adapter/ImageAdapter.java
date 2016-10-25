package com.nissens.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.nissens.bean.Image;
import com.nissens.module.view.OEPartImageView;

/**
 * Created by PC-20160514 on 2016/10/25.
 */

public class ImageAdapter extends RecyclerArrayAdapter<Image> {
    public ImageAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageViewHolder(parent);
    }
}
