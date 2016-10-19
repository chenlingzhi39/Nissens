package com.nissens.bean;

/**
 * Created by PC-20160514 on 2016/10/18.
 */

public class CarModelDataRequest extends CarConditionRequest {
    private String PageIndex,
            ItemsPerPage;


    public String getItemsPerPage() {
        return ItemsPerPage;
    }

    public void setItemsPerPage(String itemsPerPage) {
        ItemsPerPage = itemsPerPage;
    }

    public String getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(String pageIndex) {
        PageIndex = pageIndex;
    }

    public CarModelDataRequest(String factory,String brand,String series,String group,String displacement,String gearBoxType,String year,String itemsPerPage, String pageIndex) {
        ItemsPerPage = itemsPerPage;
        PageIndex = pageIndex;
        setCarFactoryName(factory);
        setCarBrand(brand);
        setCarSeries(series);
        setCheZu(group);
        setDisplacement(displacement);
        setGearBoxType(gearBoxType);
        setYear(year);
    }
}
