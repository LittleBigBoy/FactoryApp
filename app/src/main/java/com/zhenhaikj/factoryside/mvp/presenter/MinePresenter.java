package com.zhenhaikj.factoryside.mvp.presenter;

import com.zhenhaikj.factoryside.mvp.base.BaseObserver;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.CompanyInfo;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.Search;
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

    @Override
    public void GetOrderInfoList(String Phone, String OrderID,String UserID ,String limit, String page) {
        mModel.GetOrderInfoList(Phone, OrderID, UserID,limit, page)
                .subscribe(new BaseObserver<Search>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Search> value) {
                        mView.GetOrderInfoList(value);
                    }
                });
    }

    @Override
    public void GetUserOrderNum(String UserID) {
        mModel.GetUserOrderNum(UserID)
                .subscribe(new BaseObserver<Search>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Search> value) {
                        mView.GetUserOrderNum(value);
                    }
                });
    }

}
