package com.nissens.ui;

import com.nissens.R;
import com.nissens.annotation.ActivityFragmentInject;
import com.nissens.base.BaseActivity;
import com.nissens.module.presenter.CarConditionPresenter;

/**
 * Created by PC-20160514 on 2016/10/17.
 */
@ActivityFragmentInject(
        contentViewId = R.layout.activity_search_by_car
)
public class SearchByCarActivity extends BaseActivity<CarConditionPresenter> {

}
