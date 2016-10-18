package com.nissens.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nissens.R;
import com.nissens.adapter.CarAdapter;
import com.nissens.adapter.RecyclerArrayAdapter;
import com.nissens.annotation.ActivityFragmentInject;
import com.nissens.base.BaseFragment;
import com.nissens.bean.Car;
import com.nissens.bean.CarRequest;
import com.nissens.bean.OEDataRequest;
import com.nissens.module.presenter.AdjustCarPresenter;
import com.nissens.module.presenter.AdjustCarPresenterImpl;
import com.nissens.module.view.AdjustCarView;
import com.nissens.widget.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by PC-20160514 on 2016/9/26.
 */
@ActivityFragmentInject(
        contentViewId = R.layout.fragment_adjust_car
)
public class AdjustCarFragment extends BaseFragment<AdjustCarPresenter> implements AdjustCarView {
    @BindView(R.id.list)
    RecyclerView list;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.empty)
    TextView empty;
    @BindView(R.id.error)
    TextView error;
    private CarAdapter carAdapter;
    private CarRequest carRequest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initView(View fragmentRootView) {
        ButterKnife.bind(this, fragmentRootView);
        carAdapter = new CarAdapter(getActivity(),false);
        carAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent=new Intent(getActivity(),CarInfoActivity.class);
                intent.putExtra("car",carAdapter.getData().get(position));
                startActivity(intent);
            }
        });
        list.setAdapter(carAdapter);
        list.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL_LIST));
        list.setLayoutManager(new LinearLayoutManager(getActivity()));
        mPresenter = new AdjustCarPresenterImpl(this);
        carRequest = new CarRequest(getArguments().getString("factory_id"),"15", page+"");
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
                            carRequest = new CarRequest(getArguments().getString("factory_id"),"15", page + "");
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
