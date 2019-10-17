package com.zhenhaikj.factoryside.mvp.presenter;


import com.zhenhaikj.factoryside.mvp.base.BaseObserver;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.WorkOrder;
import com.zhenhaikj.factoryside.mvp.contract.AllWorkOrdersContract;

public class AllWorkOrdersPresenter extends AllWorkOrdersContract.Presenter {

    @Override
    public void GetOrderInfoList(String UserID,String state, String page, String limit) {
        mModel.GetOrderInfoList(UserID,state, page, limit)
                .subscribe(new BaseObserver<WorkOrder>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<WorkOrder> value) {
                        mView.GetOrderInfoList(value);
                    }
                });
    }

    @Override
    public void UpdateOrderState(String OrderID, String State) {
        mModel.UpdateOrderState(OrderID,State)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.UpdateOrderState(value);
                    }
                });
    }

    @Override
    public void FactoryComplaint(String OrderID, String Content) {
        mModel.FactoryComplaint(OrderID, Content)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.FactoryComplaint(value);
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

    @Override
    public void UpdateOrderFIsLook(String OrderID, String IsLook, String FIsLook) {
        mModel.UpdateOrderFIsLook(OrderID, IsLook,FIsLook)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.UpdateOrderFIsLook(value);
                    }
                });
    }

    @Override
    public void ApplyCancelOrder(String OrderID) {
        mModel.ApplyCancelOrder(OrderID)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.ApplyCancelOrder(value);
                    }
                });
    }

    @Override
    public void GetFStarOrder(String OrderID, String FStarOrder) {
        mModel.GetFStarOrder(OrderID, FStarOrder)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.GetFStarOrder(value);
                    }
                });
    }
}
