package com.nissens.ui;

import android.content.Intent;
import android.view.View;
import com.nissens.R;
import com.nissens.annotation.ActivityFragmentInject;
import com.nissens.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by PC-20160514 on 2016/9/26.
 */
@ActivityFragmentInject(
        contentViewId = R.layout.fragment_image
)
public class ImageFragment extends BaseFragment {


    @Override
    protected void initView(View fragmentRootView) {
        ButterKnife.bind(this, fragmentRootView);

    }

    @OnClick(R.id.image)
    public void onClick() {
    startActivity(new Intent(getActivity(),ImageActivity.class));
    }
}
