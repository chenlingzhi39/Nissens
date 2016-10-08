package com.nissens.bean;


import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by PC-20160514 on 2016/9/18.
 */
public interface ApiService {
    @POST("login")
    Observable<User> login(Map<String, String> params);

    @POST("queryOriginalPartOEData")
    Observable<OEDataResult> queryOriginalPartOEData(@Query("requestPara")String requestPara);

    @POST("queryBrandPartOEData")
    Observable<OEDataResult> queryBrandPartOEData(@Query("requestPara")String requestPara);

    @POST("queryBlendCarByBrandPartId")
    Observable<CarResult> queryBlendCarByBrandPartId(@Query("requestPara")String requestPara);

    @POST("queryBrandBusiness")
    Observable<BrandBusinessResult> queryBrandBusiness(@Query("requestPara")String requestPara);

    @POST("queryBrandSeriesXml")
    Observable<BrandSeriesXmlResult> queryBrandSeriesXml(@Query("requestPara")String requestPara);

    @POST("queryBrandPartDataInFuzzyMode")
    Observable<BrandPartResult> queryBrandPartDataInFuzzyMode(Map<String, String> params);

    @POST("queryBrandOrganization")
    Observable<BrandOrganizationResult> queryBrandOrganization(@Query("requestPara")String requestPara);

    @POST("queryBrandIntroduction")
    Observable<BrandIntroductionResult> queryBrandIntroduction(Map<String, String> params);

    @POST("queryCompanyIntroduction")
    Observable<CampanyIntroductionResult> queryCompanyIntroduction(Map<String, String> params);

    @POST("queryCarDataByLYVin")
    Observable<CarSingleResult> queryCarDataByLYVin(@Query("requestPara")String requestPara);

    @POST("queryCarBrandXml")
    Observable<CarBrandXmlResult> queryCarBrandXml(@Query("requestPara")String requestPara);

}
