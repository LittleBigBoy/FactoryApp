package com.zhenhaikj.factoryside.mvp.model;



import com.zhenhaikj.factoryside.mvp.ApiRetrofit;

import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.contract.OpinionContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class OpinionModel implements OpinionContract.Model {
    @Override
    public Observable<BaseResult<Data<String>>> AddOpinion(String UserId, String BackType, String Content) {
        return ApiRetrofit.getDefault().AddOpinion(UserId, BackType, Content)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
