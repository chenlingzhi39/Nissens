package com.nissens.module.presenter;

import com.nissens.base.BaseModel;
import com.nissens.base.BasePresenterImpl;
import com.nissens.module.model.IntroduceModelImpl;
import com.nissens.module.view.IntroductionView;

/**
 * Created by PC-20160514 on 2016/10/17.
 */

public class IntroductionPresenterImpl extends BasePresenterImpl<IntroductionView,String> implements IntroductionPresenter {
   private BaseModel<String> introductionModel;
    public IntroductionPresenterImpl(IntroductionView view) {
        super(view);
        introductionModel=new IntroduceModelImpl();
    }

    @Override
    public void requestData(String requestData) {
        mSubscription=introductionModel.requestSearchData(this,requestData);
    }

    @Override
    public void requestError(String msg) {
        super.requestError(msg);
        mView.showError();
    }

    @Override
    public void requestSuccess(String data) {
        super.requestSuccess(data);
        mView.showResult(data);
    }
}
