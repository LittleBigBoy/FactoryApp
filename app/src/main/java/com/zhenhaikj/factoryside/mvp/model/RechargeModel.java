package com.zhenhaikj.factoryside.mvp.model;

import com.zhenhaikj.factoryside.mvp.ApiRetrofit;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.WXpayInfo;
import com.zhenhaikj.factoryside.mvp.contract.RechargeContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class RechargeModel implements RechargeContract.Model {


    @Override
    public Observable<BaseResult<Data<String>>> GetOrderStr(String userid, String TotalAmount) {
        return ApiRetrofit.getDefault().GetOrderStr(userid, TotalAmount,"1")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
    @Override
    public Observable<BaseResult<Data<WXpayInfo>>> GetWXOrderStr(String userid, String TotalAmount) {
        return ApiRetrofit.getDefault().GetWXOrderStr(userid, TotalAmount,"1","factory")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
    @Override
    public Observable<BaseResult<Data<String>>> WXNotifyManual(String OutTradeNo) {
        return ApiRetrofit.getDefault().WXNotifyManual(OutTradeNo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
