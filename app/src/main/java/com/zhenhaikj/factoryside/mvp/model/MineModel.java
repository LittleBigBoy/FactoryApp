package com.zhenhaikj.factoryside.mvp.model;

import com.zhenhaikj.factoryside.mvp.ApiRetrofit;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.CompanyInfo;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.UserInfo;
import com.zhenhaikj.factoryside.mvp.contract.MineContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class MineModel implements MineContract.Model {
    @Override
    public Observable<BaseResult<UserInfo>> GetUserInfoList(String UserId, String limit) {
        return ApiRetrofit.getDefault().GetUserInfoList(UserId,limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> UploadAvator(RequestBody josn) {
        return ApiRetrofit.getDefault().UploadAvator(josn)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<CompanyInfo>>> GetmessageBytype(String UserId) {
        return ApiRetrofit.getDefault().GetmessageBytype(UserId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
