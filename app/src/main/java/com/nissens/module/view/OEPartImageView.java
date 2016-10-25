package com.nissens.module.view;

import com.nissens.base.BaseView;
import com.nissens.bean.BrandOrganization;
import com.nissens.bean.Image;

import java.util.List;

/**
 * Created by PC-20160514 on 2016/10/25.
 */

public interface OEPartImageView extends BaseView {
    void showResult(List<Image> images);
    void showError();
    void showEmpty();
}
