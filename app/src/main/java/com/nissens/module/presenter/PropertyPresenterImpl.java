package com.nissens.module.presenter;

import com.nissens.base.BaseModel;
import com.nissens.base.BasePresenterImpl;
import com.nissens.bean.BrandOrganization;
import com.nissens.bean.BrandSeriesXml;
import com.nissens.bean.CategoryPropertyName;
import com.nissens.module.model.PropertyModelImpl;
import com.nissens.module.view.OrganizationView;
import com.nissens.module.view.PropertyView;

import java.util.List;

/**
 * Created by Administrator on 2016/11/30.
 */

public class PropertyPresenterImpl extends BasePresenterImpl<PropertyView,List<CategoryPropertyName>> implements PropertyPresenter {
    private BaseModel<List<CategoryPropertyName>> propertyModel;

    public PropertyPresenterImpl(PropertyView view) {
        super(view);
        propertyModel =new PropertyModelImpl();
    }

    @Override
    public void requestData(String requestData) {
        mSubscription=propertyModel.requestSearchData(this,requestData);
    }

    @Override
    public void requestError(String msg) {
        super.requestError(msg);
        mView.showError();
    }

    @Override
    public void requestSuccess(List<CategoryPropertyName> data) {
        if(data.size()==0)
            mView.showEmpty();
        else mView.showResult(data);
    }


}
