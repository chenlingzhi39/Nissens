package com.nissens.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SearchView;
import android.view.Menu;

import com.nissens.R;
import com.nissens.annotation.ActivityFragmentInject;
import com.nissens.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PC-20160514 on 2016/9/23.
 */
@ActivityFragmentInject(
        contentViewId = R.layout.activity_search_by_car,
        toolbarTitle = R.string.search_by_car
)
public class SearchByCarActivity extends BaseActivity {
    @BindView(R.id.search)
    SearchView search;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        search.setQueryHint(getString(R.string.please_input_vin));
        search.onActionViewExpanded();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       /* MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);
        MenuItem menuItem = menu.findItem(R.id.search);//在菜单中找到对应控件的item
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setQueryHint(getString(R.string.please_input_vin));
        searchView.performClick();
        MenuItemCompat.setOnActionExpandListener(menuItem, new MenuItemCompat.OnActionExpandListener() {//设置打开关闭动作监听
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                Toast.makeText(SearchByCarActivity.this, "\nonExpand", Toast.LENGTH_LONG).show();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Toast.makeText(SearchByCarActivity.this, "Collapse", Toast.LENGTH_LONG).show();
                return true;
            }
        });*/
        return super.onCreateOptionsMenu(menu);
    }
}
