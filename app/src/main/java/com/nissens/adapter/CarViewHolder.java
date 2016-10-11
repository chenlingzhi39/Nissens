package com.nissens.adapter;

import android.view.ViewGroup;
import android.widget.TextView;

import com.nissens.R;
import com.nissens.bean.Car;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PC-20160514 on 2016/9/26.
 */
public class CarViewHolder extends BaseViewHolder<Car> {

    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.id)
    TextView id;
    @BindView(R.id.series)
    TextView series;

    public CarViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_car);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void setData(Car data) {
     name.setText(data.getCarBrand());
     id.setText(getContext().getString(R.string.factory_id)+":"+data.getLiYangID());
     series.setText(getContext().getString(R.string.car_series)+":"+data.getCarSeries());
    }


}
