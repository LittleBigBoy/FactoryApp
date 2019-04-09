package com.zhenhaikj.factoryside.mvp.model;

import com.zhenhaikj.factoryside.mvp.ApiRetrofit;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Address;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.WorkOrder;
import com.zhenhaikj.factoryside.mvp.contract.WorkOrdersDetailContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class WorkOrdersDetailModel implements WorkOrdersDetailContract.Model {


    @Override
    public Observable<BaseResult<WorkOrder.DataBean>> GetOrderInfo(String OrderID) {
        return ApiRetrofit.getDefault().GetOrderInfo(OrderID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> ApplyCustomService(String OrderID) {
        return ApiRetrofit.getDefault().ApplyCustomService(OrderID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> ApproveOrderAccessory(String OrderID, String AccessoryApplyState) {
        return ApiRetrofit.getDefault().ApproveOrderAccessory(OrderID, AccessoryApplyState)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> ApproveBeyondMoney(String OrderID, String BeyondState) {
        return ApiRetrofit.getDefault().ApproveBeyondMoney(OrderID, BeyondState)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> ApproveOrderService(String OrderID, String State) {
        return ApiRetrofit.getDefault().ApproveOrderService(OrderID, State)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> AddOrUpdateExpressNo(String OrderID, String ExpressNo) {
        return ApiRetrofit.getDefault().AddOrUpdateExpressNo(OrderID, ExpressNo)
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
    public Observable<BaseResult<Data<String>>> UpdateIsReturnByOrderID(String OrderID, String IsReturn, String AddressBack, String PostPayType) {
        return ApiRetrofit.getDefault().UpdateIsReturnByOrderID(OrderID, IsReturn,AddressBack,PostPayType)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
    @Override
    public Observable<BaseResult<List<Address>>> GetAccountAddress(String UserId) {
        return ApiRetrofit.getDefault().GetAccountAddress(UserId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
