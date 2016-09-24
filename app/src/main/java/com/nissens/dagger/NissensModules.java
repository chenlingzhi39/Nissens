package com.nissens.dagger;


import com.nissens.bean.ApiService;
import com.nissens.config.Constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by PC-20160514 on 2016/9/18.
 */
@Module
public class NissensModules {
    @Singleton
    @Provides
    protected ApiService getService(ScalarsConverterFactory scalarsConverterFactory, RxJavaCallAdapterFactory callAdapterFactory, GsonConverterFactory gsonConverterFactory, OkHttpClient client) {
        return new Retrofit.Builder() .baseUrl(Constants.BASE_URL)
                //增加返回值为String的支持
                .addConverterFactory(scalarsConverterFactory)
                //增加返回值为Gson的支持(以实体类返回)
                .addConverterFactory(gsonConverterFactory)
                //增加返回值为Oservable<T>的支持
                .addCallAdapterFactory(callAdapterFactory)
                .client(client)
                .build().create(ApiService.class);
    }
    @Singleton
    @Provides
    protected ScalarsConverterFactory getScalarsConverterFactory(){
        return ScalarsConverterFactory.create();
    }
    @Singleton
    @Provides
    protected RxJavaCallAdapterFactory getCallAdapterFactory() {
        return RxJavaCallAdapterFactory.create();
    }

    @Singleton
    @Provides
    protected GsonConverterFactory getGsonConvertFactory() {
        return GsonConverterFactory.create();
    }
    @Singleton
    @Provides
    protected OkHttpClient getClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();

                        // try the request
                        Response response = chain.proceed(request);

                        int tryCount = 0;
                        while (!response.isSuccessful() && tryCount < 3) {

                            tryCount++;

                            // retry the request
                            response = chain.proceed(request);
                        }

                        // otherwise just pass the original response on
                        return response;
                    }
                }).retryOnConnectionFailure(true)
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                //其他配置
                .build();
    }
}
