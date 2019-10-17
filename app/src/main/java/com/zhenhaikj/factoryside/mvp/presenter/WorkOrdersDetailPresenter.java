package com.zhenhaikj.factoryside.mvp.presenter;


import com.zhenhaikj.factoryside.mvp.base.BaseObserver;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Address;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.WorkOrder;
import com.zhenhaikj.factoryside.mvp.contract.WorkOrdersDetailContract;

import java.util.List;

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
    public void ApproveOrderAccessory(String OrderID, String AccessoryApplyState,String NewMoney,String OrderAccessoryID) {
        mModel.ApproveOrderAccessory(OrderID, AccessoryApplyState,NewMoney,OrderAccessoryID)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.ApproveOrderAccessory(value);
                    }
                });
    }

    @Override
    public void ApproveBeyondMoney(String OrderID, String BeyondState,String BeyondMoney) {
        mModel.ApproveBeyondMoney(OrderID, BeyondState,BeyondMoney)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.ApproveBeyondMoney(value);
                    }
                });
    }

    @Override
    public void ApproveOrderService(String OrderID, String State,String OrderServiceID) {
        mModel.ApproveOrderService(OrderID, State,OrderServiceID)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.ApproveOrderService(value);
                    }
                });
    }

    @Override
    public void ApproveOrderAccessoryAndService(String OrderID, String AccessoryAndServiceApplyState, String PostPayType, String IsReturn) {
        mModel.ApproveOrderAccessoryAndService(OrderID, AccessoryAndServiceApplyState,PostPayType,IsReturn)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.ApproveOrderAccessoryAndService(value);
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

    @Override
    public void UpdateIsReturnByOrderID(String OrderID, String IsReturn, String AddressBack, String PostPayType) {
        mModel.UpdateIsReturnByOrderID(OrderID, IsReturn, AddressBack, PostPayType)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.UpdateIsReturnByOrderID(value);
                    }
                });
    }
    @Override
    public void GetAccountAddress(String UserId) {
        mModel.GetAccountAddress(UserId)
                .subscribe(new BaseObserver<List<Address>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<List<Address>> value) {
                        mView.GetAccountAddress(value);
                    }
                });
    }

    @Override
    public void GetOrderAccessoryMoney(String OrderID) {
        mModel.GetOrderAccessoryMoney(OrderID)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.GetOrderAccessoryMoney(value);
                    }
                });
    }

    @Override
    public void UpdateFactoryAccessorybyFactory(String Id, String AccessoryName, String AccessoryPrice, String OrderAccessoryId) {
        mModel.UpdateFactoryAccessorybyFactory(Id, AccessoryName, AccessoryPrice, OrderAccessoryId)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.UpdateFactoryAccessorybyFactory(value);
                    }
                });
    }

    @Override
    public void ApproveOrderAccessoryByModifyPrice(String OrderID, String AccessoryApplyState, String NewMoney, String OrderAccessoryID) {
        mModel.ApproveOrderAccessoryByModifyPrice(OrderID, AccessoryApplyState, NewMoney, OrderAccessoryID)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.ApproveOrderAccessoryByModifyPrice(value);
                    }
                });
    }


    @Override
    public void NowEnSureOrder(String OrderID) {
        mModel.NowEnSureOrder(OrderID)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.NowEnSureOrder(value);
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
}
