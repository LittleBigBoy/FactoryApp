package com.emjiayuan.factoryside.mvp.model;

import com.emjiayuan.factoryside.mvp.contract.HomeContract;
import com.emjiayuan.nll.ApiRetrofit;
import com.emjiayuan.nll.base.BaseResult;
import com.emjiayuan.nll.model.HomeData;

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
