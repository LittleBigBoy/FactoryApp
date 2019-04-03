package com.zhenhaikj.factoryside.mvp.presenter;


import com.zhenhaikj.factoryside.mvp.base.BaseObserver;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.WorkOrder;
import com.zhenhaikj.factoryside.mvp.contract.WorkOrdersDetailContract;

public class WorkOrdersDetailPresenter extends WorkOrdersDetailContract.Presenter {


    @Override
    public void GetOrderInfo(String OrderID) {
        mModel.GetOrderInfo(OrderID)
                .subscribe(new BaseObserver<WorkOrder.DataBean>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<WorkOrder.DataBean> value) {
                        mView.GetOrderInfo(value);
                    }
                });
    }

    @Override
    public void ApplyCustomService(String OrderID) {
        mModel.ApplyCustomService(OrderID)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.ApplyCustomService(value);
                    }
                });
    }

    @Override
    public void ApproveOrderAccessory(String OrderID, String AccessoryApplyState) {
        mModel.ApproveOrderAccessory(OrderID, AccessoryApplyState)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.ApproveOrderAccessory(value);
                    }
                });
    }

    @Override
    public void ApproveBeyondMoney(String OrderID, String BeyondState) {
        mModel.ApproveBeyondMoney(OrderID, BeyondState)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.ApproveBeyondMoney(value);
                    }
                });
    }

    @Override
    public void ApproveOrderService(String OrderID, String State) {
        mModel.ApproveOrderService(OrderID, State)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.ApproveOrderService(value);
                    }
                });
    }

    @Override
    public void AddOrUpdateExpressNo(String OrderID, String ExpressNo) {
        mModel.AddOrUpdateExpressNo(OrderID, ExpressNo).subscribe(new BaseObserver<Data<String>>() {
            @Override
            protected void onHandleSuccess(BaseResult<Data<String>> value) {
                mView.AddOrUpdateExpressNo(value);
            }
        });
    }
    @Override
    public void EnSureOrder(String OrderID, String PayPassword) {
        mModel.EnSureOrder(OrderID, PayPassword)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.EnSureOrder(value);
                    }
                });
    }
    @Override
    public void FactoryEnsureOrder(String OrderID, String PayPassword) {
        mModel.FactoryEnsureOrder(OrderID, PayPassword)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.FactoryEnsureOrder(value);
                    }
                });
    }
}
