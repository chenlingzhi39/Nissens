package com.nissens.bean;

import android.util.SparseArray;

import com.nissens.R;

/**
 * Created by Administrator on 2016/10/17.
 */

public class CarConditionRequest extends Request {
    private String LiYangID,
            CarFactoryName,
            CarBrand,
            CarSeries,
            CarModel,
            Year,
            Displacement,
            GearBoxType,
            EngineModel,
            ChassisModel,
            CheZu;

    public CarConditionRequest() {
    }

    public CarConditionRequest(SparseArray<String> strings){
    CarFactoryName=strings.get(0);
    CarBrand= strings.get(1);
    CarSeries= strings.get(2);
        CheZu= strings.get(3);
    Displacement= strings.get(4);
    GearBoxType=strings.get(5);
    Year=strings.get(6);
}
    public String getCarBrand() {
        return CarBrand;
    }

    public void setCarBrand(String carBrand) {
        CarBrand = carBrand;
    }

    public String getCarFactoryName() {
        return CarFactoryName;
    }

    public void setCarFactoryName(String carFactoryName) {
        CarFactoryName = carFactoryName;
    }

    public String getCarModel() {
        return CarModel;
    }

    public void setCarModel(String carModel) {
        CarModel = carModel;
    }

    public String getCarSeries() {
        return CarSeries;
    }

    public void setCarSeries(String carSeries) {
        CarSeries = carSeries;
    }

    public String getChassisModel() {
        return ChassisModel;
    }

    public void setChassisModel(String chassisModel) {
        ChassisModel = chassisModel;
    }

    public String getDisplacement() {
        return Displacement;
    }

    public void setDisplacement(String displacement) {
        Displacement = displacement;
    }

    public String getEngineModel() {
        return EngineModel;
    }

    public void setEngineModel(String engineModel) {
        EngineModel = engineModel;
    }

    public String getGearBoxType() {
        return GearBoxType;
    }

    public void setGearBoxType(String gearBoxType) {
        GearBoxType = gearBoxType;
    }

    public String getLiYangID() {
        return LiYangID;
    }

    public void setLiYangID(String liYangID) {
        LiYangID = liYangID;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getCheZu() {
        return CheZu;
    }

    public void setCheZu(String cheZu) {
        CheZu = cheZu;
    }
}
