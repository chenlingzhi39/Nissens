package com.nissens.ui;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.appcompat.BuildConfig;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nissens.R;
import com.nissens.adapter.PropertyAdapter;
import com.nissens.annotation.ActivityFragmentInject;
import com.nissens.base.BaseFragment;
import com.nissens.bean.CategoryPropertyColumn;
import com.nissens.bean.CategoryPropertyContent;
import com.nissens.bean.CategoryPropertyContentRequest;
import com.nissens.bean.Property;
import com.nissens.module.presenter.ContentPresenter;
import com.nissens.module.presenter.ContentPresenterImpl;
import com.nissens.module.view.ContentView;
import com.nissens.util.ScrimUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/1.
 */
@ActivityFragmentInject(
        contentViewId = R.layout.fragment_content
)
public class ContentFragment extends BaseFragment<ContentPresenter> implements ContentView {
    @BindView(R.id.list)
    RecyclerView list;
    ArrayList<CategoryPropertyContent> contents;
    ArrayList<CategoryPropertyColumn> columns;
    ArrayList<String> specifications;
    PropertyAdapter propertyAdapter;
    @BindView(R.id.shadow)
    View shadow;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.empty)
    TextView empty;
    @BindView(R.id.error)
    TextView error;

    @Override
    protected void initView(View fragmentRootView) {
        ButterKnife.bind(this, fragmentRootView);
        mPresenter = new ContentPresenterImpl(this);
        if(Build.VERSION.SDK_INT >=16)
        shadow.setBackground(
                ScrimUtil.makeCubicGradientScrimDrawable(
                        Color.parseColor("#55000000"), //颜色
                        8, //渐变层数
                        Gravity.TOP));
        shadow.setVisibility(View.VISIBLE);
        if (!TextUtils.isEmpty(getArguments().getString("specification"))) {
            contents=new ArrayList<>();
            columns=new ArrayList<>();
            specifications=new ArrayList<>();
            specifications.addAll(Arrays.asList(getArguments().getString("specification").split("\\|")));
            Log.i("category_request",gson.toJson(new CategoryPropertyContentRequest(getArguments().getString("isExternal","No"), getArguments().getString("property_id"), "Yes")));
            mPresenter.requestData(gson.toJson(new CategoryPropertyContentRequest(getArguments().getString("isExternal","Yes"), getArguments().getString("property_id"), "Yes")));
        } else empty.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmpty() {
        if(columns .size()==0|| contents .size()==0)
        empty.setVisibility(View.VISIBLE);
    }

    @Override
    public void showResult(Object o) {
        error.setVisibility(View.GONE);
        if (((List) o).get(0) instanceof CategoryPropertyColumn)
            columns.addAll((List<CategoryPropertyColumn>) o);
        else contents.addAll((List<CategoryPropertyContent>) o);
        if (columns .size()>0&& contents .size()>0) {
            empty.setVisibility(View.GONE);
            propertyAdapter = new PropertyAdapter(getActivity());
            for (CategoryPropertyColumn categoryPropertyColumn : columns) {
                for (CategoryPropertyContent categoryPropertyContent : contents) {
                    if (specifications.contains(categoryPropertyContent.getPropertyContentID())) {
                        String[] strings = categoryPropertyContent.getPropertyContentArray().split("\\|");
                        for(int i=1;i<strings.length;i++){
                            String[] strs=strings[i].split("=");
                                if (strs[0].equals(categoryPropertyColumn.getPropertyColumnID()))
                                    propertyAdapter.add(new Property(categoryPropertyColumn.getPropertyColumn(), strs[1]));
                        }
                    }
                }
            }
            list.setAdapter(propertyAdapter);
            list.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
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

    @OnClick(R.id.error)
    public void onClick() {
        error.setVisibility(View.GONE);
        mPresenter.requestData(gson.toJson(new CategoryPropertyContentRequest(getArguments().getString("isExternal","No"), getArguments().getString("property_id"), "Yes")));
    }
}
