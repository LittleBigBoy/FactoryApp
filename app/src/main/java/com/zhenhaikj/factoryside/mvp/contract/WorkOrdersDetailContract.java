package com.zhenhaikj.factoryside.mvp.contract;


import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.Address;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.UserInfo;
import com.zhenhaikj.factoryside.mvp.bean.WorkOrder;
import com.zhenhaikj.factoryside.mvp.widget.OrderFreezing;

import java.util.List;

import io.reactivex.Observable;


public interface WorkOrdersDetailContract {
    interface Model extends BaseModel {
        Observable<BaseResult<WorkOrder.DataBean>> GetOrderInfo(String OrderID);
        Observable<BaseResult<Data<String>>> ApplyCustomService(String OrderID);
        Observable<BaseResult<Data<String>>> ApproveOrderAccessory(String OrderID,String AccessoryApplyState,String NewMoney,String OrderAccessoryID);
        Observable<BaseResult<Data<String>>> ApproveBeyondMoney(String OrderID,String BeyondState,String BeyondMoney);
        Observable<BaseResult<Data<String>>> ApproveOrderService(String OrderID,String State,String OrderServiceID);
        Observable<BaseResult<Data<String>>> ApproveOrderAccessoryAndService(String OrderID,String AccessoryAndServiceApplyState, String PostPayType, String IsReturn);
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
        Observable<BaseResult<Data<String>>> NowEnSureOrder(String OrderID);

        Observable<BaseResult<Data<String>>> FactoryComplaint(String OrderID, String Content,String ComplaintType);
        Observable<BaseResult<Data<String>>> NowPayEnSureOrder(String OrderID, String PayPassword);
        Observable<BaseResult<UserInfo>> GetUserInfoList(String UserId, String limit);
        Observable<BaseResult<Data<List<OrderFreezing>>>> getOrderFreezing(String OrderID);
        Observable<BaseResult<Data<String>>> ApplyCancelOrder(String OrderID);

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
        void ApproveOrderAccessoryAndService(BaseResult<Data<String>> baseResult);
        void AddOrUpdateExpressNo(BaseResult<Data<String>> baseResult);

        void EnSureOrder(BaseResult<Data<String>> baseResult);
        void FactoryEnsureOrder(BaseResult<Data<String>> baseResult);
        void UpdateIsReturnByOrderID(BaseResult<Data<String>> baseResult);
        void GetAccountAddress(BaseResult<List<Address>> baseResult);
        void GetOrderAccessoryMoney(BaseResult<Data<String>> baseResult);
        void UpdateFactoryAccessorybyFactory(BaseResult<Data<String>> baseResult);
        void ApproveOrderAccessoryByModifyPrice(BaseResult<Data<String>> baseResult);
        void NowEnSureOrder(BaseResult<Data<String>> baseResult);
        void FactoryComplaint(BaseResult<Data<String>> baseResult);
        void NowPayEnSureOrder(BaseResult<Data<String>> baseResult);
        void GetUserInfoList(BaseResult<UserInfo> baseResult);
        void getOrderFreezing(BaseResult<Data<List<OrderFreezing>>>baseResult);
        void ApplyCancelOrder(BaseResult<Data<String>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetOrderInfo(String OrderID);
        public abstract void ApplyCustomService(String OrderID);
        public abstract void ApproveOrderAccessory(String OrderID,String AccessoryApplyState,String NewMoney,String OrderAccessoryID);
        public abstract void ApproveBeyondMoney(String OrderID,String BeyondState,String BeyondMoney);
        public abstract void ApproveOrderService(String OrderID,String State,String OrderServiceID);
        public abstract void ApproveOrderAccessoryAndService(String OrderID,String AccessoryAndServiceApplyState, String PostPayType, String IsReturn);
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
        public abstract void NowEnSureOrder(String OrderID);
        public abstract void FactoryComplaint(String OrderID,String Content,String ComplaintType);
        public abstract void NowPayEnSureOrder(String OrderID, String PayPassword);
        public abstract void GetUserInfoList(String UserId,String limit);
        public abstract void getOrderFreezing(String OrderID);
        public abstract void ApplyCancelOrder(String OrderID);
    }
}
