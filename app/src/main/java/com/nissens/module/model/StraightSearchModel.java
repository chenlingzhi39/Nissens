package com.nissens.module.model;

import com.nissens.callback.RequestCallback;

import java.util.Map;

import rx.Subscription;

/**
 * Created by PC-20160514 on 2016/9/22.
 */
public interface StraightSearchModel<T> {
    Subscription requestSearchData(RequestCallback<T> callback, String requestPara);
}
