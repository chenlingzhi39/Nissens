package com.nissens.ui;

import android.view.View;

import android.widget.TextView;
import android.util.Log;

import com.google.gson.Gson;
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
        Log.i("oeData_result",new Gson().toJson(oeData));
        factoryId.setText(getString(R.string.factory_id) + ":" + oeData.getFactoryID());
        originalFactoryId.setText(getString(R.string.original_factory_id) + ":" + oeData.getOriginalFactoryID());
        weight.setText(oeData.getWeight());
        length.setText(oeData.getLength());
        width.setText(oeData.getWidth());
        height.setText(oeData.getHeight());
        capacity.setText(oeData.getCapacity());
        specification.setText(oeData.getStandardSpecification());
    }
}
