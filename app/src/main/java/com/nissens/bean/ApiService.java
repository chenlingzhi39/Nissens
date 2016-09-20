package com.nissens.bean;


import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by PC-20160514 on 2016/9/18.
 */
public interface ApiService {
    @POST("/login")
    Observable<User> login(@Query("UserID") String name, @Query("EncryptCode") String password);

    @POST("/queryOriginalPartOEData")
    Observable<OEDataResult> queryOriginalPartOEData(@Query("UserID") String name, @Query("EncryptCode") String password);

    @POST("/queryBrandPartOEData")
    Observable<OEDataResult> queryBrandPartOEData(@Query("UserID") String name, @Query("EncryptCode") String password);

    @POST("/queryBlendCarByBrandPartId")
    Observable<CarResult> queryBlendCarByBrandPartId(@Query("UserID") String name, @Query("EncryptCode") String password,@Query("PageIndex")String pageIndex,@Query("ItemsPerPage")String itemsPerPage,@Query("FactoryID")String factoryId);

    @POST("/queryBrandBusiness")
    Observable<BrandBusinessResult> queryBrandBusiness(@Query("UserID") String name, @Query("EncryptCode") String password,@Query("BrandBusinessID")String brandBusinessID,@Query("BrandBusinessName") String brandBusinessName);

    @POST("/queryBrandSeries")
    Observable<BrandSeriesResult> queryBrandSeries(@Query("UserID") String name, @Query("EncryptCode") String password,@Query("BrandBusinessID")String brandBusinessID,@Query("BrandBusinessName") String brandBusinessName,@Query("SeriesID")String seriesId,@Query("SeriesName")String SeriesName);

    @POST("/queryBrandPartDataInFuzzyMode")
    Observable<BrandPartResult> queryBrandPartDataInFuzzyMode(@Query("UserID") String name, @Query("EncryptCode") String password,@Query("BrandBusinessID")String brandBusinessID,@Query("BrandBusinessName")String brandBusinessName);

    @POST("/queryBrandOrganization")
    Observable<BrandOrganizationResult> queryBrandOrganization(@Query("UserID") String name, @Query("EncryptCode") String password);

    @POST("/queryBrandIntroduction")
    Observable<BrandIntroductionResult> queryBrandIntroduction(@Query("UserID") String name, @Query("EncryptCode") String password,@Query("BrandBusinessID")String brandBusinessID,@Query("BrandBusinessName")String brandBusinessName);

    @POST("/queryCompanyIntroduction")
    Observable<CampanyIntroductionResult> queryCompanyIntroduction(@Query("UserID") String name, @Query("EncryptCode") String password,@Query("BrandBusinessID")String brandBusinessID,@Query("BrandBusinessName")String brandBusinessName);

    @POST("/queryCarDataByLYVin")
    Observable<CarSingleResult> queryCarDataByLYVin(@Query("UserID") String name, @Query("EncryptCode") String password,@Query("vin")String vin);
}
