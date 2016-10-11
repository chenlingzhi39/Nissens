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
    @BindView(R.id.factory_id)
    TextView factory_id;
    @BindView(R.id.original_factory_id)
    TextView originalFactoryId;

    public OEDataViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_oedata);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(OEData data) {
        name.setText(data.getPartName());
        factory_id.setText(getContext().getString(R.string.factory_id) + ":" + data.getFactoryID());
        originalFactoryId.setText(getContext().getString(R.string.original_factory_id)+ ":" +data.getOriginalFactoryID());
    }

}
