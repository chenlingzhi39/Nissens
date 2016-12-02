package com.nissens.module.model;

import com.nissens.app.MyApplication;
import com.nissens.base.BaseModel;
import com.nissens.bean.ApiService;
import com.nissens.bean.CategoryPropertyColumn;
import com.nissens.bean.CategoryPropertyColumnResult;
import com.nissens.bean.CategoryPropertyContentResult;
import com.nissens.callback.RequestCallback;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/1.
 */

public class ContentModelImpl implements BaseModel<Object> {
    @Inject
    ApiService apiService;

    public ContentModelImpl() {
        MyApplication.getComponent().inject(this);
    }

    @Override
    public Subscription requestSearchData(final RequestCallback<Object> callback, String requestPara) {
        Observable.merge(apiService.queryCategoryPropertyColumn(requestPara),apiService.queryCategoryPropertyContent(requestPara))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Object>() {
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
                            if(o instanceof CategoryPropertyColumnResult)
                            {if(((CategoryPropertyColumnResult)o).getResult().equals("00"))
                            {callback.requestSuccess(((CategoryPropertyColumnResult)o).getData());}
                            else callback.requestError(((CategoryPropertyColumnResult)o).getDescription());}
                            else{
                                if(((CategoryPropertyContentResult)o).getResult().equals("00"))
                                {callback.requestSuccess(((CategoryPropertyContentResult)o).getData());}
                                else callback.requestError(((CategoryPropertyContentResult)o).getDescription());
                            }

                        } else {
                            callback.requestError("");
                        }
                    }
                });

        return null;
    }
}
