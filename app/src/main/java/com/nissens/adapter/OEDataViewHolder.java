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
    @BindView(R.id.id)
    TextView id;
    @BindView(R.id.label)
    TextView label;

    public OEDataViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_oedata);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(OEData data) {
        name.setText(data.getOriginalFactoryName());
        id.setText(getContext().getString(R.string.original_factory_id) + ":" + data.getOriginalFactoryID());
        label.setText(getContext().getString(R.string.factory_label)+":"+data.getFactoryLabel());
    }

}
