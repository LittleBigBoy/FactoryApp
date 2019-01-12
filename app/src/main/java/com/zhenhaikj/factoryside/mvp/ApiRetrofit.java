package com.zhenhaikj.factoryside.mvp;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRetrofit {

    private static ApiService SERVICE;
    public static ApiService getDefault(){
//        if (SERVICE == null){
            SERVICE = new Retrofit.Builder()
            .baseUrl(Config.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(Config.getClient())
            .build()
            .create(ApiService.class);
//        }
        return SERVICE;
    }
}
