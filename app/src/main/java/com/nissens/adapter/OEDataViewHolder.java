package com.nissens.adapter;

import android.view.ViewGroup;
import android.widget.TextView;

import com.nissens.R;
import com.nissens.bean.OEData;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PC-20160514 on 2016/9/22.
 */
public class OEDataViewHolder extends BaseViewHolder<OEData> {
    @BindView(R.id.name)
    TextView name;

    public OEDataViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_oedata);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void setData(OEData data) {
     name.setText(data.getFactoryName());
    }

}
