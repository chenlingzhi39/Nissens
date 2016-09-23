package com.nissens.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.nissens.R;
import com.nissens.annotation.ActivityFragmentInject;
import com.nissens.manager.SystemBarTintManager;
import com.nissens.ui.MainActivity;

/**
 * Created by PC-20160514 on 2016/9/21.
 */
public class BaseActivity<T extends BasePresenter> extends AppCompatActivity{
    /**
     * 布局的id
     */
    protected int mContentViewId;
    /**
     * 菜单的id
     */
    private int mMenuId;
    /**
     * Toolbar标题
     */
    private int mToolbarTitle;
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getClass().isAnnotationPresent(ActivityFragmentInject.class)) {
            ActivityFragmentInject annotation = getClass()
                    .getAnnotation(ActivityFragmentInject.class);
            mContentViewId = annotation.contentViewId();
            mMenuId = annotation.menuId();
            mToolbarTitle = annotation.toolbarTitle();
        } else {
            throw new RuntimeException(
                    "Class must add annotations of ActivityFragmentInitParams.class");
        }
        setContentView(mContentViewId);
        initToolbar();
        if(mToolbarTitle!=-1)
            setToolbarTitle(mToolbarTitle);
        SystemBarTintManager systemBarTintManager=new SystemBarTintManager(this);
        systemBarTintManager.setStatusBarTintEnabled(true);
        systemBarTintManager.setStatusBarTintResource(R.color.colorPrimary);
    }
    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }
    public void setToolbarTitle(int strId){
        if(this.getClass()!=MainActivity.class)
        {getSupportActionBar().setTitle(getString(strId));
         getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         }
        else{
            getSupportActionBar().setTitle("");
            TextView title = (TextView) findViewById(R.id.title);
            title.setText(getResources().getString(mToolbarTitle));
        }
    }
    public void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        if (mMenuId != -1)
            getMenuInflater().inflate(mMenuId, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
    }
}
