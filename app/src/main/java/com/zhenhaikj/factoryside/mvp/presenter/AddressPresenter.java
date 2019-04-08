package com.zhenhaikj.factoryside.mvp.presenter;


import com.zhenhaikj.factoryside.mvp.base.BaseObserver;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Address;
import com.zhenhaikj.factoryside.mvp.bean.Area;
import com.zhenhaikj.factoryside.mvp.bean.Brand;
import com.zhenhaikj.factoryside.mvp.bean.City;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.District;
import com.zhenhaikj.factoryside.mvp.bean.Province;
import com.zhenhaikj.factoryside.mvp.contract.AddressContract;

import java.util.List;

public class AddressPresenter extends AddressContract.Presenter {

    @Override
    public void AddAccountAddress(String UserID, String Province, String City, String Area,String District, String Address, String Default, String UserName, String Phone) {
        mModel.AddAccountAddress(UserID, Province, City, Area,District, Address, Default, UserName, Phone)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.AddAccountAddress(value);
                    }
                });
    }

    @Override
    public void UpdateAccountAddress(String ID, String UserID, String Province, String City, String Area,String District, String Address, String Default, String UserName, String Phone) {
        mModel.UpdateAccountAddress(ID, UserID, Province, City, Area,District, Address, Default, UserName, Phone)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.UpdateAccountAddress(value);
                    }
                });
    }

    @Override
    public void DeleteAccountAddress(String ID) {
        mModel.DeleteAccountAddress(ID)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.DeleteAccountAddress(value);
                    }
                });
    }

    @Override
    public void GetAccountAddress(String UserId) {
        mModel.GetAccountAddress(UserId)
                .subscribe(new BaseObserver<List<Address>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<List<Address>> value) {
                        mView.GetAccountAddress(value);
                    }
                });
    }
    @Override
    public void GetProvince() {
        mModel.GetProvince()
                .subscribe(new BaseObserver<List<Province>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<List<Province>> value) {
                        mView.GetProvince(value);
                    }
                });
    }

    @Override
    public void GetCity(String parentcode) {
        mModel.GetCity(parentcode)
                .subscribe(new BaseObserver<Data<List<City>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<List<City>>> value) {
                        mView.GetCity(value);
                    }
                });
    }

    @Override
    public void GetArea(String parentcode) {
        mModel.GetArea(parentcode)
                .subscribe(new BaseObserver<Data<List<Area>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<List<Area>>> value) {
                        mView.GetArea(value);
                    }
                });
    }

    @Override
    public void GetDistrict(String parentcode) {
        mModel.GetDistrict(parentcode)
                .subscribe(new BaseObserver<Data<List<District>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<List<District>>> value) {
                        mView.GetDistrict(value);
                    }
                });
    }
}
