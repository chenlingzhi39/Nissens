package com.nissens.adapter;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.nissens.R;
import com.nissens.bean.Car;
import com.nissens.ui.CarInfoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PC-20160514 on 2016/9/26.
 */
public class CarViewHolder extends BaseViewHolder<Car> {

    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.series)
    TextView series;
    @BindView(R.id.info)
    ImageButton info;
    private boolean has_info;
    public CarViewHolder(ViewGroup parent,boolean has_info) {
        super(parent, R.layout.item_car);
        ButterKnife.bind(this, itemView);
        this.has_info=has_info;
    }

    @Override
    public void setData(final Car data) {
        name.setText(data.getCarBrand());
        series.setText(getContext().getString(R.string.car_series) + ":" + data.getCarSeries());
        if(has_info)
        {info.setVisibility(View.VISIBLE);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),CarInfoActivity.class);
                intent.putExtra("car",data);
                getContext().startActivity(intent);
            }
        });}
    }


}
