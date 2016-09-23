package com.nissens.module.presenter;

import com.nissens.base.BasePresenterImpl;
import com.nissens.bean.OEData;
import com.nissens.module.model.StraightSearchModel;
import com.nissens.module.model.StraightSearchModelImpl;
import com.nissens.view.StraightSearchView;

import java.util.List;
import java.util.Map;

/**
 * Created by PC-20160514 on 2016/9/22.
 */
public class StraightSearchPresenterImpl extends BasePresenterImpl<StraightSearchView,List<OEData>> implements StraightSearchPresenter{
    StraightSearchModel straightSearchModel;
    public StraightSearchPresenterImpl(StraightSearchView view) {
        super(view);
        straightSearchModel=new StraightSearchModelImpl();
    }

    @Override
    public void requestData(Map<String,String> map) {
        mSubscription=straightSearchModel.requestSearchData(this,map);
    }

    @Override
    public void requestSuccess(List<OEData> data) {
        if(data.size()==0)
            mView.showEmpty();
        else mView.showResult(data);
    }

    @Override
    public void requestError(String msg) {
        super.requestError(msg);
       mView.showError();
    }

}
