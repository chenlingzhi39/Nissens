package com.nissens.base.basePresenter;

import com.nissens.app.MyApplication;
import com.nissens.bean.ApiService;

import java.util.Map;


import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by PC-20160514 on 2016/9/18.
 */
public abstract class BasePresenter {
    @Inject
    ApiService mService;
    Subscription mSubscription;

    protected RequestMode mode = RequestMode.FRIST;
    public BasePresenter() {
        MyApplication.getComponent().inject(this);
    }
    public enum RequestMode {
        FRIST, LOAD_MORE, REFRESH
    }
    @SuppressWarnings("unchecked")
    public void requestDate(Map<String, String> params, RequestMode mode) {
        if (null == getObservable(params)) {
            throw new IllegalArgumentException("no Observable");
        }

        this.mode = mode;
        mSubscription = getObservable(params).subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()).subscribe(new Subscriber() {
            @Override
            public void onCompleted() {
                onFinish();
            }

            @Override
            public void onError(Throwable e) {
                onFail();
            }

            @Override
            public void onNext(Object o) {
                if (null != o) {
                    onAllSuccess(o);
                } else {
                    onFail();
                }
            }
        });
    }

    protected void onAllSuccess(Object o) {
    }

    protected ApiService getService() {
        return mService;
    }

    protected void onFinish() {

    }

    protected abstract void onFail();


    protected abstract Observable getObservable(Map<String, String> params);
}
