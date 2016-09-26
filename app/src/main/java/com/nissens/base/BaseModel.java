package com.nissens.base;

import com.nissens.callback.RequestCallback;

import rx.Subscription;

/**
 * Created by PC-20160514 on 2016/9/26.
 */
public interface BaseModel<T>{
    Subscription requestSearchData(RequestCallback<T> callback, String requestPara);
}
