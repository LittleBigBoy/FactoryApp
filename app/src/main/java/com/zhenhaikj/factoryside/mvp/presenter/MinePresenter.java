package com.zhenhaikj.factoryside.mvp.presenter;

import com.zhenhaikj.factoryside.mvp.base.BaseObserver;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.UserInfo;
import com.zhenhaikj.factoryside.mvp.contract.MineContract;

import okhttp3.RequestBody;

public class MinePresenter extends MineContract.Presenter {
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
    public void UploadAvator(RequestBody josn) {
        mModel.UploadAvator(josn)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.UploadAvator(value);
                    }
                });
    }
}