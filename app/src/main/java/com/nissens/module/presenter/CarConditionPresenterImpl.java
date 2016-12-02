package com.nissens.module.presenter;

import com.nissens.base.BaseModel;
import com.nissens.base.BasePresenterImpl;
import com.nissens.bean.Car;
import com.nissens.module.model.CarConditionModelImpl;
import com.nissens.module.view.SearchByCarView;

/**
 * Created by PC-20160514 on 2016/10/17.
 */

public class CarConditionPresenterImpl extends BasePresenterImpl<SearchByCarView,Car> implements CarConditionPresenter {
   private BaseModel<Car> carConditionModel;
    public CarConditionPresenterImpl(SearchByCarView view) {
        super(view);
        carConditionModel=new CarConditionModelImpl();
    }

    @Override
    public void requestData(String requestData) {
     mSubscription=carConditionModel.requestSearchData(this,requestData);
    }
    @Override
    public void requestError(String msg) {
        super.requestError(msg);
        mView.showError();
    }

    @Override
    public void requestSuccess(Car car) {
        mView.showResult(car);
    }
}
