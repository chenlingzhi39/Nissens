package com.nissens.module.presenter;

import com.nissens.base.BaseModel;
import com.nissens.base.BasePresenter;
import com.nissens.base.BasePresenterImpl;
import com.nissens.bean.Car;
import com.nissens.module.model.CarConditionModelImpl;
import com.nissens.module.view.SearchByCarView;

import java.util.List;

/**
 * Created by PC-20160514 on 2016/10/17.
 */

public class CarConditionPreasenterImpl extends BasePresenterImpl<SearchByCarView,List<Car>> implements CarConditionPresenter {
BaseModel carConditionModel;
    public CarConditionPreasenterImpl(SearchByCarView view) {
        super(view);
        carConditionModel=new CarConditionModelImpl();
    }

    @Override
    public void requestData(String requestData) {
     carConditionModel.requestSearchData(this,requestData);
    }
}
