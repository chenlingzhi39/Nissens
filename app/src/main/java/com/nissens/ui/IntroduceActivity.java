package com.nissens.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nissens.R;
import com.nissens.annotation.ActivityFragmentInject;
import com.nissens.base.BaseActivity;
import com.nissens.bean.IntroductionRequest;
import com.nissens.module.presenter.IntroductionPresenter;
import com.nissens.module.presenter.IntroductionPresenterImpl;
import com.nissens.module.view.IntroductionView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by PC-20160514 on 2016/10/17.
 */
@ActivityFragmentInject(
        contentViewId = R.layout.activity_introduction,
        toolbarTitle = R.string.company_introduction,
        menuId = R.menu.normal
)
public class IntroduceActivity extends BaseActivity<IntroductionPresenter> implements IntroductionView {
    @BindView(R.id.introduction)
    TextView tv_introduction;
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
        mPresenter = new IntroductionPresenterImpl(this);
        mPresenter.requestData(gson.toJson(new IntroductionRequest()));
    }

    @Override
    public void showEmpty() {
        empty.setVisibility(View.VISIBLE);
    }

    @Override
    public void showResult(String introduction) {
        tv_introduction.setText(introduction);
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
        mPresenter.requestData(gson.toJson(new IntroductionRequest()));
    }
}
