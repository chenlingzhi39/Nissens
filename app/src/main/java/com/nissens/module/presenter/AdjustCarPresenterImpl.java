package com.nissens.module.presenter;

import com.nissens.base.BaseModel;
import com.nissens.base.BasePresenterImpl;
import com.nissens.bean.Car;
import com.nissens.module.model.AdjustCarModelImpl;
import com.nissens.module.view.AdjustCarView;

import java.util.List;

/**
 * Created by PC-20160514 on 2016/9/26.
 */
public class AdjustCarPresenterImpl extends BasePresenterImpl<AdjustCarView,List<Car>> implements AdjustCarPresenter {
    private BaseModel<List<Car>> adjustCarModel;

    public AdjustCarPresenterImpl(AdjustCarView view) {
        super(view);
       adjustCarModel = new AdjustCarModelImpl();
    }

    @Override
    public void requestData(String requestData) {
        mSubscription=adjustCarModel.requestSearchData(this,requestData);
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
