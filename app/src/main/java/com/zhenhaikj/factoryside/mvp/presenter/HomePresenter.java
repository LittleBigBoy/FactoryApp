package com.zhenhaikj.factoryside.mvp.presenter;


import com.zhenhaikj.factoryside.mvp.base.BaseObserver;
import com.zhenhaikj.factoryside.mvp.bean.CompanyInfo;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.Search;
import com.zhenhaikj.factoryside.mvp.bean.UserInfo;
import com.zhenhaikj.factoryside.mvp.bean.WXpayInfo;
import com.zhenhaikj.factoryside.mvp.contract.HomeContract;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.HomeData;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class HomePresenter extends HomeContract.Presenter {

    @Override
    public void getData(String userid) {
        mModel.getData(userid)
                .subscribe(new Observer<BaseResult<HomeData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult<HomeData> baseResult) {
                        if ("200".equals(baseResult.getStatusCode())){
                            mView.hideProgress();
                            mView.success(baseResult);
                        }else{

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.hideProgress();
                    }

                    @Override
                    public void onComplete() {

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
    public void GetRemainMoney(String UserId) {
        mModel.GetRemainMoney(UserId)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.GetRemainMoney(value);
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
