package com.nissens.module.model;

import com.nissens.app.MyApplication;
import com.nissens.base.BaseModel;
import com.nissens.bean.ApiService;
import com.nissens.bean.BrandOrganization;
import com.nissens.bean.BrandOrganizationResult;
import com.nissens.bean.IntroductionResult;
import com.nissens.callback.RequestCallback;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by PC-20160514 on 2016/10/17.
 */

public class IntroduceModelImpl implements BaseModel {
    @Inject
    ApiService apiService;

    public IntroduceModelImpl() {
        MyApplication.getComponent().inject(this);
    }

    @Override
    public Subscription requestSearchData(final RequestCallback callback, String requestPara) {
        return apiService.queryCompanyIntroduction(requestPara).subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()).subscribe(new Subscriber() {
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
            public void onNext(Object o) {
                if (null != o) {
                    if(((IntroductionResult)o).getResult().equals("00"))
                    {
                        callback.requestSuccess(((IntroductionResult)o).getCompanyIntroduction());}
                    else callback.requestError(((IntroductionResult)o).getDescription());
                } else {
                    callback.requestError("");
                }
            }
        });
    }
}
