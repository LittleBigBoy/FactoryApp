package com.zhenhaikj.factoryside.mvp.contract;


import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.WorkOrder;

import io.reactivex.Observable;


public interface AllWorkOrdersContract {
    interface Model extends BaseModel {
        Observable<BaseResult<WorkOrder>> GetOrderInfoList(String UserID,String state, String page, String limit);
        Observable<BaseResult<Data<String>>> UpdateOrderState(String OrderID, String Content);
        Observable<BaseResult<Data<String>>> FactoryComplaint(String OrderID, String Content);
        Observable<BaseResult<Data<String>>> EnSureOrder(String OrderID, String PayPassword);
        Observable<BaseResult<Data<String>>> FactoryEnsureOrder(String OrderID, String PayPassword);
        Observable<BaseResult<Data<String>>> UpdateOrderFIsLook(String OrderID, String IsLook,String FIsLook);
        Observable<BaseResult<Data<String>>> ApplyCancelOrder(String OrderID);
    }

    interface View extends BaseView {
        void GetOrderInfoList(BaseResult<WorkOrder> baseResult);
        void UpdateOrderState(BaseResult<Data<String>> baseResult);
        void FactoryComplaint(BaseResult<Data<String>> baseResult);
        void EnSureOrder(BaseResult<Data<String>> baseResult);
        void FactoryEnsureOrder(BaseResult<Data<String>> baseResult);
        void UpdateOrderFIsLook(BaseResult<Data<String>> baseResult);
        void ApplyCancelOrder(BaseResult<Data<String>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetOrderInfoList(String UserID,String state, String page,String limit);
        public abstract void UpdateOrderState(String OrderID,String Content);
        public abstract void FactoryComplaint(String OrderID,String Content);
        public abstract void EnSureOrder(String OrderID, String PayPassword);
        public abstract void FactoryEnsureOrder(String OrderID, String PayPassword);
        public abstract void UpdateOrderFIsLook(String OrderID, String IsLook,String FIsLook);
        public abstract void ApplyCancelOrder(String OrderID);
    }
}
