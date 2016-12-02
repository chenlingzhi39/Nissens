package com.nissens.module.model;

import com.nissens.app.MyApplication;
import com.nissens.base.BaseModel;
import com.nissens.bean.ApiService;
import com.nissens.bean.Car;
import com.nissens.bean.CarResult;
import com.nissens.callback.RequestCallback;

import java.util.ArrayList;
import java.util.List;
import android.util.Log;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by PC-20160514 on 2016/10/17.
 */

public class CarsModelImpl implements BaseModel<List<Car>> {
    @Inject
    ApiService apiService;

    public CarsModelImpl() {
        MyApplication.getComponent().inject(this);
    }
    @Override
    public Subscription requestSearchData(final RequestCallback<List<Car>> callback, String requestPara) {
        return apiService.queryBlendCarModelData(requestPara).subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()).subscribe(new Subscriber<CarResult>() {
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
            public void onNext(CarResult o) {
                if (null != o) {
                    Log.i("count",o.getAllItemsCount());
                    if(o.getResult().equals("00"))
                    {ArrayList<Car> cars=o.getData();
                        callback.requestSuccess(cars);}
                    else callback.requestError(o.getDescription());
                } else {
                    callback.requestError("");
                }
            }
        });
    }
}
