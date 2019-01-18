package com.zhenhaikj.factoryside.mvp.presenter;


import com.zhenhaikj.factoryside.mvp.base.BaseObserver;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Accessory;
import com.zhenhaikj.factoryside.mvp.bean.Area;
import com.zhenhaikj.factoryside.mvp.bean.Brand;
import com.zhenhaikj.factoryside.mvp.bean.Category;
import com.zhenhaikj.factoryside.mvp.bean.City;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.ProductType;
import com.zhenhaikj.factoryside.mvp.bean.Province;
import com.zhenhaikj.factoryside.mvp.contract.HomeMaintenanceContract;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class HomeMaintenancePresenter extends HomeMaintenanceContract.Presenter {

    @Override
    public void GetFactoryBrand(String UserID) {
        mModel.GetFactoryBrand(UserID)
                .subscribe(new BaseObserver<List<Brand>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<List<Brand>> value) {
                        mView.GetFactoryBrand(value);
                    }
                });
    }

    @Override
    public void GetFactoryCategory() {
        mModel.GetFactoryCategory()
                .subscribe(new BaseObserver<Data<List<Category>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<List<Category>>> value) {
                        mView.GetFactoryCategory(value);
                    }
                });
    }

    @Override
    public void GetFactoryProducttype(String FBrandID, String FCategoryID) {
        mModel.GetFactoryProducttype(FBrandID, FCategoryID)
                .subscribe(new BaseObserver<Data<List<ProductType>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<List<ProductType>>> value) {
                        mView.GetFactoryProducttype(value);
                    }
                });
    }

    @Override
    public void GetFactoryAccessory(String FProductTypeID) {
        mModel.GetFactoryAccessory(FProductTypeID)
                .subscribe(new BaseObserver<Accessory>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Accessory> value) {
                        mView.GetFactoryAccessory(value);
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
    public void AddOrder(String TypeID, String TypeName, String UserID, String FBrandID, String BrandName, String FCategoryID, String CategoryName, String FProductTypeID, String ProductType, String ProvinceCode, String CityCode, String AreaCode, String Address, String UserName, String Phone, String Memo, String OrderMoney, String RecycleOrderHour, String Guarantee, String AccessorySendState, String Extra, String ExtraTime, String ExtraFee) {
        mModel.AddOrder(TypeID, TypeName, UserID, FBrandID, BrandName, FCategoryID, CategoryName, FProductTypeID, ProductType, ProvinceCode, CityCode, AreaCode, Address, UserName, Phone, Memo, OrderMoney, RecycleOrderHour, Guarantee, AccessorySendState, Extra, ExtraTime, ExtraFee)
                .subscribe(new Observer<Data<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Data<String> stringData) {
                        mView.AddOrder(stringData);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
