package com.nissens.bean;

/**
 * Created by PC-20160514 on 2016/9/18.
 */
public class CarSingleResult extends Result{
    private String LiYangID,
            CarFactoryName,
            CarBrand,
            CarModel,
            Year,
            EngineModel,
            NameOfSales;

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
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

    public String getEngineModel() {
        return EngineModel;
    }

    public void setEngineModel(String engineModel) {
        EngineModel = engineModel;
    }

    public String getNameOfSales() {
        return NameOfSales;
    }

    public void setNameOfSales(String nameOfSales) {
        NameOfSales = nameOfSales;
    }

    public String getLiYangID() {
        return LiYangID;
    }

    public void setLiYangID(String liYangID) {
        LiYangID = liYangID;
    }
}
