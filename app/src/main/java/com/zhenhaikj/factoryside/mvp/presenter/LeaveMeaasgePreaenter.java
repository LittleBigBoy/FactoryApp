package com.zhenhaikj.factoryside.mvp.presenter;


import com.zhenhaikj.factoryside.mvp.base.BaseObserver;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.LeaveMessage;
import com.zhenhaikj.factoryside.mvp.contract.LeaveMeaasgeContract;

public class LeaveMeaasgePreaenter extends LeaveMeaasgeContract.Presenter {
    @Override
    public void GetNewsLeaveMessage(String UserID, String limit, String page) {
        mModel.GetNewsLeaveMessage(UserID, limit, page)
                .subscribe(new BaseObserver<Data<LeaveMessage>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<LeaveMessage>> value) {
                        mView.GetNewsLeaveMessage(value);
                    }
                });
    }

    @Override
    public void LeaveMessageWhetherLook(String OrderID) {
        mModel.LeaveMessageWhetherLook(OrderID)
                .subscribe(new BaseObserver<Data>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data> value) {
                        mView.LeaveMessageWhetherLook(value);
                    }
                });
    }
}
