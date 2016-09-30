package com.nissens.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nissens.R;
import com.nissens.annotation.ActivityFragmentInject;
import com.nissens.base.BaseActivity;
import com.nissens.bean.Car;
import com.nissens.bean.VinRequest;
import com.nissens.module.presenter.CarSinglePresenter;
import com.nissens.module.presenter.CarSinglePresenterImpl;
import com.nissens.module.view.CarSingleView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PC-20160514 on 2016/9/23.
 */
@ActivityFragmentInject(
        contentViewId = R.layout.activity_search_by_car,
        toolbarTitle = R.string.search_by_car
)
public class SearchByCarActivity extends BaseActivity<CarSinglePresenter> implements CarSingleView {
    @BindView(R.id.search)
    SearchView search;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.empty)
    TextView empty;
    @BindView(R.id.error)
    TextView error;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        search.setQueryHint(getString(R.string.please_input_vin));
        search.onActionViewExpanded();
        mPresenter = new CarSinglePresenterImpl(this);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                VinRequest vinRequest = new VinRequest("");
                mPresenter.requestData(gson.toJson(vinRequest));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void showError() {
        error.setVisibility(View.GONE);
        empty.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showResult(Car car) {

    }

    @Override
    public void showProgress() {
    progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {progressBar.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       /* MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);
        MenuItem menuItem = menu.findItem(R.id.search);//在菜单中找到对应控件的item
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setQueryHint(getString(R.string.please_input_vin));
        searchView.performClick();
        MenuItemCompat.setOnActionExpandListener(menuItem, new MenuItemCompat.OnActionExpandListener() {//设置打开关闭动作监听
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                Toast.makeText(SearchByCarActivity.this, "\nonExpand", Toast.LENGTH_LONG).show();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Toast.makeText(SearchByCarActivity.this, "Collapse", Toast.LENGTH_LONG).show();
                return true;
            }
        });*/
        return super.onCreateOptionsMenu(menu);
    }
}
