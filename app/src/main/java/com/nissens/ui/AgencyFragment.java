package com.nissens.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nissens.R;
import com.nissens.adapter.OrganizationAdapter;
import com.nissens.annotation.ActivityFragmentInject;
import com.nissens.base.BaseFragment;
import com.nissens.bean.BrandOrganization;
import com.nissens.bean.Request;
import com.nissens.module.presenter.OrganizationPresenter;
import com.nissens.module.presenter.OrganizationPresenterImpl;
import com.nissens.module.view.OrganizationView;
import com.nissens.widget.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/17.
 */
@ActivityFragmentInject(
        contentViewId = R.layout.fragment_agency
)
public class AgencyFragment extends BaseFragment<OrganizationPresenter> implements OrganizationView {
    @BindView(R.id.organization_list)
    RecyclerView organizationList;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.empty)
    TextView empty;
    @BindView(R.id.error)
    TextView error;
    OrganizationAdapter organizationAdapter;
    @Override
    protected void initView(View fragmentRootView) {
     ButterKnife.bind(this,fragmentRootView);
        organizationAdapter = new OrganizationAdapter(getActivity());
        organizationList.setAdapter(organizationAdapter);
        organizationList.setLayoutManager(new LinearLayoutManager(getActivity()));
        organizationList.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mPresenter = new OrganizationPresenterImpl(this);
        mPresenter.requestData(gson.toJson(new Request()));
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
}
