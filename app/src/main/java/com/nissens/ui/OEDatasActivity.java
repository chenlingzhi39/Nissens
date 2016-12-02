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
import com.nissens.adapter.OEDataAdapter;
import com.nissens.adapter.RecyclerArrayAdapter;
import com.nissens.annotation.ActivityFragmentInject;
import com.nissens.base.BaseActivity;
import com.nissens.bean.OEData;
import com.nissens.bean.OEDataRequest;
import com.nissens.module.presenter.StraightSearchPresenter;
import com.nissens.module.presenter.StraightSearchPresenterImpl;
import com.nissens.module.view.StraightSearchView;
import com.nissens.widget.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/10/9.
 */
@ActivityFragmentInject(
        contentViewId = R.layout.activity_oedatas,
        toolbarTitle = R.string.search_result,
        menuId = R.menu.normal
)
public class OEDatasActivity extends BaseActivity<StraightSearchPresenter> implements StraightSearchView {
    @BindView(R.id.result_list)
    RecyclerView resultList;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.empty)
    TextView empty;
    @BindView(R.id.error)
    TextView error;
    private OEDataAdapter oeDataAdapter;
    private OEDataRequest oeDataRequest;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        oeDataAdapter = new OEDataAdapter(this);
        oeDataAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(OEDatasActivity.this, InfoActivity.class);
                intent.putExtra("oeData", oeDataAdapter.getData().get(position));
                startActivity(intent);
            }
        });
        resultList.setAdapter(oeDataAdapter);
        resultList.setLayoutManager(new LinearLayoutManager(this));
        resultList.addItemDecoration(new DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL_LIST));
        mPresenter = new StraightSearchPresenterImpl(this);
        Log.i("label",getIntent().getStringExtra("label"));
        Log.i("series",getIntent().getStringExtra("series"));
        oeDataRequest = new OEDataRequest("15", page + "", getIntent().getStringExtra("label"), getIntent().getStringExtra("series"));
        mPresenter.requestData(gson.toJson(oeDataRequest));
    }

    @Override
    public void showEmpty() {
        if (oeDataAdapter.getCount() == 0) {
            oeDataAdapter.clear();
            empty.setVisibility(View.VISIBLE);
        } else oeDataAdapter.stopMore();
    }

    @Override
    public void showResult(final List<OEData> oeDatas) {
        if (is_first) {
            oeDataAdapter.setError(R.layout.view_more_error).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (oeDataAdapter.getCount() > 0)
                        oeDataAdapter.resumeMore();
                }
            });
            oeDataAdapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnLoadMoreListener() {
                @Override
                public void onLoadMore() {
                    if (oeDataAdapter.getCount() > 0) {
                        if(oeDatas.size()>=15)
                        {page += 1;
                        OEDataRequest oeDataRequest = new OEDataRequest("15", page + "", getSupportActionBar().getTitle().toString());
                        mPresenter.requestData(gson.toJson(oeDataRequest));}else oeDataAdapter.stopMore();
                    }
                }
            });
            oeDataAdapter.setNoMore(R.layout.view_nomore);
            is_first = false;
        }
        resultList.setVisibility(View.VISIBLE);
        empty.setVisibility(View.GONE);
        error.setVisibility(View.GONE);
        oeDataAdapter.addAll(oeDatas);
    }

    @Override
    public void showError() {
        if (oeDataAdapter.getCount() == 0) {
            oeDataAdapter.clear();
            error.setVisibility(View.VISIBLE);
        } else oeDataAdapter.pauseMore();
    }

    @Override
    public void showProgress() {
        if (oeDataAdapter.getCount() == 0) {
            error.setVisibility(View.GONE);
            empty.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            resultList.setVisibility(View.GONE);
        }

    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @OnClick(R.id.error)
    public void onClick() {
        error.setVisibility(View.GONE);
        mPresenter.requestData(gson.toJson(oeDataRequest));
    }
}
