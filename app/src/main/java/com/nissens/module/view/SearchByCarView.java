package com.nissens.module.view;

import com.nissens.base.BaseView;
import com.nissens.bean.Car;

/**
 * Created by PC-20160514 on 2016/10/17.
 */

public interface SearchByCarView extends BaseView {
    void showResult(Car car);
    void showError();
    void showEmpty();
}
