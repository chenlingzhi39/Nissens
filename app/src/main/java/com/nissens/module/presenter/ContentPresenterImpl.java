package com.nissens.module.presenter;

import com.nissens.base.BaseModel;
import com.nissens.base.BasePresenterImpl;
import com.nissens.bean.CategoryPropertyName;
import com.nissens.module.model.ContentModelImpl;
import com.nissens.module.view.ContentView;

import java.util.List;

/**
 * Created by Administrator on 2016/12/1.
 */

public class ContentPresenterImpl extends BasePresenterImpl<ContentView,Object> implements ContentPresenter {
    private BaseModel<Object> contentModel;
    public ContentPresenterImpl(ContentView view) {
        super(view);
        contentModel=new ContentModelImpl();
    }

    @Override
    public void requestData(String requestData) {
        mSubscription=contentModel.requestSearchData(this,requestData);
    }

    @Override
    public void requestError(String msg) {
        super.requestError(msg);
        mView.showError();
    }

    @Override
    public void requestSuccess(Object data) {
        if(((List)data).size()==0)
            mView.showEmpty();
        else mView.showResult(data);
    }

}
