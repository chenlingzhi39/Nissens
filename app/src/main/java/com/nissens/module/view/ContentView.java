package com.nissens.module.view;

import com.nissens.base.BaseView;
import com.nissens.bean.CategoryPropertyName;

import java.util.List;

/**
 * Created by Administrator on 2016/12/1.
 */

public interface ContentView extends BaseView {
    void showResult(Object contents);
    void showError();
    void showEmpty();
}
