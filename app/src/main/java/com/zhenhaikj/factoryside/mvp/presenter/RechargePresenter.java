package com.zhenhaikj.factoryside.mvp.presenter;


import com.zhenhaikj.factoryside.mvp.base.BaseObserver;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.contract.RechargeContract;

public class RechargePresenter extends RechargeContract.Presenter {


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
}
