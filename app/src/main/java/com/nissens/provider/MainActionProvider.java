package com.nissens.provider;

import android.content.Context;

import android.content.Intent;
import android.support.v4.view.ActionProvider;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

import com.nissens.R;
import com.nissens.ui.MainActivity;
import com.nissens.ui.SearchByVinActivity;
import com.nissens.ui.SearchByTypeActivity;
import com.nissens.ui.SearchDirectlyActivity;
import com.nissens.ui.SearchOrganizationActivity;

/**
 * Created by PC-20160514 on 2016/10/17.
 */

public class MainActionProvider extends ActionProvider {
    Context context;

    public MainActionProvider(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public View onCreateActionView() {
        return null;
    }

    @Override
    public void onPrepareSubMenu(SubMenu subMenu) {
        subMenu.clear();
        subMenu.add(context.getString(R.string.home_page))
                .setIcon(R.mipmap.ic_home_white_24dp)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        context.startActivity(new Intent(context, MainActivity.class));
                        return true;
                    }
                });
        subMenu.add(context.getString(R.string.search_directly))
                .setIcon(R.mipmap.search_directly)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        context.startActivity(new Intent(context, SearchDirectlyActivity.class));
                        return true;
                    }
                });
        subMenu.add(context.getString(R.string.search_by_car))
                .setIcon(R.mipmap.search_by_car)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        context.startActivity(new Intent(context, SearchByVinActivity.class));
                        return false;
                    }
                });
        subMenu.add(context.getString(R.string.agency))
                .setIcon(R.mipmap.agency)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        context.startActivity(new Intent(context, SearchOrganizationActivity.class));
                        return false;
                    }
                });
        subMenu.add(context.getString(R.string.search_by_type))
                .setIcon(R.mipmap.search_by_type)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        context.startActivity(new Intent(context, SearchByTypeActivity.class));
                        return false;
                    }
                });
    }
    @Override
    public boolean hasSubMenu() {
        return true;
    }
}
