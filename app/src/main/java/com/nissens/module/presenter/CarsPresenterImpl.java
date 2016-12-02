package com.nissens.module.presenter;

import com.nissens.base.BaseModel;
import com.nissens.base.BasePresenterImpl;
import com.nissens.bean.Car;
import com.nissens.module.model.CarsModelImpl;
import com.nissens.module.view.CarsView;

import java.util.List;

/**
 * Created by PC-20160514 on 2016/10/18.
 */

public class CarsPresenterImpl extends BasePresenterImpl<CarsView,List<Car>> implements CarsPresenter{
    private BaseModel<List<Car>> carsModel;
    public CarsPresenterImpl(CarsView view) {
        super(view);
        carsModel=new CarsModelImpl();
    }

    @Override
    public void requestData(String requestData) {

        mSubscription=carsModel.requestSearchData(this,requestData);
    }

    @Override
    public void requestError(String msg) {
        super.requestError(msg);
        mView.showError();
    }

    @Override
    public void requestSuccess(List<Car> data) {
        if(data.size()==0)
            mView.showEmpty();
        else mView.showResult(data);
    }
}
