package com.zhenhaikj.factoryside.mvp.contract;


import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.HomeData;
import com.zhenhaikj.factoryside.mvp.bean.UserInfo;

import io.reactivex.Observable;
import okhttp3.RequestBody;


public interface LoginContract {
    interface Model extends BaseModel {
        Observable<BaseResult<Data<String>>> Login(String userName, String passWord);
        Observable<BaseResult<String>> GetUserInfo(RequestBody json);
        Observable<BaseResult<String>> GetUserInfo(String userName);
        Observable<BaseResult<Data<String>>> AddAndUpdatePushAccount(String token,String type,String UserID);
        Observable<BaseResult<String>> ValidateUserName(String userName);
        Observable<BaseResult<Data<String>>> GetCode(String mobile,String type);
        Observable<BaseResult<Data<String>>> LoginOnMessage(String mobile,String code);
        Observable<BaseResult<Data<String>>> LoginOut(String UserID);
        Observable<BaseResult<Data<String>>> barCode(String UserID,String barCode);
        Observable<BaseResult<UserInfo>> GetUserInfoList(String UserId, String limit);
    }

    interface View extends BaseView {
        void Login(BaseResult<Data<String>> baseResult);
        void GetUserInfo(BaseResult<String> baseResult);
        void AddAndUpdatePushAccount(BaseResult<Data<String>> baseResult);
        void ValidateUserName(BaseResult<String> baseResult);
        void GetCode(BaseResult<Data<String>> baseResult);
        void LoginOnMessage(BaseResult<Data<String>> baseResult);
        void LoginOut(BaseResult<Data<String>> baseResult);
        void barCode(BaseResult<Data<String>> baseResult);
        void GetUserInfoList(BaseResult<UserInfo> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void Login(String userName,String passWord);
        public abstract void GetUserInfo(RequestBody json);
        public abstract void GetUserInfo(String userName);
        public abstract void AddAndUpdatePushAccount(String token,String type,String UserID);
        public abstract void ValidateUserName(String userName);
        public abstract void GetCode(String mobile,String type);
        public abstract void LoginOnMessage(String mobile,String code);
        public abstract void LoginOut(String UserID);
        public abstract void barCode(String UserID,String barCode);
        public abstract void GetUserInfoList(String UserId,String limit);
    }
}
