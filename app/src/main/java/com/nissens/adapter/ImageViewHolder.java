package com.nissens.adapter;

import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nissens.R;
import com.nissens.bean.Image;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PC-20160514 on 2016/10/25.
 */

public class ImageViewHolder extends BaseViewHolder<Image> {
    @BindView(R.id.image)
    ImageView image;

    public ImageViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_image);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void setData(Image data) {
        if(!TextUtils.isEmpty(data.getOEPartImagePath()))
        Glide.with(getContext()).load(data.getOEPartImagePath()).into(image);
    }
}
