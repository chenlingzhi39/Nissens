package com.nissens.presenter;

import com.nissens.base.BasePresenter;
import com.nissens.bean.OEDataResult;
import com.nissens.view.StraightSearchView;

import java.util.Map;

import rx.Observable;

/**
 * Created by PC-20160514 on 2016/9/21.
 */
public class StraightSearchPresenter extends BasePresenter{
StraightSearchView straightSearchView;

    public StraightSearchPresenter(StraightSearchView straightSearchView) {
        super();
        this.straightSearchView = straightSearchView;
    }

    @Override
    protected Observable getObservable(Map<String, String> params) {
       return getService().queryOriginalPartOEData(params);
    }

    @Override
    protected void onAllSuccess(Object o) {
        OEDataResult result=(OEDataResult)o;
    if(result.getResult().equals("1")){
        straightSearchView.showResult(result.getData());
    }else straightSearchView.showEmptyView("");
    }

    @Override
    protected void onFail() {
   straightSearchView.showToastError();
    }
}
