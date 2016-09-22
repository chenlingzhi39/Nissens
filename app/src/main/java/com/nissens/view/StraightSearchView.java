package com.nissens.view;

import com.nissens.base.BaseView;
import com.nissens.bean.OEData;

import java.util.List;

/**
 * Created by PC-20160514 on 2016/9/21.
 */
public interface StraightSearchView extends BaseView {
void showResult(List<OEData> oeDatas);
}
