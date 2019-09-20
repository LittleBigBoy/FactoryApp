package com.zhenhaikj.factoryside.mvp.presenter;

import com.zhenhaikj.factoryside.mvp.base.BaseObserver;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.DepositRecharge;
import com.zhenhaikj.factoryside.mvp.bean.DepositWithDraw;
import com.zhenhaikj.factoryside.mvp.bean.UserInfo;
import com.zhenhaikj.factoryside.mvp.bean.WXpayInfo;
import com.zhenhaikj.factoryside.mvp.contract.MarginContract;

public class MarginPresenter extends MarginContract.Presenter {

    @Override
    public void GetOrderStr(String userid, String TotalAmount) {
        mModel.GetOrderStr(userid, TotalAmount)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.GetOrderStr(value);
                    }
                });
    }

    @Override
    public void GetWXOrderStr(String userid, String TotalAmount) {
        mModel.GetWXOrderStr(userid, TotalAmount)
                .subscribe(new BaseObserver<Data<WXpayInfo>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<WXpayInfo>> value) {
                        mView.GetWXOrderStr(value);
                    }
                });
    }
    @Override
    public void WXNotifyManual(String OutTradeNo) {
        mModel.WXNotifyManual(OutTradeNo)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.WXNotifyManual(value);
                    }
                });
    }

    @Override
    public void GetUserInfoList(String UserId, String limit) {
        mModel.GetUserInfoList(UserId, limit)
                .subscribe(new BaseObserver<UserInfo>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<UserInfo> value) {
                        mView.GetUserInfoList(value);
                    }
                });
    }

    @Override
    public void DepositRechargeList(String UserID, String state) {
        mModel.DepositRechargeList(UserID, state)
                .subscribe(new BaseObserver<Data<DepositRecharge>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<DepositRecharge>> value) {
                        mView.DepositRechargeList(value);
                    }
                });
    }

    @Override
    public void GetDepositWithDrawList(String UserID, String state) {
        mModel.GetDepositWithDrawList(UserID, state)
                .subscribe(new BaseObserver<DepositWithDraw>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<DepositWithDraw> value) {
                        mView.GetDepositWithDrawList(value);
                    }
                });
    }
}
