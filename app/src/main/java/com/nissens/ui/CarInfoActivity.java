package com.nissens.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.nissens.R;
import com.nissens.annotation.ActivityFragmentInject;
import com.nissens.base.BaseActivity;
import com.nissens.bean.Car;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PC-20160514 on 2016/10/12.
 */
@ActivityFragmentInject(
        contentViewId = R.layout.activity_car_info
)
public class CarInfoActivity extends BaseActivity {
    @BindView(R.id.factory)
    TextView factory;
    @BindView(R.id.brand)
    TextView brand;
    @BindView(R.id.car_series)
    TextView carSeries;
    @BindView(R.id.car_model)
    TextView carModel;
    @BindView(R.id.name_of_sales)
    TextView nameOfSales;
    @BindView(R.id.year)
    TextView year;
    @BindView(R.id.gear_box_type)
    TextView gearBoxType;
    @BindView(R.id.engine_model)
    TextView engineModel;
    @BindView(R.id.year_in_produce)
    TextView yearInProduce;
    @BindView(R.id.displacement)
    TextView displacement;
    private Car car;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        car = (Car) getIntent().getExtras().get("car");
        getSupportActionBar().setTitle(car.getCarBrand());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        factory.setText(getString(R.string.factory) + ":" + car.getCarFactoryName());
        brand.setText(getString(R.string.brand) + ":" + car.getCarBrand());
        carSeries.setText(getString(R.string.car_series) + ":" + car.getCarSeries());
        carModel.setText(getString(R.string.car_model) + ":" + car.getCarModel());
        nameOfSales.setText(getString(R.string.name_of_sales) + ":" + car.getNameOfSales());
        year.setText(getString(R.string.year) + ":" + car.getYear());
        gearBoxType.setText(getString(R.string.gear_box_type) + ":" + car.getGearBoxType());
        engineModel.setText(getString(R.string.engine_model) + ":" + car.getEngineModel());
        yearInProduce.setText(getString(R.string.year_in_produce) + ":" + car.getYearInProduce());
        displacement.setText(getString(R.string.displacement) + ":" + car.getDisplacement());
    }
}
