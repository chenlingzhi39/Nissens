package com.nissens.adapter;

import android.text.util.Linkify;
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

    public OrganizationViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_organization);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void setData(BrandOrganization data) {
        Linkify.addLinks
                (
                       number, Linkify.PHONE_NUMBERS
                );
        name.setText(data.getOrganizationName());
        address.setText(getContext().getString(R.string.address)+":"+data.getOrgAddress());
        number.setText(getContext().getString(R.string.tel)+":"+data.getOrgTel());
    }
}
