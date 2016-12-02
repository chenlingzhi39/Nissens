package com.nissens.module.presenter;

import com.nissens.base.BaseModel;
import com.nissens.base.BasePresenterImpl;
import com.nissens.bean.Car;
import com.nissens.bean.CarSingleResult;
import com.nissens.module.model.CarSingleModelImpl;
import com.nissens.module.view.CarSingleView;

/**
 * Created by PC-20160514 on 2016/9/28.
 */
public class CarSinglePresenterImpl extends BasePresenterImpl<CarSingleView,CarSingleResult> implements CarSinglePresenter {
    private BaseModel<CarSingleResult> carSingleModel;
    public CarSinglePresenterImpl(CarSingleView view) {
        super(view);
         carSingleModel=new CarSingleModelImpl();
    }

    @Override
    public void requestData(String requestData) {
        mSubscription=carSingleModel.requestSearchData(this,requestData);
    }

    @Override
    public void requestSuccess(CarSingleResult data) {
        mView.showResult(data);
    }

    @Override
    public void requestError(String msg) {
        super.requestError(msg);
        mView.showError();
    }
}
