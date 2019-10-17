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
    public Observable<BaseResult<Data<String>>> ApproveOrderAccessory(String OrderID, String AccessoryApplyState,String NewMoney,String OrderAccessoryID) {
        return ApiRetrofit.getDefault().ApproveOrderAccessory(OrderID, AccessoryApplyState,NewMoney,OrderAccessoryID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> ApproveBeyondMoney(String OrderID, String BeyondState,String BeyondMoney) {
        return ApiRetrofit.getDefault().ApproveBeyondMoney(OrderID, BeyondState,BeyondMoney)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> ApproveOrderService(String OrderID, String State,String OrderServiceID) {
        return ApiRetrofit.getDefault().ApproveOrderService(OrderID, State,OrderServiceID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> ApproveOrderAccessoryAndService(String OrderID, String AccessoryAndServiceApplyState, String PostPayType, String IsReturn) {
        return ApiRetrofit.getDefault().ApproveOrderAccessoryAndService(OrderID, AccessoryAndServiceApplyState,PostPayType,IsReturn)
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

    @Override
    public Observable<BaseResult<Data<String>>> GetOrderAccessoryMoney(String OrderID) {
        return ApiRetrofit.getDefault().GetOrderAccessoryMoney(OrderID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> UpdateFactoryAccessorybyFactory(String Id, String AccessoryName, String AccessoryPrice, String OrderAccessoryId) {
        return ApiRetrofit.getDefault().UpdateFactoryAccessorybyFactory(Id, AccessoryName, AccessoryPrice, OrderAccessoryId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> ApproveOrderAccessoryByModifyPrice(String OrderID, String AccessoryApplyState, String NewMoney, String OrderAccessoryID) {
        return ApiRetrofit.getDefault().ApproveOrderAccessoryByModifyPrice(OrderID, AccessoryApplyState, NewMoney, OrderAccessoryID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> NowEnSureOrder(String OrderID) {
        return ApiRetrofit.getDefault().NowEnSureOrder(OrderID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> FactoryComplaint(String OrderID, String Content) {
        return ApiRetrofit.getDefault().FactoryComplaint(OrderID, Content)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

}
