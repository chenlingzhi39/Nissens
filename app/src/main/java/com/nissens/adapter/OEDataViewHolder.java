package com.nissens.adapter;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.nissens.bean.OEData;

/**
 * Created by PC-20160514 on 2016/9/22.
 */
public class OEDataViewHolder extends BaseViewHolder<OEData>{
    public OEDataViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
    }
    @Override
    public void setData(OEData data) {

    }

}
