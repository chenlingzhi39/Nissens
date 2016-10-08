package com.nissens.module.presenter;

import com.nissens.base.BaseModel;
import com.nissens.base.BasePresenterImpl;
import com.nissens.bean.BrandOrganization;
import com.nissens.module.model.OrganizationModelImpl;
import com.nissens.module.view.OrganizationView;

import java.util.List;

/**
 * Created by PC-20160514 on 2016/10/7.
 */

public class OrganizationPresenterImpl extends BasePresenterImpl<OrganizationView,List<BrandOrganization>> implements OrganizationPresenter{
    BaseModel organizationModel;

    public OrganizationPresenterImpl(OrganizationView view) {
        super(view);
       organizationModel=new OrganizationModelImpl();
    }

    @Override
    public void requestData(String requestData) {
        organizationModel.requestSearchData(this,requestData);
    }
    @Override
    public void requestError(String msg) {
        super.requestError(msg);
        mView.showError();
    }

    @Override
    public void requestSuccess(List<BrandOrganization> data) {
        if(data.size()==0)
            mView.showEmpty();
        else mView.showResult(data);
    }
}
