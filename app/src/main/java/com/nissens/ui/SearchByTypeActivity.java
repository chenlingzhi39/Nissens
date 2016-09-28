package com.nissens.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nissens.R;
import com.nissens.annotation.ActivityFragmentInject;
import com.nissens.base.BaseActivity;
import com.nissens.bean.BrandSeriesXml;
import com.nissens.module.presenter.SearchByTypePresenter;
import com.nissens.module.presenter.SearchByTypePresenterImpl;
import com.nissens.module.view.SearchByTypeView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by PC-20160514 on 2016/9/23.
 */
@ActivityFragmentInject(
        contentViewId = R.layout.activity_search_by_type,
        toolbarTitle = R.string.search_by_type
)
public class SearchByTypeActivity extends BaseActivity implements SearchByTypeView {
    SearchByTypePresenter searchByTypePresenter;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.empty)
    TextView empty;
    @BindView(R.id.error)
    TextView error;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchByTypePresenter = new SearchByTypePresenterImpl(this);
    }

    @Override
    public void showEmpty() {
        empty.setVisibility(View.VISIBLE);
    }

    @Override
    public void showResult(List<BrandSeriesXml> brandSeriesXmls) {

    }

    @Override
    public void showError() {
       error.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress() {
        empty.setVisibility(View.GONE);
        error.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }
}
