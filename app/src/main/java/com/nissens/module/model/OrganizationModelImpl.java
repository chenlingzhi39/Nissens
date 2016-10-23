package com.nissens.module.model;

import com.nissens.app.MyApplication;
import com.nissens.base.BaseModel;
import com.nissens.bean.ApiService;
import com.nissens.bean.BrandOrganization;
import com.nissens.bean.BrandOrganizationResult;
import com.nissens.callback.RequestCallback;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by PC-20160514 on 2016/10/7.
 */

public class OrganizationModelImpl implements BaseModel<List<BrandOrganization>> {
    @Inject
    ApiService apiService;

    public OrganizationModelImpl() {
        MyApplication.getComponent().inject(this);
    }

    @Override
    public Subscription requestSearchData(final RequestCallback<List<BrandOrganization>> callback, String requestPara) {
        return apiService.queryBrandOrganization(requestPara).subscribeOn(Schedulers.io()).observeOn(
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
                    if(((BrandOrganizationResult)o).getResult().equals("00"))
                    {ArrayList<BrandOrganization> brandOrganizations=((BrandOrganizationResult)o).getData();
                        callback.requestSuccess(brandOrganizations);}
                    else callback.requestError(((BrandOrganizationResult)o).getDescription());
                } else {
                    callback.requestError("");
                }
            }
        });
    }
}
