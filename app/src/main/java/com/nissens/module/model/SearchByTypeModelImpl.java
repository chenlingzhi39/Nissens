package com.nissens.module.model;

import com.nissens.app.MyApplication;
import com.nissens.base.BaseModel;
import com.nissens.bean.ApiService;
import com.nissens.bean.BrandSeriesXml;
import com.nissens.bean.BrandSeriesXmlResult;
import com.nissens.callback.RequestCallback;

import java.util.ArrayList;
import java.util.List;

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
        return apiService.queryBrandSeriesXml(requestPara).subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()).subscribe(new Subscriber<BrandSeriesXmlResult>() {
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
            public void onNext(BrandSeriesXmlResult o) {
                if (null != o) {
                    if(o.getResult().equals("00"))
                    {ArrayList<BrandSeriesXml> brandSeriesXmls=o.getData();
                        callback.requestSuccess(brandSeriesXmls);}
                    else callback.requestError(o.getDescription());
                } else {
                    callback.requestError("");
                }
            }
        });
    }
}
