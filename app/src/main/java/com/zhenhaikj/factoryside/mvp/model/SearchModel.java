package com.zhenhaikj.factoryside.mvp.model;

import com.zhenhaikj.factoryside.mvp.ApiRetrofit;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.Search;
import com.zhenhaikj.factoryside.mvp.contract.SearchContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SearchModel implements SearchContract.Model {
    @Override
    public Observable<BaseResult<Search>> GetOrderInfoList(String Phone, String OrderID, String UserID,String limit, String page) {
        return ApiRetrofit.getDefault().GetOrderInfoList(Phone, OrderID, UserID,limit, page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> GetFStarOrder(String OrderID, String FStarOrder) {
        return ApiRetrofit.getDefault().GetFStarOrder(OrderID, FStarOrder)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
