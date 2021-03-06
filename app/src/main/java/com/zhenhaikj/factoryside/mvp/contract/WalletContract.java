package com.zhenhaikj.factoryside.mvp.contract;

import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BaseObserver;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.Bill;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.FrozenMoney;
import com.zhenhaikj.factoryside.mvp.bean.MonthBill;
import com.zhenhaikj.factoryside.mvp.bean.UserInfo;

import java.util.List;

import io.reactivex.Observable;

public interface WalletContract {
    interface Model extends BaseModel{
        Observable<BaseResult<UserInfo>> GetUserInfoList(String UserId,String limit);
        Observable<BaseResult<Data<Bill>>> AccountBill(String UserID, String state,String page, String limit);
        Observable<BaseResult<Data<MonthBill>>> MonthBill(String UserID, String state);
        Observable<BaseResult<Data<FrozenMoney>>> GetFrozenMoney(String UserID);
        Observable<BaseResult<Data<String>>> GetRemainMoney(String UserID);
    }

    interface View extends BaseView{
        void GetUserInfoList(BaseResult<UserInfo> baseResult);
        void AccountBill(BaseResult<Data<Bill>> baseResult);
        void MonthBill(BaseResult<Data<MonthBill>> baseResult);
        void GetFrozenMoney(BaseResult<Data<FrozenMoney>> baseResult);
        void GetRemainMoney(BaseResult<Data<String>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void GetUserInfoList(String UserId,String limit);
        public abstract void AccountBill(String UserID,String state,String page, String limit);
        public abstract void MonthBill(String UserID,String state);
        public abstract void GetFrozenMoney(String UserId);
        public abstract void GetRemainMoney(String UserId);
    }
}
