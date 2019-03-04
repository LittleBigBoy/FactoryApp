package com.zhenhaikj.factoryside.mvp.contract;

import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BaseObserver;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.UserInfo;

import io.reactivex.Observable;


public interface InfoManageContract {
    interface Model extends BaseModel{
        Observable<BaseResult<UserInfo>> GetUserInfoList(String UserID, String limit);
        Observable<BaseResult<Data>> UpdateAccountNickName(String UserId, String NickName);
        Observable<BaseResult<Data>> UpdatePassword(String UserId,String Password);
    }

    interface View extends BaseView{
        void GetUserInfoList(BaseResult<UserInfo> baseResult);
        void UpdateAccountNickName(BaseResult<Data> baseResult);
        void UpdatePassword(BaseResult<Data> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void GetUserInfoList(String UserId,String limit);
        public abstract void UpdateAccountNickName(String UserId,String NickName);
        public abstract void UpdatePassword(String UserId,String NickName);
    }
}
