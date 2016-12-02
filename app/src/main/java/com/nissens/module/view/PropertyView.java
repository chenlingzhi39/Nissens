package com.nissens.module.view;

import com.nissens.base.BaseView;
import com.nissens.bean.CategoryPropertyName;

import java.util.List;

/**
 * Created by Administrator on 2016/11/30.
 */

public interface PropertyView extends BaseView{
    void showResult(List<CategoryPropertyName> categoryPropertyNames);
    void showError();
    void showEmpty();
}
