package com.zhenhaikj.factoryside.mvp.model;

import com.zhenhaikj.factoryside.mvp.ApiRetrofit;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.ReadMessage;
import com.zhenhaikj.factoryside.mvp.bean.WorkOrder;
import com.zhenhaikj.factoryside.mvp.contract.LeaveMessageContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class LeaveMessageModel implements LeaveMessageContract.Model {
    @Override
    public Observable<BaseResult<Data<String>>> AddLeaveMessageForOrder(String UserID, String OrderId, String Content,String photo) {
        return ApiRetrofit.getDefault().AddLeaveMessageForOrder(UserID, "2", OrderId, Content,photo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<WorkOrder.DataBean>> GetOrderInfo(String OrderID) {
        return ApiRetrofit.getDefault().GetOrderInfo(OrderID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> LeaveMessageImg(RequestBody json) {
        return  ApiRetrofit.getDefault().LeaveMessageImg(json)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<List<ReadMessage>>>> LeaveMessageWhetherLook(String OrderID) {
        return ApiRetrofit.getDefault().LeaveMessageWhetherLook(OrderID,"2","1","1")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }


}
