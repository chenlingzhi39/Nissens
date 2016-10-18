package com.nissens.adapter;

import android.view.ViewGroup;
import android.widget.TextView;

import com.nissens.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/18.
 */

public class CarTypeViewHolder extends BaseViewHolder<String> {
    @BindView(R.id.text)
    TextView text;

    public CarTypeViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_car_type);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void setData(String data) {
       text.setText(data);
    }
}
