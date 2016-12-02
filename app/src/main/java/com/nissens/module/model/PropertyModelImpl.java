package com.nissens.module.model;

import android.util.Log;

import com.google.gson.Gson;
import com.nissens.app.MyApplication;
import com.nissens.base.BaseModel;
import com.nissens.bean.ApiService;
import com.nissens.bean.BrandOrganization;
import com.nissens.bean.BrandOrganizationResult;
import com.nissens.bean.CategoryPropertyName;
import com.nissens.bean.CategoryPropertyNameResult;
import com.nissens.callback.RequestCallback;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/30.
 */

public class PropertyModelImpl implements BaseModel<List<CategoryPropertyName>> {
    @Inject
    ApiService apiService;

    public PropertyModelImpl() {
        MyApplication.getComponent().inject(this);
    }
    @Override
    public Subscription requestSearchData(final RequestCallback<List<CategoryPropertyName>> callback, String requestPara) {
        return apiService.queryCategoryPropertyName(requestPara).subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()).subscribe(new Subscriber<CategoryPropertyNameResult>() {
            @Override
            public void onStart() {
                callback.beforeRequest();
            }

            @Override
            public void onCompleted() {
                callback.requestComplete();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                callback.requestError(e.toString());
            }

            @Override
            public void onNext(CategoryPropertyNameResult o) {
                if (null != o) {
                    if(o.getResult().equals("00"))
                    {callback.requestSuccess(o.getData());}
                    else callback.requestError(o.getDescription());
                } else {
                    callback.requestError("");
                }
            }});
    }
}
