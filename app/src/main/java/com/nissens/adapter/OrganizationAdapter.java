package com.nissens.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.nissens.bean.BrandOrganization;

/**
 * Created by PC-20160514 on 2016/10/7.
 */

public class OrganizationAdapter extends RecyclerArrayAdapter<BrandOrganization> {
    public OrganizationAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new OrganizationViewHolder(parent);
    }
}
