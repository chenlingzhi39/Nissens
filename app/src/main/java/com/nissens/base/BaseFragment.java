package com.nissens.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.google.gson.Gson;
import com.nissens.annotation.ActivityFragmentInject;

import java.net.UnknownHostException;

import rx.Subscription;

/**
 * Created by PC-20160514 on 2016/5/18.
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment {
    protected View fragmentRootView;
    protected int mContentViewId;
    public ProgressDialog progressDialog;
    public boolean is_first=true;
    protected T mPresenter;
    public Gson gson;
    public int page=1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        if (null == fragmentRootView) {
            if (getClass().isAnnotationPresent(ActivityFragmentInject.class)) {
                ActivityFragmentInject annotation = getClass()
                        .getAnnotation(ActivityFragmentInject.class);
                mContentViewId = annotation.contentViewId();
            } else {
                throw new RuntimeException(
                        "Class must add annotations of ActivityFragmentInitParams.class");
            }
            fragmentRootView = inflater.inflate(mContentViewId, container, false);
            gson=new Gson();
            initView(fragmentRootView);
        }
        return fragmentRootView;
    }
    protected abstract void initView(View fragmentRootView);
    public void toast(String msg){
        Toast.makeText(getActivity(),msg, Toast.LENGTH_SHORT).show();
    }
    public void toast(int id){
        Toast.makeText(getActivity(),getActivity().getString(id), Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.onResume();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
    }

}
