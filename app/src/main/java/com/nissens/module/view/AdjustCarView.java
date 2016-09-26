package com.nissens.module.view;

import com.nissens.base.BaseView;
import com.nissens.bean.Car;

import java.util.List;

/**
 * Created by PC-20160514 on 2016/9/26.
 */
public interface AdjustCarView extends BaseView {
    void showResult(List<Car> carList);
    void showError();
    void showEmpty();
}
