package com.zhenhaikj.factoryside.mvp.model;

import com.zhenhaikj.factoryside.mvp.ApiRetrofit;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.WorkOrder;
import com.zhenhaikj.factoryside.mvp.contract.AllWorkOrdersContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class AllWorkOrdersModel implements AllWorkOrdersContract.Model {

    @Override
    public Observable<BaseResult<WorkOrder>> GetOrderInfoList(String UserID,String state, String page, String limit) {
        return ApiRetrofit.getDefault().FactoryGetOrderList(UserID,state, page, limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> UpdateOrderState(String OrderID, String State) {
        return ApiRetrofit.getDefault().UpdateOrderState(OrderID, State)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> FactoryComplaint(String OrderID, String Content) {
        return ApiRetrofit.getDefault().FactoryComplaint(OrderID, Content)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> EnSureOrder(String OrderID, String PayPassword) {
        return ApiRetrofit.getDefault().EnSureOrder(OrderID, PayPassword)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
    @Override
    public Observable<BaseResult<Data<String>>> FactoryEnsureOrder(String OrderID, String PayPassword) {
        return ApiRetrofit.getDefault().FactoryEnsureOrder(OrderID, PayPassword)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> UpdateOrderFIsLook(String OrderID, String IsLook, String FIsLook) {
        return ApiRetrofit.getDefault().UpdateOrderFIsLook(OrderID, IsLook,FIsLook)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> ApplyCancelOrder(String OrderID) {
        return ApiRetrofit.getDefault().ApplyCancelOrder(OrderID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
