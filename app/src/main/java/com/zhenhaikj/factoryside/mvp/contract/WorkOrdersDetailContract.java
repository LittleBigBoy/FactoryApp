package com.zhenhaikj.factoryside.mvp.contract;


import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.Address;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.WorkOrder;

import java.util.List;

import io.reactivex.Observable;


public interface WorkOrdersDetailContract {
    interface Model extends BaseModel {
        Observable<BaseResult<WorkOrder.DataBean>> GetOrderInfo(String OrderID);
        Observable<BaseResult<Data<String>>> ApplyCustomService(String OrderID);
        Observable<BaseResult<Data<String>>> ApproveOrderAccessory(String OrderID,String AccessoryApplyState,String NewMoney,String OrderAccessoryID);
        Observable<BaseResult<Data<String>>> ApproveBeyondMoney(String OrderID,String BeyondState);
        Observable<BaseResult<Data<String>>> ApproveOrderService(String OrderID,String State);
        Observable<BaseResult<Data<String>>> AddOrUpdateExpressNo(String OrderID,String ExpressNo);

        Observable<BaseResult<Data<String>>> EnSureOrder(String OrderID, String PayPassword);
        Observable<BaseResult<Data<String>>> FactoryEnsureOrder(String OrderID, String PayPassword);
        Observable<BaseResult<Data<String>>> UpdateIsReturnByOrderID(String OrderID, String IsReturn,String AddressBack,String PostPayType);
        Observable<BaseResult<List<Address>>> GetAccountAddress(String UserId);
        Observable<BaseResult<Data<String>>> GetOrderAccessoryMoney(String OrderID);
        Observable<BaseResult<Data<String>>> UpdateFactoryAccessorybyFactory(String Id,String AccessoryName,String AccessoryPrice,String OrderAccessoryId);
        Observable<BaseResult<Data<String>>> ApproveOrderAccessoryByModifyPrice(String OrderID,
                                                                                String AccessoryApplyState,
                                                                                String NewMoney,
                                                                                String OrderAccessoryID);
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

        void EnSureOrder(BaseResult<Data<String>> baseResult);
        void FactoryEnsureOrder(BaseResult<Data<String>> baseResult);
        void UpdateIsReturnByOrderID(BaseResult<Data<String>> baseResult);
        void GetAccountAddress(BaseResult<List<Address>> baseResult);
        void GetOrderAccessoryMoney(BaseResult<Data<String>> baseResult);
        void UpdateFactoryAccessorybyFactory(BaseResult<Data<String>> baseResult);
        void ApproveOrderAccessoryByModifyPrice(BaseResult<Data<String>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetOrderInfo(String OrderID);
        public abstract void ApplyCustomService(String OrderID);
        public abstract void ApproveOrderAccessory(String OrderID,String AccessoryApplyState,String NewMoney,String OrderAccessoryID);
        public abstract void ApproveBeyondMoney(String OrderID,String BeyondState);
        public abstract void ApproveOrderService(String OrderID,String State);
        public abstract void AddOrUpdateExpressNo(String OrderID,String ExpressNo);

        public abstract void EnSureOrder(String OrderID, String PayPassword);
        public abstract void FactoryEnsureOrder(String OrderID, String PayPassword);
        public abstract void UpdateIsReturnByOrderID(String OrderID, String IsReturn,String AddressBack,String PostPayType);
        public abstract void GetAccountAddress(String UserId);
        public abstract void GetOrderAccessoryMoney(String OrderID);
        public abstract void UpdateFactoryAccessorybyFactory(String Id,String AccessoryName,String AccessoryPrice,String OrderAccessoryId);
        public abstract void ApproveOrderAccessoryByModifyPrice(String OrderID,
                                                                String AccessoryApplyState,
                                                                String NewMoney,
                                                                String OrderAccessoryID);
    }
}
