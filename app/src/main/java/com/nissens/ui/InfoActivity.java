package com.nissens.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.nissens.R;
import com.nissens.annotation.ActivityFragmentInject;
import com.nissens.base.BaseActivity;
import com.nissens.bean.OEData;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PC-20160514 on 2016/9/26.
 */
@ActivityFragmentInject(
        contentViewId = R.layout.activity_info,
        toolbarTitle = R.string.info,
        menuId = R.menu.normal
)
public class InfoActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.layout_tab)
    TabLayout layoutTab;
    @BindView(R.id.mToolbarContainer)
    AppBarLayout mToolbarContainer;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    private String tabTitles[] = new String[]{"信息", "适用车型", "图片", "供应商","主要规格","扩展规格"};
    private OEData oeData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        oeData = (OEData) getIntent().getExtras().get("oeData");
        viewpager.setAdapter(new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this));
        layoutTab.setupWithViewPager(viewpager);
        getSupportActionBar().setTitle(oeData.getPartName());
    }

    public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

        final int PAGE_COUNT = 6;

        private Context context;

        public SimpleFragmentPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;

        }

        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            switch (position) {
                case 0:
                    bundle.putParcelable("oeData", oeData);
                    InfoFragment infoFragment = new InfoFragment();
                    infoFragment.setArguments(bundle);
                    return infoFragment;
                case 1:
                    bundle.putString("factory_id", oeData.getFactoryID());
                    AdjustCarFragment adjustCarFragment = new AdjustCarFragment();
                    adjustCarFragment.setArguments(bundle);
                    return adjustCarFragment;
                case 2:
                    bundle.putString("id",oeData.getFactoryID());
                    bundle.putString("name",oeData.getOriginalFactoryName());
                    ImageFragment imageFragment = new ImageFragment();
                    imageFragment.setArguments(bundle);
                    return imageFragment;
                case 3:
                    return new AgencyFragment();
                case 4:
                    PropertyFragment propertyFragment=new PropertyFragment();
                    bundle.putString("isExternal","No");
                    bundle.putString("category_id",oeData.getPartNameID());
                    bundle.putString("specification",oeData.getStandardSpecification());
                    propertyFragment.setArguments(bundle);
                    return propertyFragment;
                case 5:
                    PropertyFragment propertyFragment1=new PropertyFragment();
                    bundle.putString("isExternal","Yes");
                    bundle.putString("category_id",oeData.getPartNameID());
                    bundle.putString("specification",oeData.getStandardSpecification());
                    propertyFragment1.setArguments(bundle);
                    return propertyFragment1;
                default:
                    return null;

            }

        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }
}
