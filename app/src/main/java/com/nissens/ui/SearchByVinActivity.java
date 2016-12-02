package com.nissens.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nissens.R;
import com.nissens.annotation.ActivityFragmentInject;
import com.nissens.base.BaseActivity;
import com.nissens.bean.CarSingleResult;
import com.nissens.bean.VinRequest;
import com.nissens.module.presenter.CarSinglePresenter;
import com.nissens.module.presenter.CarSinglePresenterImpl;
import com.nissens.module.view.CarSingleView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by PC-20160514 on 2016/9/23.
 */
@ActivityFragmentInject(
        contentViewId = R.layout.activity_search_by_vin,
        toolbarTitle = R.string.search_by_vin,
        menuId = R.menu.normal
)
public class SearchByVinActivity extends BaseActivity<CarSinglePresenter> implements CarSingleView {
    @BindView(R.id.search)
    SearchView search;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.empty)
    TextView empty;
    @BindView(R.id.error)
    TextView error;
    VinRequest vinRequest;
    @BindView(R.id.factory)
    TextView factory;
    @BindView(R.id.brand)
    TextView brand;
    @BindView(R.id.car_model)
    TextView carModel;
    @BindView(R.id.year)
    TextView year;
    @BindView(R.id.engine_model)
    TextView engineModel;
    @BindView(R.id.name_of_sales)
    TextView nameOfSales;
    @BindView(R.id.content)
    LinearLayout content;
    CarSingleResult carSingleResult;

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
                if (query.length() == 17) {
                    vinRequest = new VinRequest(query);
                    Log.i("request", gson.toJson(vinRequest));
                    mPresenter.requestData(gson.toJson(vinRequest));
                } else {
                    toast("vin码必须为17位！");
                }
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
        error.setVisibility(View.VISIBLE);
    }

    @Override
    public void showResult(CarSingleResult car) {
        carSingleResult = car;
        progressBar.setVisibility(View.GONE);
        content.setVisibility(View.VISIBLE);
        factory.setText(getString(R.string.factory) + ":" + car.getCarFactoryName());
        brand.setText(getString(R.string.brand) + ":" + car.getCarBrand());
        carModel.setText(getString(R.string.car_model) + ":" + car.getCarModel());
        year.setText(getString(R.string.year) + ":" + car.getYear());
        engineModel.setText(getString(R.string.engine_model) + ":" + car.getEngineModel());
        nameOfSales.setText(getString(R.string.name_of_sales) + ":" + car.getNameOfSales());
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        error.setVisibility(View.GONE);
        content.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
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
                Toast.makeText(SearchByVinActivity.this, "\nonExpand", Toast.LENGTH_LONG).show();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Toast.makeText(SearchByVinActivity.this, "Collapse", Toast.LENGTH_LONG).show();
                return true;
            }
        });*/
        return super.onCreateOptionsMenu(menu);
    }


    @OnClick({R.id.error, R.id.content,R.id.select_type})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.error:
                error.setVisibility(View.GONE);
                mPresenter.requestData(gson.toJson(vinRequest));
                break;
            case R.id.content:

                break;
            case R.id.select_type:
                Intent intent = new Intent(SearchByVinActivity.this, SearchByTypeActivity.class);
                intent.putExtra("mode", "car");
                intent.putExtra("label", carSingleResult.getCarFactoryName());
                startActivity(intent);
                finish();
                break;
        }
    }
}
