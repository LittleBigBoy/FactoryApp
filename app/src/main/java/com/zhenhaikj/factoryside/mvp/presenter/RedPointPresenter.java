package com.zhenhaikj.factoryside.mvp.presenter;


import com.zhenhaikj.factoryside.mvp.base.BaseObserver;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.RedPointData;
import com.zhenhaikj.factoryside.mvp.contract.RedPointContract;


public class RedPointPresenter extends RedPointContract.Presenter {


    @Override
    public void FactoryGetOrderRed(String UserID) {
        mModel.FactoryGetOrderRed(UserID)
                .subscribe(new BaseObserver<RedPointData>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<RedPointData> value) {
                        mView.FactoryGetOrderRed(value);
                    }
                });
    }
}
