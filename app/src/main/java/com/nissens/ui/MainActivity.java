package com.nissens.ui;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nissens.R;
import com.nissens.annotation.ActivityFragmentInject;
import com.nissens.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@ActivityFragmentInject(
        contentViewId = R.layout.activity_main,
        toolbarTitle = R.string.main_title
)
public class MainActivity extends BaseActivity {

    @BindView(R.id.message)
    TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.straight, R.id.car, R.id.agency, R.id.type,R.id.settings,R.id.info})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.straight:
                startActivity(new Intent(MainActivity.this, SearchDirectlyActivity.class));
                break;
            case R.id.car:
                startActivity(new Intent(MainActivity.this, SearchByCarActivity.class));
                break;
            case R.id.agency:
                startActivity(new Intent(MainActivity.this, SearchOrganizationActivity.class));
                break;
            case R.id.type:
                startActivity(new Intent(MainActivity.this, SearchByTypeActivity.class).putExtra("mode","type"));
                break;
            case R.id.settings:
                startActivity(new Intent(MainActivity.this,SettingsActivity.class));
                break;
            case R.id.info:
                startActivity(new Intent(MainActivity.this,IntroduceActivity.class));
                break;
        }
    }
}
