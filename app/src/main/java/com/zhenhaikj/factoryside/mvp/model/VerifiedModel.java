package com.zhenhaikj.factoryside.mvp.model;

import com.zhenhaikj.factoryside.mvp.ApiRetrofit;
import com.zhenhaikj.factoryside.mvp.activity.VerifiedActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseObserver;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.contract.VerifiedContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class VerifiedModel implements VerifiedContract.Model {
    @Override
    public Observable<BaseResult<Data<String>>> IDCardUpload(RequestBody json, int code) {
        return  ApiRetrofit.getDefault().IDCardUpload(json)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> FactoryApplyAuthInfo(String UserID, String CompanyName, String CompanyNum, String ManagyName,String ManagyPhone, String ServicePhone, String FinancePhone, String ArtisanPhone, String PhoneUrl, String Province, String City, String Area, String District, String Address, String IfAuth, String IsUse) {
        return ApiRetrofit.getDefault().FactoryApplyAuthInfo(UserID, CompanyName, CompanyNum, ManagyName, ManagyPhone,ServicePhone, FinancePhone, ArtisanPhone, PhoneUrl, Province, City, Area, District, Address, IfAuth, IsUse)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
