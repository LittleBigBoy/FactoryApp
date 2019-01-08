package com.zhenhaikj.factoryside.mvp.model;

import com.zhenhaikj.factoryside.mvp.ApiRetrofit;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.HomeData;
import com.zhenhaikj.factoryside.mvp.contract.HomeContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class HomeModel implements HomeContract.Model {
    @Override
    public Observable<BaseResult<HomeData>> getData(String userid) {
        return ApiRetrofit.getDefault("public.appHome").getHome(userid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
