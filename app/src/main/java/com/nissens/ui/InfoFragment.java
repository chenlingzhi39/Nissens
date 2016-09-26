package com.nissens.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nissens.R;
import com.nissens.annotation.ActivityFragmentInject;
import com.nissens.base.BaseFragment;
import com.nissens.bean.OEData;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PC-20160514 on 2016/9/26.
 */
@ActivityFragmentInject(
        contentViewId = R.layout.fragment_info
)
public class InfoFragment extends BaseFragment {
    @BindView(R.id.id)
    TextView id;
    @BindView(R.id.status)
    TextView status;
    @BindView(R.id.weight)
    TextView weight;
    OEData oeData;
    @Override
    protected void initView(View fragmentRootView) {
      ButterKnife.bind(this,fragmentRootView);
        oeData=(OEData) getArguments().get("oeData");
        id.setText(getString(R.string.factory_id)+":"+oeData.getFactoryID());
        status.setText(getString(R.string.status)+":"+oeData.getOedataStatus());
        weight.setText(getString(R.string.weight)+oeData.getWeight());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
