package com.zhenhaikj.factoryside.mvp.contract;

import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BaseObserver;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.Data;

import io.reactivex.Observable;
import okhttp3.RequestBody;

public interface VerifiedContract {
    interface Model extends BaseModel{
        Observable<BaseResult<Data<String>>> IDCardUpload(RequestBody json, int code);
        Observable<BaseResult<Data<String>>> FactoryApplyAuthInfo(String UserID,
                                                                  String CompanyName,
                                                                  String CompanyNum,
                                                                  String ManagyName,
                                                                  String ManagyPhone,
                                                                  String ServicePhone,
                                                                  String FinancePhone,
                                                                  String ArtisanPhone,
                                                                  String PhoneUrl,
                                                                  String Province,
                                                                  String City,
                                                                  String Area,
                                                                  String District,
                                                                  String Address,
                                                                  String IfAuth,
                                                                  String IsUse);
    }

    interface View extends BaseView{
        void IDCardUpload(BaseResult<Data<String>> baseResult,int code);
        void FactoryApplyAuthInfo(BaseResult<Data<String>> baseObserver);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void IDCardUpload(RequestBody json,int code);
        public abstract void FactoryApplyAuthInfo(String UserID,
                                                  String CompanyName,
                                                  String CompanyNum,
                                                  String ManagyName,
                                                  String ManagyPhone,
                                                  String ServicePhone,
                                                  String FinancePhone,
                                                  String ArtisanPhone,
                                                  String PhoneUrl,
                                                  String Province,
                                                  String City,
                                                  String Area,
                                                  String District,
                                                  String Address,
                                                  String IfAuth,
                                                  String IsUse);
    }
}
