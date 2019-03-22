package com.zhenhaikj.factoryside.mvp.contract;


import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.WorkOrder;

import io.reactivex.Observable;


public interface WorkOrdersDetailContract {
    interface Model extends BaseModel {
        Observable<BaseResult<WorkOrder.DataBean>> GetOrderInfo(String OrderID);
        Observable<BaseResult<Data<String>>> ApplyCustomService(String OrderID);
        Observable<BaseResult<Data<String>>> ApproveOrderAccessory(String OrderID,String AccessoryApplyState);
        Observable<BaseResult<Data<String>>> ApproveBeyondMoney(String OrderID,String BeyondState);
        Observable<BaseResult<Data<String>>> ApproveOrderService(String OrderID,String State);
        Observable<BaseResult<Data<String>>> AddOrUpdateExpressNo(String OrderID,String ExpressNo);
    }

    interface View extends BaseView {
        void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult);

        /**
         * 发起质保
         * @param baseResult
         */
        void ApplyCustomService(BaseResult<Data<String>> baseResult);

        /**
         * 审核派件单
         * @param baseResult
         */
        void ApproveOrderAccessory(BaseResult<Data<String>> baseResult);

        /**
         * 审核远程费
         * @param baseResult
         */
        void ApproveBeyondMoney(BaseResult<Data<String>> baseResult);
         /**
         * 审核服务
         * @param baseResult
         */
        void ApproveOrderService(BaseResult<Data<String>> baseResult);
        void AddOrUpdateExpressNo(BaseResult<Data<String>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetOrderInfo(String OrderID);
        public abstract void ApplyCustomService(String OrderID);
        public abstract void ApproveOrderAccessory(String OrderID,String AccessoryApplyState);
        public abstract void ApproveBeyondMoney(String OrderID,String BeyondState);
        public abstract void ApproveOrderService(String OrderID,String State);
        public abstract void AddOrUpdateExpressNo(String OrderID,String ExpressNo);
    }
}
