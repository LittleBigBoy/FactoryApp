package com.zhenhaikj.factoryside.mvp.presenter;

import com.zhenhaikj.factoryside.mvp.base.BaseObserver;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Bill;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.Data2;
import com.zhenhaikj.factoryside.mvp.bean.Freezing;
import com.zhenhaikj.factoryside.mvp.bean.Recharge;
import com.zhenhaikj.factoryside.mvp.bean.SingleQuantity;
import com.zhenhaikj.factoryside.mvp.contract.RecordContract;

import java.util.List;

public class RecordPresenter extends RecordContract.Presenter {
    @Override
    public void RechargeRecord(String UserID, String CreateTimeStart, String CreateTimeEnd, String StateName,String State,String page, String limit) {
        mModel.RechargeRecord(UserID, CreateTimeStart, CreateTimeEnd, StateName,State,page,limit)
                .subscribe(new BaseObserver<Data2<Recharge>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data2<Recharge>> value) {
                        mView.RechargeRecord(value);
                    }
                });
    }

    @Override
    public void FreezingAmount(String UserID,String CreateTimeStart, String CreateTimeEnd, String page, String limit) {
        mModel.FreezingAmount(UserID,CreateTimeStart,CreateTimeEnd, page, limit)
                .subscribe(new BaseObserver<Data<Freezing>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<Freezing>> value) {
                        mView.FreezingAmount(value);
                    }
                });
    }

    @Override
    public void WorkOrderTime(String UserID, String CreateTimeStart, String CreateTimeEnd, String TypeID,String page, String limit) {
        mModel.WorkOrderTime(UserID, CreateTimeStart, CreateTimeEnd, TypeID,page, limit)
                .subscribe(new BaseObserver<Data<SingleQuantity>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<SingleQuantity>> value) {
                        mView.WorkOrderTime(value);
                    }
                });
    }
}
