package com.nissens.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.BaseAdapter;

import com.nissens.R;
import com.nissens.annotation.ActivityFragmentInject;
import com.nissens.base.BaseActivity;

/**
 * Created by PC-20160514 on 2016/9/23.
 */
@ActivityFragmentInject(
        contentViewId = R.layout.activity_search_by_car,
        toolbarTitle = R.string.search_by_car
)
public class SearchByCarActivity extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
