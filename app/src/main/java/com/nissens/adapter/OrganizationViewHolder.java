package com.nissens.adapter;

import android.view.ViewGroup;
import android.widget.TextView;

import com.nissens.R;
import com.nissens.bean.BrandOrganization;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PC-20160514 on 2016/10/7.
 */

public class OrganizationViewHolder extends BaseViewHolder<BrandOrganization> {
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.number)
    TextView number;
    @BindView(R.id.web)
    TextView web;
    @BindView(R.id.e_mail)
    TextView eMail;


    public OrganizationViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_organization);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(BrandOrganization data) {
        name.setText(data.getOrganizationName());
        address.setText(getContext().getString(R.string.address) + ":" + data.getOrgAddress());
        number.setText(getContext().getString(R.string.tel) + ":" + data.getOrgTel());
        eMail.setText(getContext().getString(R.string.e_mail) + ":" + data.getOrgEmail());
        web.setText(getContext().getString(R.string.web) + ":" + data.getOrgWebUrl());
    }
}
