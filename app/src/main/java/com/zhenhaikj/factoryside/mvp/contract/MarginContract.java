package com.zhenhaikj.factoryside.mvp.contract;

import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.DepositRecharge;
import com.zhenhaikj.factoryside.mvp.bean.DepositWithDraw;
import com.zhenhaikj.factoryside.mvp.bean.UserInfo;
import com.zhenhaikj.factoryside.mvp.bean.WXpayInfo;

import io.reactivex.Observable;

public interface MarginContract {
    interface Model extends BaseModel{
        Observable<BaseResult<Data<String>>> GetOrderStr(String userid, String TotalAmount);
        Observable<BaseResult<Data<WXpayInfo>>> GetWXOrderStr(String userid, String TotalAmount);
        Observable<BaseResult<Data<String>>> WXNotifyManual(String OutTradeNo);
        Observable<BaseResult<UserInfo>> GetUserInfoList(String UserId, String limit);
        Observable<BaseResult<Data<DepositRecharge>>> DepositRechargeList(String UserID, String state);
        Observable<BaseResult<DepositWithDraw>> GetDepositWithDrawList(String UserID, String state);
    }

    interface View extends BaseView{
        void GetOrderStr(BaseResult<Data<String>> baseResult);
        void GetWXOrderStr(BaseResult<Data<WXpayInfo>> baseResult);
        void WXNotifyManual(BaseResult<Data<String>> baseResult);
        void GetUserInfoList(BaseResult<UserInfo> baseResult);
        void DepositRechargeList(BaseResult<Data<DepositRecharge>> baseResult);
        void GetDepositWithDrawList(BaseResult<DepositWithDraw> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void GetOrderStr(String userid,String TotalAmount);
        public abstract void GetWXOrderStr(String userid,String TotalAmount);
        public abstract void WXNotifyManual(String OutTradeNo);
        public abstract void GetUserInfoList(String UserId,String limit);
        public abstract void DepositRechargeList(String UserID, String state);
        public abstract void GetDepositWithDrawList(String UserID, String state);
    }
}
