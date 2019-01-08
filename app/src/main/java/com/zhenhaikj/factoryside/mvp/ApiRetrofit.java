package com.zhenhaikj.factoryside.mvp;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRetrofit {

    private static ApiService SERVICE;
    public static ApiService getDefault(String method){
//        if (SERVICE == null){
            SERVICE = new Retrofit.Builder()
            .baseUrl(Config.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(Config.getClient(method))
            .build()
            .create(ApiService.class);
//        }
        return SERVICE;
    }
}
