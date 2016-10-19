package com.nissens.bean;

import java.io.Serializable;

/**
 * Created by PC-20160514 on 2016/9/18.
 */
public class Car implements Serializable{
    private String LiYangID,
            CarFactoryName,
            CarBrand,
            CarSeries,
            CarModel,
            Year,
            Displacement,
            GearBoxType,
            EngineModel,
            NameOfSales,
            YearInProduce,
            ChassisModel,
            CheZu;

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

    public String getDisplacement() {
        return Displacement;
    }

    public void setDisplacement(String displacement) {
        Displacement = displacement;
    }

    public String getGearBoxType() {
        return GearBoxType;
    }

    public void setGearBoxType(String gearBoxType) {
        GearBoxType = gearBoxType;
    }

    public String getEngineModel() {
        return EngineModel;
    }

    public void setEngineModel(String engineModel) {
        EngineModel = engineModel;
    }

    public String getLiYangID() {
        return LiYangID;
    }

    public void setLiYangID(String liYangID) {
        LiYangID = liYangID;
    }

    public String getNameOfSales() {
        return NameOfSales;
    }

    public void setNameOfSales(String nameOfSales) {
        NameOfSales = nameOfSales;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getYearInProduce() {
        return YearInProduce;
    }

    public void setYearInProduce(String yearInProduce) {
        YearInProduce = yearInProduce;
    }

    public String getChassisModel() {
        return ChassisModel;
    }

    public void setChassisModel(String chassisModel) {
        ChassisModel = chassisModel;
    }

    public String getCheZu() {
        return CheZu;
    }

    public void setCheZu(String cheZu) {
        CheZu = cheZu;
    }
}
