package com.nissens.module.view;

import com.nissens.base.BaseView;
import com.nissens.bean.BrandOrganization;
import com.nissens.bean.OEData;

import java.util.List;

/**
 * Created by PC-20160514 on 2016/10/7.
 */

public interface OrganizationView extends BaseView {
    void showResult(List<BrandOrganization> brandOrganizations);
    void showError();
    void showEmpty();
}
