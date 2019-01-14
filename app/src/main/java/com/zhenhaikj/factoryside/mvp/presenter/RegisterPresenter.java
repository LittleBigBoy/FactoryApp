package com.zhenhaikj.factoryside.mvp.presenter;


import com.zhenhaikj.factoryside.mvp.base.BaseObserver;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.contract.RegisterContract;


public class RegisterPresenter extends RegisterContract.Presenter {

    @Override
    public void Reg(String userName, String code) {
        mModel.Reg(userName,code)
                .subscribe(new BaseObserver<String>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<String> value) {
                        mView.Reg(value);
                    }
                });
    }

    @Override
    public void GetCode(String userName) {
        mModel.GetCode(userName)
                .subscribe(new BaseObserver<String>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<String> value) {
                        mView.GetCode(value);
                    }
                });
    }

    @Override
    public void Login(String userName, String passWord) {
        mModel.Login(userName,passWord)
                .subscribe(new BaseObserver<String>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<String> value) {
                        mView.Login(value);
                    }
                });
    }

    @Override
    public void ValidateUserName(String userName) {
        mModel.ValidateUserName(userName)
                .subscribe(new BaseObserver<String>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<String> value) {
                        mView.ValidateUserName(value);
                    }
                });
    }
}