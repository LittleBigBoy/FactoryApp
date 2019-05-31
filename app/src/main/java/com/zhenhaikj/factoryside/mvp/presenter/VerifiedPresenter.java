package com.zhenhaikj.factoryside.mvp.presenter;

import com.zhenhaikj.factoryside.mvp.base.BaseObserver;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.contract.VerifiedContract;

import okhttp3.RequestBody;

public class VerifiedPresenter extends VerifiedContract.Presenter {
    @Override
    public void IDCardUpload(RequestBody json, final int code) {
        mModel.IDCardUpload(json,code)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.IDCardUpload(value,code);
                    }
                });
    }

    @Override
    public void FactoryApplyAuthInfo(String UserID, String CompanyName, String CompanyNum, String ManagyName, String ManagyPhone,String ServicePhone, String FinancePhone, String ArtisanPhone, String PhoneUrl, String Province, String City, String Area, String District, String Address, String IfAuth, String IsUse) {
        mModel.FactoryApplyAuthInfo(UserID, CompanyName, CompanyNum, ManagyName,ManagyPhone, ServicePhone, FinancePhone, ArtisanPhone, PhoneUrl, Province, City, Area, District, Address, IfAuth, IsUse)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.FactoryApplyAuthInfo(value);
                    }
                });

    }
}
