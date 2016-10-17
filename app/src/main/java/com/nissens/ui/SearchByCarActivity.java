package com.nissens.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nissens.R;
import com.nissens.adapter.CarTypeAdapter;
import com.nissens.annotation.ActivityFragmentInject;
import com.nissens.base.BaseActivity;
import com.nissens.bean.CarModelDataRequest;
import com.nissens.module.presenter.CarConditionPresenter;
import com.nissens.module.presenter.CarConditionPresenterImpl;
import com.nissens.module.view.SearchByCarView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PC-20160514 on 2016/10/17.
 */
@ActivityFragmentInject(
        contentViewId = R.layout.activity_search_by_car,
        toolbarTitle = R.string.search_by_car
)
public class SearchByCarActivity extends BaseActivity<CarConditionPresenter> implements SearchByCarView {
    @BindView(R.id.list)
    RecyclerView list;
    private CarTypeAdapter carTypeAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mPresenter = new CarConditionPresenterImpl(this);
        mPresenter.requestData(gson.toJson(new CarModelDataRequest()));
        carTypeAdapter=new CarTypeAdapter(this);
        list.setLayoutManager(new GridLayoutManager(this,2));
        list.setAdapter(carTypeAdapter);

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showResult(List<String> strings) {
 carTypeAdapter.addAll(strings);
    }

    @Override
    public void showError() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
