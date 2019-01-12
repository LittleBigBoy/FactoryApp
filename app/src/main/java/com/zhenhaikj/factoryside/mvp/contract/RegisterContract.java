package com.zhenhaikj.factoryside.mvp.contract;


import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;

import io.reactivex.Observable;
import okhttp3.RequestBody;


public interface RegisterContract {
    interface Model extends BaseModel {
        Observable<BaseResult<String>> Reg(String userName, String code);
        Observable<BaseResult<String>> GetCode(String userName);
        Observable<BaseResult<String>> Login(String userName,String passWord);
        Observable<BaseResult<String>> ValidateUserName(String userName);
    }

    interface View extends BaseView {
        void Reg(BaseResult<String> baseResult);
        void GetCode(BaseResult<String> baseResult);
        void Login(BaseResult<String> baseResult);
        void ValidateUserName(BaseResult<String> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void Reg(String userName,String code);
        public abstract void GetCode(String userName);
        public abstract void  Login(String userName,String passWord);
        public abstract void  ValidateUserName(String userName);
    }
}
