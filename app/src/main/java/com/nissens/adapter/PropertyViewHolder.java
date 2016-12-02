package com.nissens.adapter;

import android.view.ViewGroup;
import android.widget.TextView;

import com.nissens.R;
import com.nissens.bean.Property;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/2.
 */

public class PropertyViewHolder extends BaseViewHolder<Property> {
    @BindView(R.id.column)
    TextView column;
    @BindView(R.id.content)
    TextView content;

    public PropertyViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_property);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void setData(Property data) {
      column.setText(data.getColumn());
      content.setText(data.getContent());
    }
}
