package com.zhenhaikj.factoryside.mvp.presenter;

import com.zhenhaikj.factoryside.mvp.base.BaseObserver;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.Logistics;
import com.zhenhaikj.factoryside.mvp.bean.WorkOrder;
import com.zhenhaikj.factoryside.mvp.contract.ExpressInfoContract;

import java.util.List;

public class ExpressInfoPresenter extends ExpressInfoContract.Presenter {
    @Override
    public void GetExpressInfo(String ExpressNo) {
        mModel.GetExpressInfo(ExpressNo)
                .subscribe(new BaseObserver<Data<Logistics>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<Logistics>> value) {
                        mView.GetExpressInfo(value);
                    }
                });
    }

    @Override
    public void GetOrderInfo(String OrderID) {
        mModel.GetOrderInfo(OrderID).subscribe(new BaseObserver<WorkOrder.DataBean>() {
            @Override
            protected void onHandleSuccess(BaseResult<WorkOrder.DataBean> value) {
                mView.GetOrderInfo(value);
            }
        });
    }

}
