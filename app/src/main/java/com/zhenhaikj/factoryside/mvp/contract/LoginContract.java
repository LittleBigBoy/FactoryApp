package com.zhenhaikj.factoryside.mvp.contract;


import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.HomeData;

import io.reactivex.Observable;
import okhttp3.RequestBody;


public interface LoginContract {
    interface Model extends BaseModel {
        Observable<BaseResult<Data<String>>> Login(String userName, String passWord);
        Observable<BaseResult<String>> GetUserInfo(RequestBody json);
        Observable<BaseResult<String>> GetUserInfo(String userName);
        Observable<BaseResult<Data<String>>> AddAndUpdatePushAccount(String token,String type,String UserID);
    }

    interface View extends BaseView {
        void Login(BaseResult<Data<String>> baseResult);
        void GetUserInfo(BaseResult<String> baseResult);
        void AddAndUpdatePushAccount(BaseResult<Data<String>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void Login(String userName,String passWord);
        public abstract void GetUserInfo(RequestBody json);
        public abstract void GetUserInfo(String userName);
        public abstract void AddAndUpdatePushAccount(String token,String type,String UserID);
    }
}
