package com.zhenhaikj.factoryside.mvp.contract;

import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BaseObserver;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.Bill;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.UserInfo;

import io.reactivex.Observable;

public interface WalletContract {
    interface Model extends BaseModel{
        Observable<BaseResult<UserInfo>> GetUserInfoList(String UserId,String limit);
        Observable<BaseResult<Data<Bill>>> AccountBill(String UserID, String state);
    }

    interface View extends BaseView{
        void GetUserInfoList(BaseResult<UserInfo> baseResult);
        void AccountBill(BaseResult<Data<Bill>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void GetUserInfoList(String UserId,String limit);
        public abstract void AccountBill(String UserID,String state);
    }
}
