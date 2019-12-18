package com.zhenhaikj.factoryside.mvp.model;

import com.zhenhaikj.factoryside.mvp.ApiRetrofit;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.contract.RegisterContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class RegisterModel implements RegisterContract.Model {


    @Override
    public Observable<BaseResult<Data<String>>> Reg(String userName, String code,String password) {
        return ApiRetrofit.getDefault().Reg(userName,"1",code,"factory",password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> GetCode(String userName,String type) {
        return ApiRetrofit.getDefault().GetCode(userName,"Reg","factory")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> Login(String userName, String passWord) {
        return ApiRetrofit.getDefault().LoginOn(userName,passWord,"6")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<String>> ValidateUserName(String userName) {
        return ApiRetrofit.getDefault().ValidateUserName(userName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
    @Override
    public Observable<BaseResult<Data<String>>> AddAndUpdatePushAccount(String token, String type, String UserID) {
        return ApiRetrofit.getDefault().AddAndUpdatePushAccount(token, type, UserID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
