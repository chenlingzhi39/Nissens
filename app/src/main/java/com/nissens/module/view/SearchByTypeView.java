package com.nissens.module.view;

import com.nissens.base.BaseView;
import com.nissens.bean.BrandSeriesXml;

import java.util.List;

/**
 * Created by PC-20160514 on 2016/9/28.
 */
public interface SearchByTypeView extends BaseView{
    void showResult(List<BrandSeriesXml> brandSeriesXmls);
    void showError();
    void showEmpty();
}
