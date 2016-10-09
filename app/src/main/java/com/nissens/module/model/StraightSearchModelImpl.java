package com.nissens.module.model;

import android.util.Log;

import com.nissens.app.MyApplication;
import com.nissens.base.BaseModel;
import com.nissens.bean.ApiService;
import com.nissens.bean.OEData;
import com.nissens.bean.OEDataResult;
import com.nissens.callback.RequestCallback;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by PC-20160514 on 2016/9/22.
 */
public class StraightSearchModelImpl implements BaseModel<List<OEData>> {
    @Inject
    ApiService apiService;

    public StraightSearchModelImpl() {
        MyApplication.getComponent().inject(this);
    }

    @Override
    public Subscription requestSearchData(final RequestCallback<List<OEData>> callback, String requestPara) {
        return apiService.queryBrandPartOEData(requestPara).subscribeOn(Schedulers.io()).observeOn(
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
                    if(((OEDataResult)o).getResult().equals("00"))
                    {ArrayList<OEData> oeDatas=((OEDataResult)o).getData();
                   callback.requestSuccess(oeDatas);}
                    else callback.requestError(((OEDataResult)o).getDescription());
                } else {
                    callback.requestError("");
                }
            }
        });
    }
}
