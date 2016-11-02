package com.nissens.module.model;

import com.google.gson.Gson;
import com.nissens.app.MyApplication;
import com.nissens.base.BaseModel;
import com.nissens.bean.ApiService;
import com.nissens.bean.Car;
import com.nissens.bean.CarResult;
import com.nissens.callback.RequestCallback;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import android.util.Log;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by PC-20160514 on 2016/9/26.
 */
public class AdjustCarModelImpl implements BaseModel<List<Car>>{
    @Inject
    ApiService apiService;

    public AdjustCarModelImpl() {
        MyApplication.getComponent().inject(this);
    }
    @Override
    public Subscription requestSearchData(final RequestCallback<List<Car>> callback, String requestPara) {
        return apiService.queryBlendCarByBrandPartId(requestPara).subscribeOn(Schedulers.io()).observeOn(
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
                    Log.i("car_result",new Gson().toJson(o));
                    if(((CarResult)o).getResult().equals("00"))
                    {ArrayList<Car> cars=((CarResult)o).getData();
                    callback.requestSuccess(cars);}
                    else callback.requestError(((CarResult)o).getDescription());
                } else {
                    callback.requestError("");
                }
            }
        });
    }
}

