package com.nissens.app;

import android.app.Application;
import android.content.Context;

import com.nissens.dagger.NissensComponent;

/**
 * Created by PC-20160514 on 2016/9/18.
 */
public class MyApplication extends Application{
    private NissensComponent nissensComponent;
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        nissensComponent=NissensComponent.NissensInitialize.init();
        mContext=this;
    }
    public static NissensComponent getComponent(){
        return ((MyApplication)mContext.getApplicationContext()).nissensComponent;
    }
}
