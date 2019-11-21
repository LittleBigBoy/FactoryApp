package com.zhenhaikj.factoryside.mvp.contract;

import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.LeaveMessage;

import io.reactivex.Observable;


public interface LeaveMeaasgeContract {
    interface Model extends BaseModel {
        Observable<BaseResult<Data<LeaveMessage>>> GetNewsLeaveMessage(String UserID, String limit, String page);
        Observable<BaseResult<Data>> LeaveMessageWhetherLook(String OrderID);
    }

    interface View extends BaseView {
        void GetNewsLeaveMessage(BaseResult<Data<LeaveMessage>> baseResult);
        void LeaveMessageWhetherLook(BaseResult<Data> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetNewsLeaveMessage(String UserID, String limit, String page);
        public abstract void LeaveMessageWhetherLook(String OrderID);
    }
}
