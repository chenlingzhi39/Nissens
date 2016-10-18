package com.nissens.module.view;

import com.nissens.base.BaseView;
import com.nissens.bean.Car;

import java.util.List;

/**
 * Created by PC-20160514 on 2016/10/18.
 */

public interface CarsView extends BaseView {
    void showResult(List<Car> cars);
    void showError();
    void showEmpty();
}
