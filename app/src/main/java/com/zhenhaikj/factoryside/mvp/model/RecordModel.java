package com.zhenhaikj.factoryside.mvp.model;

import com.zhenhaikj.factoryside.mvp.ApiRetrofit;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Bill;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.Data2;
import com.zhenhaikj.factoryside.mvp.bean.Freezing;
import com.zhenhaikj.factoryside.mvp.bean.Recharge;
import com.zhenhaikj.factoryside.mvp.bean.SingleQuantity;
import com.zhenhaikj.factoryside.mvp.contract.RecordContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RecordModel implements RecordContract.Model {
    @Override
    public Observable<BaseResult<Data2<Recharge>>> RechargeRecord(String UserID, String CreateTimeStart, String CreateTimeEnd, String StateName, String State,String page, String limit) {
        return ApiRetrofit.getDefault().RechargeRecord(UserID, CreateTimeStart, CreateTimeEnd, StateName,State,page,limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<Freezing>>> FreezingAmount(String UserID,String CreateTimeStart, String CreateTimeEnd, String page, String limit) {
        return ApiRetrofit.getDefault().FreezingAmount(UserID, CreateTimeStart,CreateTimeEnd,page, limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<SingleQuantity>>> WorkOrderTime(String UserID, String CreateTimeStart, String CreateTimeEnd, String TypeID,String page, String limit) {
        return ApiRetrofit.getDefault().WorkOrderTime(UserID, CreateTimeStart, CreateTimeEnd,TypeID, page, limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
