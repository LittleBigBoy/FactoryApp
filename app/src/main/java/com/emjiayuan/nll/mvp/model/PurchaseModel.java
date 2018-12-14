package com.emjiayuan.nll.mvp.model;

import com.emjiayuan.nll.ApiRetrofit;
import com.emjiayuan.nll.base.BaseResult;
import com.emjiayuan.nll.model.Category;
import com.emjiayuan.nll.mvp.contract.PurchaseContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class PurchaseModel implements PurchaseContract.Model {
    @Override
    public Observable<BaseResult<List<Category>>> getData() {
        return ApiRetrofit.getDefault("product.getCategoryList").getCategory()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
