package com.zhenhaikj.factoryside.mvp.presenter;

import com.zhenhaikj.factoryside.mvp.base.BaseObserver;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.BankCard;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.UserInfo;
import com.zhenhaikj.factoryside.mvp.contract.WithdrawContract;

import java.util.List;

public class WithdrawPresenter extends WithdrawContract.Presenter {

    @Override
    public void GetBankNameByCardNo(String CardNo) {
        mModel.GetBankNameByCardNo(CardNo)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.GetBankNameByCardNo(value);
                    }
                });
    }

    @Override
    public void WithDrawDeposit(String DrawMoney, String CardNo, String UserID, String CardName) {
        mModel.WithDrawDeposit(DrawMoney, CardNo, UserID, CardName)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.WithDrawDeposit(value);
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
    public void AddorUpdateAccountPayInfo(String UserId, String PayInfoCode, String PayInfoName,String PayNo,String PayName) {
        mModel.AddorUpdateAccountPayInfo(UserId, PayInfoCode,PayInfoName,PayNo,PayName)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.AddorUpdateAccountPayInfo(value);
                    }
                });
    }

    @Override
    public void GetAccountPayInfoList(String UserId) {
        mModel.GetAccountPayInfoList(UserId)
                .subscribe(new BaseObserver<List<BankCard>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<List<BankCard>> value) {
                        mView.GetAccountPayInfoList(value);
                    }
                });
    }
}
