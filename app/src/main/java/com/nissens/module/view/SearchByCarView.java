package com.nissens.module.view;

import com.nissens.base.BaseView;

import java.util.List;

/**
 * Created by PC-20160514 on 2016/10/17.
 */

public interface SearchByCarView extends BaseView {
    void showResult(List<String> strings);
    void showError();
    void showEmpty();
}
