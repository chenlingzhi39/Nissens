package com.nissens.ui;

import android.graphics.PointF;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nissens.R;
import com.nissens.annotation.ActivityFragmentInject;
import com.nissens.base.BaseFragment;
import com.nissens.bean.CategoryPropertyName;
import com.nissens.bean.CategoryPropertyNameRequest;
import com.nissens.module.presenter.PropertyPresenter;
import com.nissens.module.presenter.PropertyPresenterImpl;
import com.nissens.module.view.PropertyView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/11/30.
 */
@ActivityFragmentInject(
        contentViewId = R.layout.fragment_property
)
public class PropertyFragment extends BaseFragment<PropertyPresenter> implements PropertyView {

    @BindView(R.id.layout_tab)
    TabLayout layoutTab;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.empty)
    TextView empty;
    @BindView(R.id.error)
    TextView error;

    @Override
    protected void initView(View fragmentRootView) {
        ButterKnife.bind(this, fragmentRootView);
        mPresenter=new PropertyPresenterImpl(this);
        mPresenter.requestData(gson.toJson(new CategoryPropertyNameRequest(getArguments().getString("category_id"), getArguments().getBoolean("isExternal") ? "Yes" : "No")));
    }



    @OnClick(R.id.empty)
    public void onClick() {
        mPresenter.requestData(gson.toJson(new CategoryPropertyNameRequest(getArguments().getString("category_id"), getArguments().getBoolean("isExternal") ? "Yes" : "No")));
    }

    public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
        private List<CategoryPropertyName> categoryPropertyNames;

        public SimpleFragmentPagerAdapter(FragmentManager fm, List<CategoryPropertyName> categoryPropertyNames) {
            super(fm);
            this.categoryPropertyNames = categoryPropertyNames;
        }

        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            bundle.putString("specification",getArguments().getString("specification"));
            bundle.putBoolean("isExternal",getArguments().getBoolean("isExternal"));
            bundle.putString("property_id",categoryPropertyNames.get(position).getPropertyNameID());
            ContentFragment contentFragment=new ContentFragment();
            contentFragment.setArguments(bundle);
            return contentFragment;

        }

        @Override
        public int getCount() {
            return categoryPropertyNames.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return categoryPropertyNames.get(position).getPropertyName();
        }
    }

    @Override
    public void showEmpty() {
        error.setVisibility(View.GONE);
        empty.setVisibility(View.VISIBLE);
    }

    @Override
    public void showResult(List<CategoryPropertyName> categoryPropertyNames) {
        error.setVisibility(View.GONE);
        layoutTab.setVisibility(View.VISIBLE);
        viewpager.setAdapter(new SimpleFragmentPagerAdapter(getChildFragmentManager(), categoryPropertyNames));
        layoutTab.setupWithViewPager(viewpager);
    }

    @Override
    public void showError() {
        error.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }
}
