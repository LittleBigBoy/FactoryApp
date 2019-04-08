package com.zhenhaikj.factoryside.mvp.model;

import com.zhenhaikj.factoryside.mvp.ApiRetrofit;
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

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class AddressModel implements AddressContract.Model {

    @Override
    public Observable<BaseResult<Data<String>>> AddAccountAddress(String UserID, String Province, String City, String Area,String District, String Address, String Default, String UserName, String Phone) {
        return ApiRetrofit.getDefault().AddAccountAddress(UserID, Province, City, Area,District, Address, Default, UserName, Phone)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> UpdateAccountAddress(String ID, String UserID, String Province, String City, String Area,String District, String Address, String Default, String UserName, String Phone) {
        return ApiRetrofit.getDefault().UpdateAccountAddress(ID, UserID, Province, City, Area,District, Address, Default, UserName, Phone)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> DeleteAccountAddress(String ID) {
        return ApiRetrofit.getDefault().DeleteAccountAddress(ID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<List<Address>>> GetAccountAddress(String UserId) {
        return ApiRetrofit.getDefault().GetAccountAddress(UserId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<List<Province>>> GetProvince() {
        return ApiRetrofit.getDefault().GetProvince()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<List<City>>>> GetCity(String parentcode) {
        return ApiRetrofit.getDefault().GetCity(parentcode)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<List<Area>>>> GetArea(String parentcode) {
        return ApiRetrofit.getDefault().GetArea(parentcode)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<List<District>>>> GetDistrict(String parentcode) {
        return ApiRetrofit.getDefault().GetDistrict(parentcode)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

}
