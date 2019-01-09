package com.zhenhaikj.factoryside.mvp.model;

import com.zhenhaikj.factoryside.mvp.contract.PurchaseContract;
import com.zhenhaikj.factoryside.mvp.ApiRetrofit;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Category;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class PurchaseModel implements PurchaseContract.Model {
    @Override
    public Observable<BaseResult<List<Category>>> getData() {
        return ApiRetrofit.getDefault().getCategory()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
