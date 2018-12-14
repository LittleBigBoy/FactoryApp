package com.emjiayuan.nll.mvp.model;

import com.emjiayuan.nll.ApiRetrofit;
import com.emjiayuan.nll.base.BaseResult;
import com.emjiayuan.nll.model.HomeData;
import com.emjiayuan.nll.mvp.contract.HomeContract;

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
