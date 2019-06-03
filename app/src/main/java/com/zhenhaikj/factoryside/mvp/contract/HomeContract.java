package com.zhenhaikj.factoryside.mvp.contract;


import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.CompanyInfo;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.HomeData;
import com.zhenhaikj.factoryside.mvp.bean.UserInfo;
import com.zhenhaikj.factoryside.mvp.bean.WXpayInfo;

import io.reactivex.Observable;


public interface HomeContract {
    interface Model extends BaseModel {
        Observable<BaseResult<HomeData>> getData(String userid);
        Observable<BaseResult<UserInfo>> GetUserInfoList(String UserId, String limit);
        Observable<BaseResult<Data<String>>> GetOrderStr(String userid, String TotalAmount);
        Observable<BaseResult<Data<WXpayInfo>>> GetWXOrderStr(String userid, String TotalAmount);
        Observable<BaseResult<Data<String>>> WXNotifyManual(String OutTradeNo);
        Observable<BaseResult<Data<CompanyInfo>>> GetmessageBytype(String UserId);
    }

    interface View extends BaseView {
        void success(BaseResult<HomeData> baseResult);
        void GetUserInfoList(BaseResult<UserInfo> baseResult);
        void GetOrderStr(BaseResult<Data<String>> baseResult);
        void GetWXOrderStr(BaseResult<Data<WXpayInfo>> baseResult);
        void WXNotifyManual(BaseResult<Data<String>> baseResult);
        void GetmessageBytype(BaseResult<Data<CompanyInfo>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void getData(String userid);
        public abstract void GetUserInfoList(String UserId,String limit);
        public abstract void GetOrderStr(String userid,String TotalAmount);
        public abstract void GetWXOrderStr(String userid,String TotalAmount);
        public abstract void WXNotifyManual(String OutTradeNo);
        public abstract void GetmessageBytype(String UserId);
    }
}
