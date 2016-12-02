package com.nissens.module.model;

import android.util.Log;

import com.google.gson.Gson;
import com.nissens.app.MyApplication;
import com.nissens.base.BaseModel;
import com.nissens.bean.ApiService;
import com.nissens.bean.Car;
import com.nissens.bean.CarModelDataResult;
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

public class CarConditionModelImpl implements BaseModel<Car> {
    @Inject
    ApiService apiService;

    public CarConditionModelImpl() {
        MyApplication.getComponent().inject(this);
    }

    @Override
    public Subscription requestSearchData(final RequestCallback<Car> callback, String requestPara) {
        return apiService.queryCarCondition(requestPara).subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()).subscribe(new Subscriber<CarModelDataResult>() {
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
            public void onNext(CarModelDataResult o) {
                if (null != o) {
                    System.out.print(o.getResult());
                    Gson gson=new Gson();
                    Log.i("data",gson.toJson(o));
                    if (o.getResult().equals("00")) {
                        ArrayList<Car> cars = o.getData();
                        Car car=cars.get(0);
                        callback.requestSuccess(car);
                    } else callback.requestError(o.getDescription());
                } else {
                    callback.requestError("");
                }
            }
        });
    }
}
