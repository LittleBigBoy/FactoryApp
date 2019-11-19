package com.zhenhaikj.factoryside.mvp.presenter;

import com.zhenhaikj.factoryside.mvp.base.BaseObserver;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.ReadMessage;
import com.zhenhaikj.factoryside.mvp.bean.WorkOrder;
import com.zhenhaikj.factoryside.mvp.contract.LeaveMessageContract;

import java.util.List;

import okhttp3.RequestBody;

public class LeaveMessagePresenter extends LeaveMessageContract.Presenter {
    @Override
    public void AddLeaveMessageForOrder(String UserID, String OrderId, String Content,String photo) {
        mModel.AddLeaveMessageForOrder(UserID, OrderId, Content,photo)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.AddLeaveMessageForOrder(value);
                    }
                });
    }

    @Override
    public void GetOrderInfo(String OrderID) {
        mModel.GetOrderInfo(OrderID)
                .subscribe(new BaseObserver<WorkOrder.DataBean>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<WorkOrder.DataBean> value) {
                        mView.GetOrderInfo(value);
                    }
                });
    }

    @Override
    public void LeaveMessageImg(RequestBody json ) {
        mModel.LeaveMessageImg(json)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.LeaveMessageImg(value);
                    }
                });
    }

    @Override
    public void LeaveMessageWhetherLook(String OrderID) {
        mModel.LeaveMessageWhetherLook(OrderID)
                .subscribe(new BaseObserver<Data<List<ReadMessage>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<List<ReadMessage>>> value) {
                        mView.LeaveMessageWhetherLook(value);
                    }
                });
    }
}
