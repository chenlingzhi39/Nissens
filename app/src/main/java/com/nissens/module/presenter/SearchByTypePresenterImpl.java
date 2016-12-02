package com.nissens.module.presenter;

import com.nissens.base.BaseModel;
import com.nissens.base.BasePresenterImpl;
import com.nissens.bean.BrandSeriesXml;
import com.nissens.module.model.SearchByTypeModelImpl;
import com.nissens.module.view.SearchByTypeView;

import java.util.List;

/**
 * Created by PC-20160514 on 2016/9/28.
 */
public class SearchByTypePresenterImpl extends BasePresenterImpl<SearchByTypeView,List<BrandSeriesXml>> implements SearchByTypePresenter{
   private BaseModel<List<BrandSeriesXml>> searchByTypeModel;
    public SearchByTypePresenterImpl(SearchByTypeView view) {
        super(view);
        searchByTypeModel=new SearchByTypeModelImpl();
    }

    @Override
    public void requestData(String requestData) {
        mSubscription=searchByTypeModel.requestSearchData(this,requestData);
    }

    @Override
    public void requestError(String msg) {
        super.requestError(msg);
        mView.showError();
    }

    @Override
    public void requestSuccess(List<BrandSeriesXml> data) {
        if(data.size()==0)
            mView.showEmpty();
        else mView.showResult(data);
    }
}
