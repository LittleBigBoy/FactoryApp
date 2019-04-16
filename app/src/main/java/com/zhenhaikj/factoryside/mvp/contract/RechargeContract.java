package com.zhenhaikj.factoryside.mvp.contract;

import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.WXpayInfo;

import io.reactivex.Observable;


public interface RechargeContract {
    interface Model extends BaseModel {
        Observable<BaseResult<Data<String>>> GetOrderStr(String userid, String TotalAmount);
        Observable<BaseResult<Data<WXpayInfo>>> GetWXOrderStr(String userid, String TotalAmount);
        Observable<BaseResult<Data<String>>> WXNotifyManual(String OutTradeNo);
    }

    interface View extends BaseView {
        void GetOrderStr(BaseResult<Data<String>> baseResult);
        void GetWXOrderStr(BaseResult<Data<WXpayInfo>> baseResult);
        void WXNotifyManual(BaseResult<Data<String>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetOrderStr(String userid,String TotalAmount);
        public abstract void GetWXOrderStr(String userid,String TotalAmount);
        public abstract void WXNotifyManual(String OutTradeNo);
    }
}
