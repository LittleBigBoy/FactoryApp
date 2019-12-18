package com.zhenhaikj.factoryside.mvp.model;


import com.zhenhaikj.factoryside.mvp.ApiRetrofit;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.contract.ForgetPasswordContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class ForgetPasswordModel implements ForgetPasswordContract.Model {
    @Override
    public Observable<BaseResult<Data<String>>> Login(String userName, String passWord) {
        return ApiRetrofit.getDefault().LoginOn(userName,passWord,"7")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> AddAndUpdatePushAccount(String token, String type, String UserID) {
        return ApiRetrofit.getDefault().AddAndUpdatePushAccount(token,type,UserID)
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
    public Observable<BaseResult<Data<String>>> GetCode(String userName, String type) {
        return ApiRetrofit.getDefault().GetCode(userName,type,"worker")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

//    @Override
//    public Observable<BaseResult<Data<String>>> LoginOnMessage(String mobile, String code) {
//        return ApiRetrofit.getDefault().LoginOnMessage(mobile,code,"3","6")
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io());
//    }

    @Override
    public Observable<BaseResult<Data<String>>> ForgetPassword(String mobile, String type, String code, String password) {
        return ApiRetrofit.getDefault().ForgetPassword(mobile,type,code,password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }


}
