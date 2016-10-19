package com.nissens.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nissens.R;
import com.nissens.adapter.CarAdapter;
import com.nissens.adapter.RecyclerArrayAdapter;
import com.nissens.annotation.ActivityFragmentInject;
import com.nissens.base.BaseActivity;
import com.nissens.bean.Car;
import com.nissens.bean.CarConditionRequest;
import com.nissens.bean.CarModelDataRequest;
import com.nissens.module.presenter.CarsPresenter;
import com.nissens.module.presenter.CarsPresenterImpl;
import com.nissens.module.view.CarsView;
import com.nissens.widget.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by PC-20160514 on 2016/9/28.
 */
@ActivityFragmentInject(
        contentViewId = R.layout.activity_cars,
        toolbarTitle = R.string.please_select_specific_car
)
public class CarsActivity extends BaseActivity<CarsPresenter> implements CarsView {
    @BindView(R.id.list)
    RecyclerView list;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.empty)
    TextView empty;
    @BindView(R.id.error)
    TextView error;
    private CarAdapter carAdapter;
    private CarModelDataRequest carRequest;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        carAdapter = new CarAdapter(this,true);
        carAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                /*Intent intent=new Intent(CarsActivity.this,CarInfoActivity.class);
                intent.putExtra("car",carAdapter.getData().get(position));
                startActivity(intent);*/
                Intent intent=new Intent(CarsActivity.this,SearchByTypeActivity.class);
                intent.putExtra("mode","car");
                intent.putExtra("label",carAdapter.getItem(position).getCarFactoryName());
                startActivity(intent);
                finish();
            }
        });
        list.setAdapter(carAdapter);
        list.addItemDecoration(new DividerItemDecoration(
               this, DividerItemDecoration.VERTICAL_LIST));
        list.setLayoutManager(new LinearLayoutManager(this));
        mPresenter = new CarsPresenterImpl(this);
        carRequest = new CarModelDataRequest(getIntent().getStringExtra("factory")
                ,getIntent().getStringExtra("brand")
                ,getIntent().getStringExtra("series")
                ,getIntent().getStringExtra("group")
                ,getIntent().getStringExtra("displacement")
                ,getIntent().getStringExtra("gear_box_type")
                ,getIntent().getStringExtra("year"),"15",page+"");
        carRequest.setUserID("MobileApp");
        Log.i("factory",getIntent().getStringExtra("factory"));
        Log.i("brand",getIntent().getStringExtra("brand"));
        Log.i("displacement",getIntent().getStringExtra("displacement"));
        Log.i("year",getIntent().getStringExtra("year"));
        Log.i("request",gson.toJson(carRequest));
        mPresenter.requestData(gson.toJson(carRequest));
    }

    @Override
    public void showEmpty() {
        if (carAdapter.getCount() == 0) {
            carAdapter.clear();
            empty.setVisibility(View.VISIBLE);
        } else carAdapter.stopMore();
    }


    @Override
    public void showError() {
        if (carAdapter.getCount() == 0) {
            carAdapter.clear();
            error.setVisibility(View.VISIBLE);
        } else carAdapter.pauseMore();
    }

    @Override
    public void showResult(List<Car> carList) {
        Log.i("showResult", carList.size() + "");
        if (is_first) {
            carAdapter.setError(R.layout.view_more_error).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (carAdapter.getCount() > 0)
                        carAdapter.resumeMore();
                }
            });
            carAdapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnLoadMoreListener() {
                @Override
                public void onLoadMore() {
                    if (carAdapter.getCount() > 0) {
                        if (carAdapter.getCount() > 15) {
                            page += 1;
                            carRequest = new CarModelDataRequest(getIntent().getStringExtra("factory")
                                    ,getIntent().getStringExtra("brand")
                                    ,getIntent().getStringExtra("series")
                                    ,getIntent().getStringExtra("group")
                                    ,getIntent().getStringExtra("displacement")
                                    ,getIntent().getStringExtra("gear_box_type")
                                    ,getIntent().getStringExtra("year"),"15",page+"");
                            carRequest.setUserID("MobileApp");
                            mPresenter.requestData(gson.toJson(carRequest));
                        } else carAdapter.stopMore();
                    }
                }
            });
            carAdapter.setNoMore(R.layout.view_nomore);
            is_first = false;
        }
        list.setVisibility(View.VISIBLE);
        empty.setVisibility(View.GONE);
        error.setVisibility(View.GONE);
        carAdapter.addAll(carList);
    }

    @Override
    public void showProgress() {
        if (carAdapter.getCount() == 0) {
            error.setVisibility(View.GONE);
            empty.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            list.setVisibility(View.GONE);
        }
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @OnClick(R.id.error)
    public void onClick() {
        mPresenter.requestData(gson.toJson(carRequest));
    }
}
