package com.nissens.module.view;

import com.nissens.base.BaseView;
import com.nissens.bean.OEData;

import java.util.List;

/**
 * Created by PC-20160514 on 2016/9/22.
 */
public interface StraightSearchView extends BaseView{
    void addData(List<OEData> oeDataList);
}
