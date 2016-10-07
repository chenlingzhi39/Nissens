package com.nissens.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.nissens.R;
import com.nissens.adapter.OrganizationAdapter;
import com.nissens.annotation.ActivityFragmentInject;
import com.nissens.base.BaseActivity;
import com.nissens.bean.BrandBusinessRequest;
import com.nissens.bean.BrandOrganization;
import com.nissens.bean.BrandOrganizationRequest;
import com.nissens.bean.Request;
import com.nissens.module.presenter.OrganizationPresenter;
import com.nissens.module.presenter.OrganizationPresenterImpl;
import com.nissens.module.view.OrganizationView;
import com.nissens.widget.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.AddressPicker;
import cn.qqtheme.framework.util.ConvertUtils;

/**
 * Created by PC-20160514 on 2016/10/7.
 */
@ActivityFragmentInject(
        contentViewId = R.layout.activity_search_organization,
        toolbarTitle = R.string.agency
)
public class SearchOrganizationActivity extends BaseActivity<OrganizationPresenter> implements OrganizationView {
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.empty)
    TextView empty;
    @BindView(R.id.error)
    TextView error;
    @BindView(R.id.organization_list)
    RecyclerView organizationList;
    OrganizationAdapter organizationAdapter;
    @BindView(R.id.select_city)
    Button selectCity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        organizationAdapter = new OrganizationAdapter(this);
        organizationList.setAdapter(organizationAdapter);
        organizationList.setLayoutManager(new LinearLayoutManager(this));
        organizationList.addItemDecoration(new DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL_LIST));
        mPresenter = new OrganizationPresenterImpl(this);
        //mPresenter.requestData(gson.toJson(new Request()));
    }

    @Override
    public void showEmpty() {
        if (organizationAdapter.getCount() == 0) {
            organizationAdapter.clear();
            empty.setVisibility(View.VISIBLE);
        } else organizationAdapter.stopMore();
    }

    @Override
    public void showResult(List<BrandOrganization> brandOrganizations) {
        organizationList.setVisibility(View.VISIBLE);
        empty.setVisibility(View.GONE);
        error.setVisibility(View.GONE);
        organizationAdapter.addAll(brandOrganizations);
    }

    @Override
    public void showError() {
        if (organizationAdapter.getCount() == 0) {
            organizationAdapter.clear();
            error.setVisibility(View.VISIBLE);
        } else organizationAdapter.pauseMore();
    }

    @Override
    public void showProgress() {
        error.setVisibility(View.GONE);
        empty.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        organizationList.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @OnClick(R.id.select_city)
    public void onClick() {
        try {
            ArrayList<AddressPicker.Province> data = new ArrayList<>();
            final String json = ConvertUtils.toString(getAssets().open("city.json"));
            data.addAll(JSON.parseArray(json, AddressPicker.Province.class));
            AddressPicker picker = new AddressPicker(this, data);
            picker.setSelectedItem("江苏", "无锡", "惠山");
            picker.setOnAddressPickListener(new AddressPicker.OnAddressPickListener() {
                @Override
                public void onAddressPicked(AddressPicker.Province province, AddressPicker.City city, AddressPicker.County county) {
                    if (county == null) {
                        selectCity.setText( province.getAreaName()+" "+city.getAreaName());
                    } else {
                        if(!province.getAreaName().equals(city.getAreaName()))
                        selectCity.setText( province.getAreaName()+" "+city.getAreaName());
                        else  selectCity.setText(city.getAreaName()+" "+county.getAreaName());
                    }
                    organizationAdapter.clear();
                    BrandOrganizationRequest brandOrganizationRequest;
                    if(!province.getAreaName().equals(city.getAreaName()))
                     brandOrganizationRequest=new BrandOrganizationRequest(province.getAreaName().substring(0,province.getAreaName().length()-1),city.getAreaName().substring(0,city.getAreaName().length()-1));
                    else brandOrganizationRequest=new BrandOrganizationRequest(province.getAreaName().substring(0,province.getAreaName().length()-1),county.getAreaName().substring(0,county.getAreaName().length()-1));
                    mPresenter.requestData(gson.toJson(brandOrganizationRequest));
                }
            });
            picker.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
