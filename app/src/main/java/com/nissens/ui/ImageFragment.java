package com.nissens.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nissens.R;
import com.nissens.adapter.ImageAdapter;
import com.nissens.adapter.RecyclerArrayAdapter;
import com.nissens.annotation.ActivityFragmentInject;
import com.nissens.base.BaseFragment;
import com.nissens.bean.Image;
import com.nissens.bean.ImageRequest;
import com.nissens.module.presenter.ImagePresenter;
import com.nissens.module.presenter.ImagePresenterImpl;
import com.nissens.module.view.OEPartImageView;

import java.util.List;
import android.util.Log;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by PC-20160514 on 2016/9/26.
 */
@ActivityFragmentInject(
        contentViewId = R.layout.fragment_image
)
public class ImageFragment extends BaseFragment<ImagePresenter> implements OEPartImageView {


    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.empty)
    TextView empty;
    @BindView(R.id.error)
    TextView error;
    @BindView(R.id.image_list)
    RecyclerView imageList;
    private ImageAdapter imageAdapter;

    @Override
    protected void initView(View fragmentRootView) {
        ButterKnife.bind(this, fragmentRootView);
        imageAdapter = new ImageAdapter(getActivity());
        imageList.setLayoutManager(new LinearLayoutManager(getActivity()));
        imageList.setAdapter(imageAdapter);
        imageAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startActivity(new Intent(getActivity(), ImageActivity.class).putExtra("path", imageAdapter.getItem(position).getOEPartImagePath()));
            }
        });
        mPresenter = new ImagePresenterImpl(this);
        Log.i("request",gson.toJson(new ImageRequest(getArguments().getString("id"))));
        mPresenter.requestData(gson.toJson(new ImageRequest(getArguments().getString("id"))));
    }

    @Override
    public void showEmpty() {
        empty.setVisibility(View.VISIBLE);
    }

    @Override
    public void showResult(List<Image> images) {
        error.setVisibility(View.GONE);
        imageAdapter.addAll(images);
    }

    @Override
    public void showError() {
        error.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress() {
        if(imageAdapter.getCount()==0)
        {error.setVisibility(View.GONE);
        empty.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);}
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @OnClick(R.id.error)
    public void onClick() {
        error.setVisibility(View.GONE);
        mPresenter.requestData(gson.toJson(new ImageRequest(getArguments().getString("id"))));
    }
}
