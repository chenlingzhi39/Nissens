package com.nissens.module.model;

import com.google.gson.Gson;
import com.nissens.app.MyApplication;
import com.nissens.base.BaseModel;
import com.nissens.bean.ApiService;
import com.nissens.bean.Car;
import com.nissens.bean.CarResult;
import com.nissens.bean.Image;
import com.nissens.bean.ImageResult;
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
 * Created by PC-20160514 on 2016/10/25.
 */

public class ImageModelImpl implements BaseModel<List<Image>> {
    @Inject
    ApiService apiService;
    public ImageModelImpl() {
        MyApplication.getComponent().inject(this);
    }

    @Override
    public Subscription requestSearchData(final RequestCallback<List<Image>> callback, String requestPara) {
        return apiService.queryOEPartImage(requestPara).subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()).subscribe(new Subscriber<ImageResult>() {
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
            public void onNext(ImageResult o) {
                if (null != o) {
                    Gson gson=new Gson();
                    Log.i("imageResult",gson.toJson(o));
                    if(o.getResult().equals("00"))
                    {ArrayList<Image> images=o.getData();
                        callback.requestSuccess(images);}
                    else callback.requestError(o.getDescription());
                } else {
                    callback.requestError("");
                }
            }
        });
    }
}
