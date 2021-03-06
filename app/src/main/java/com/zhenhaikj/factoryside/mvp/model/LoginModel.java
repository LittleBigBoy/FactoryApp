package com.zhenhaikj.factoryside.mvp.model;

import com.huawei.hms.api.Api;
import com.zhenhaikj.factoryside.mvp.ApiRetrofit;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.UserInfo;
import com.zhenhaikj.factoryside.mvp.contract.LoginContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;


public class LoginModel implements LoginContract.Model {
    @Override
    public Observable<BaseResult<Data<String>>> Login(String userName, String passWord) {
        return ApiRetrofit.getDefault().LoginOn(userName,passWord,"6")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<String>> GetUserInfo(RequestBody json) {
        return ApiRetrofit.getDefault().GetUserInfo(json)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<String>> GetUserInfo(String userName) {
        return ApiRetrofit.getDefault().GetUserInfo(userName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> AddAndUpdatePushAccount(String token, String type, String UserID) {
        return ApiRetrofit.getDefault().AddAndUpdatePushAccount(token, type, UserID)
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
    public Observable<BaseResult<Data<String>>> GetCode(String mobile, String type) {
        return ApiRetrofit.getDefault().GetCode(mobile,type,"factory")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());

    }

    @Override
    public Observable<BaseResult<Data<String>>> LoginOnMessage(String mobile, String code) {
        return ApiRetrofit.getDefault().LoginOnMessage(mobile,code,"3","6")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
    @Override
    public Observable<BaseResult<Data<String>>> LoginOut(String UserID) {
        return ApiRetrofit.getDefault().LoginOut(UserID,"6")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> barCode(String UserID, String barCode) {
        return ApiRetrofit.getDefault().barCode(UserID, barCode)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<UserInfo>> GetUserInfoList(String UserId, String limit) {
        return ApiRetrofit.getDefault().GetUserInfoList(UserId,limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

}
