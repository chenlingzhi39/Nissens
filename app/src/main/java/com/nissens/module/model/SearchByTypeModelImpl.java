package com.nissens.module.model;
import android.util.Log;

import com.google.gson.Gson;
import com.nissens.app.MyApplication;
import com.nissens.base.BaseModel;
import com.nissens.bean.ApiService;
import com.nissens.bean.BrandSeriesXml;
import com.nissens.bean.BrandSeriesXmlResult;
import com.nissens.bean.OEData;
import com.nissens.bean.OEDataResult;
import com.nissens.callback.RequestCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.LoggingMXBean;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by PC-20160514 on 2016/9/28.
 */
public class SearchByTypeModelImpl implements BaseModel<List<BrandSeriesXml>>{
    @Inject
    ApiService apiService;
    public SearchByTypeModelImpl() {
        MyApplication.getComponent().inject(this);
    }

    @Override
    public Subscription requestSearchData(final RequestCallback<List<BrandSeriesXml>> callback, String requestPara) {
        return apiService.queryBrandSeries(requestPara).subscribeOn(Schedulers.io()).observeOn(
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
                    if(((BrandSeriesXmlResult)o).getResult().equals("00"))
                    {ArrayList<BrandSeriesXml> brandSeriesXmls=((BrandSeriesXmlResult)o).getData();
                        callback.requestSuccess(brandSeriesXmls);}
                    else callback.requestError(((BrandSeriesXmlResult)o).getDescription());
                } else {
                    callback.requestError("");
                }
            }
        });
    }
}
