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
    Observable<OEDataResult> queryBrandPartOEData(Map<String, String> params);

    @POST("queryBlendCarByBrandPartId")
    Observable<CarResult> queryBlendCarByBrandPartId(Map<String, String> params);

    @POST("queryBrandBusiness")
    Observable<BrandBusinessResult> queryBrandBusiness(Map<String, String> params);

    @POST("queryBrandSeries")
    Observable<BrandSeriesResult> queryBrandSeries(Map<String, String> params);

    @POST("queryBrandPartDataInFuzzyMode")
    Observable<BrandPartResult> queryBrandPartDataInFuzzyMode(Map<String, String> params);

    @POST("queryBrandOrganization")
    Observable<BrandOrganizationResult> queryBrandOrganization(Map<String, String> params);

    @POST("queryBrandIntroduction")
    Observable<BrandIntroductionResult> queryBrandIntroduction(Map<String, String> params);

    @POST("queryCompanyIntroduction")
    Observable<CampanyIntroductionResult> queryCompanyIntroduction(Map<String, String> params);

    @POST("queryCarDataByLYVin")
    Observable<CarSingleResult> queryCarDataByLYVin(Map<String, String> params);
}
