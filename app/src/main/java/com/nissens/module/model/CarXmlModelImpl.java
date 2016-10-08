package com.nissens.module.model;

import android.util.Log;

import com.nissens.app.MyApplication;
import com.nissens.base.BaseModel;
import com.nissens.bean.ApiService;
import com.nissens.bean.BrandSeriesXml;
import com.nissens.bean.BrandSeriesXmlResult;
import com.nissens.bean.CarBrandXmlResult;
import com.nissens.callback.RequestCallback;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by PC-20160514 on 2016/9/30.
 */

public class CarXmlModelImpl implements BaseModel<String> {
    @Inject
    ApiService apiService;

    public CarXmlModelImpl() {
        MyApplication.getComponent().inject(this);
    }

    @Override
    public Subscription requestSearchData(final RequestCallback<String> callback, String requestPara) {
        return apiService.queryCarBrandXml(requestPara).subscribeOn(Schedulers.io()).observeOn(
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
                    if(((CarBrandXmlResult)o).getResult().equals("00"))
                    {  Log.i("showResult", ((CarBrandXmlResult)o).getCarBrandXml());
                        callback.requestSuccess(((CarBrandXmlResult)o).getCarBrandXml());}
                    else callback.requestError(((BrandSeriesXmlResult)o).getDescription());
                } else {
                    callback.requestError("");
                }
            }
        });
    }
}
