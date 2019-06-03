package com.zhenhaikj.factoryside.mvp.presenter;

import com.zhenhaikj.factoryside.mvp.base.BaseObserver;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.CompanyInfo;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.User;
import com.zhenhaikj.factoryside.mvp.bean.UserInfo;
import com.zhenhaikj.factoryside.mvp.contract.InfoManageContract;

import okhttp3.RequestBody;

public class InfoManagePresenter extends InfoManageContract.Presenter {
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
    public void UpdateAccountNickName(String UserId, String NickName) {
        mModel.UpdateAccountNickName(UserId,NickName)
                .subscribe(new BaseObserver<Data>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data> value) {
                        mView.UpdateAccountNickName(value);
                    }
                });

    }

    @Override
    public void UpdatePassword(String UserId, String NickName) {
        mModel.UpdatePassword(UserId, NickName)
                .subscribe(new BaseObserver<Data>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data> value) {
                        mView.UpdatePassword(value);
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

    @Override
    public void UpdateSex(String UserId, String Sex) {
        mModel.UpdateSex(UserId, Sex)
                .subscribe(new BaseObserver<Data>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data> value) {
                        mView.UpdateSex(value);
                    }
                });
    }

    @Override
    public void GetmessageBytype(String UserId) {
        mModel.GetmessageBytype(UserId)
                .subscribe(new BaseObserver<Data<CompanyInfo>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<CompanyInfo>> value) {
                        mView.GetmessageBytype(value);
                    }
                });
    }

}
