package com.nissens.module.view;

import com.nissens.base.BaseView;

/**
 * Created by PC-20160514 on 2016/9/30.
 */

public interface CarXmlView extends BaseView {
    void showResult(String xml);
    void showError();
    void showEmpty();
}
