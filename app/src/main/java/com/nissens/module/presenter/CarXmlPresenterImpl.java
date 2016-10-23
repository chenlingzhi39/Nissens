package com.nissens.module.presenter;

import com.nissens.base.BaseModel;
import com.nissens.base.BasePresenterImpl;
import com.nissens.module.model.CarXmlModelImpl;
import com.nissens.module.view.CarXmlView;

/**
 * Created by PC-20160514 on 2016/9/30.
 */

public class CarXmlPresenterImpl extends BasePresenterImpl<CarXmlView,String> implements CarXmlPresenter {
    BaseModel CarXmlModel;

    public CarXmlPresenterImpl(CarXmlView view) {
        super(view);
        CarXmlModel=new CarXmlModelImpl();
    }

    @Override
    public void requestData(String requestData) {
      CarXmlModel.requestSearchData(this,requestData);
    }

    @Override
    public void requestError(String msg) {
        super.requestError(msg);
        mView.showError();
    }

    @Override
    public void requestSuccess(String data) {
       mView.showResult(data);
    }
}
