package com.nissens.module.model;

import com.nissens.app.MyApplication;
import com.nissens.base.BaseModel;
import com.nissens.bean.ApiService;
import com.nissens.bean.Car;
import com.nissens.bean.CarSingleResult;
import com.nissens.callback.RequestCallback;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by PC-20160514 on 2016/9/28.
 */
public class CarSingleModelImpl implements BaseModel<Car>{
    @Inject
    ApiService apiService;

    public CarSingleModelImpl() {
            MyApplication.getComponent().inject(this);
    }

    @Override
    public Subscription requestSearchData(final RequestCallback<Car> callback, final String requestPara) {
        return apiService.queryCarDataByLYVin(requestPara).subscribeOn(Schedulers.io()).observeOn(
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
                    CarSingleResult carSingleResult=(CarSingleResult) o;
                    if(carSingleResult.getResult().equals("1"))
                    callback.requestSuccess((Car) o);
                    else
                        callback.requestError(carSingleResult.getDescription());
                } else {
                    callback.requestError("");
                }
            }
        });
    };
    }
