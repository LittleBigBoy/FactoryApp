package com.zhenhaikj.factoryside.mvp.contract;


import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.Data;

import io.reactivex.Observable;
import okhttp3.RequestBody;


public interface RegisterContract {
    interface Model extends BaseModel {
        Observable<BaseResult<Data<String>>> Reg(String userName, String code,String password);
        Observable<BaseResult<Data<String>>> GetCode(String userName,String type);
        Observable<BaseResult<Data<String>>> Login(String userName,String passWord);
        Observable<BaseResult<String>> ValidateUserName(String userName);
        Observable<BaseResult<Data<String>>> AddAndUpdatePushAccount(String token, String type, String UserID);
    }

    interface View extends BaseView {
        void Reg(BaseResult<Data<String>> baseResult);
        void GetCode(BaseResult<Data<String>> baseResult);
        void Login(BaseResult<Data<String>> baseResult);
        void ValidateUserName(BaseResult<String> baseResult);
        void AddAndUpdatePushAccount(BaseResult<Data<String>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void Reg(String userName,String code,String password);
        public abstract void GetCode(String userName,String type);
        public abstract void  Login(String userName,String passWord);
        public abstract void  ValidateUserName(String userName);
        public abstract void  AddAndUpdatePushAccount(String token, String type, String UserID);
    }
}
