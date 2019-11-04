package com.zhenhaikj.factoryside.mvp.presenter;

import com.zhenhaikj.factoryside.mvp.base.BaseObserver;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Bill;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.FrozenMoney;
import com.zhenhaikj.factoryside.mvp.bean.MonthBill;
import com.zhenhaikj.factoryside.mvp.bean.UserInfo;
import com.zhenhaikj.factoryside.mvp.contract.WalletContract;

import java.util.List;

public class WalletPresenter extends WalletContract.Presenter {
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
    public void AccountBill(String UserID,String state,String page, String limit) {
        mModel.AccountBill(UserID,state,page,limit)
                .subscribe(new BaseObserver<Data<Bill>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<Bill>> value) {
                        mView.AccountBill(value);
                    }
                });
    }

    @Override
    public void MonthBill(String UserID, String state) {
        mModel.MonthBill(UserID, state)
                .subscribe(new BaseObserver<Data<MonthBill>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<MonthBill>> value) {
                        mView.MonthBill(value);
                    }
                });
    }

    @Override
    public void GetFrozenMoney(String UserId) {
        mModel.GetFrozenMoney(UserId)
                .subscribe(new BaseObserver<Data<FrozenMoney>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<FrozenMoney>> value) {
                        mView.GetFrozenMoney(value);
                    }
                });
    }

    @Override
    public void GetRemainMoney(String UserId) {
        mModel.GetRemainMoney(UserId)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.GetRemainMoney(value);
                    }
                });
    }
}
