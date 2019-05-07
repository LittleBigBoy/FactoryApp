package com.zhenhaikj.factoryside.mvp.presenter;

import com.zhenhaikj.factoryside.mvp.base.BaseObserver;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Data;
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
}
