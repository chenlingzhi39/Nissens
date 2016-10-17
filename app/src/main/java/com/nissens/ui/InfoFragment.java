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
    @BindView(R.id.weight)
    TextView weight;
    OEData oeData;
    @BindView(R.id.factory_id)
    TextView factoryId;
    @BindView(R.id.original_factory_id)
    TextView originalFactoryId;
    @BindView(R.id.length)
    TextView length;
    @BindView(R.id.width)
    TextView width;
    @BindView(R.id.height)
    TextView height;
    @BindView(R.id.capacity)
    TextView capacity;
    @BindView(R.id.specification)
    TextView specification;
    @Override
    protected void initView(View fragmentRootView) {
        ButterKnife.bind(this, fragmentRootView);
        oeData = (OEData) getArguments().get("oeData");
        factoryId.setText(getString(R.string.factory_id) + ":" + oeData.getFactoryID());
        originalFactoryId.setText(getString(R.string.original_factory_id) + ":" + oeData.getOriginalFactoryID());
        weight.setText(oeData.getWeight());
        length.setText(oeData.getLength());
        width.setText(oeData.getWidth());
        height.setText(oeData.getHeight());
        capacity.setText(oeData.getCapacity());
        specification.setText(oeData.getStandardSpecification());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        ButterKnife.bind(this, super.onCreateView(inflater, container, savedInstanceState));
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
