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
    @BindView(android.R.id.text1)
    TextView text1;

    public CarTypeViewHolder(ViewGroup parent) {
        super(parent, R.layout.list_item);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void setData(String data) {
       text1.setText(data);
    }
}
