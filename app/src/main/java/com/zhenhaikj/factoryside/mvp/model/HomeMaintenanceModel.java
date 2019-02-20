package com.zhenhaikj.factoryside.mvp.model;

import com.zhenhaikj.factoryside.mvp.ApiRetrofit;
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

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class HomeMaintenanceModel implements HomeMaintenanceContract.Model {

    @Override
    public Observable<BaseResult<List<Brand>>> GetFactoryBrand(String UserID) {
        return ApiRetrofit.getDefault().GetFactoryBrand(UserID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<List<Category>>>> GetFactoryCategory(String ParentID) {
        return ApiRetrofit.getDefault().GetFactoryCategory(ParentID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
    @Override
    public Observable<BaseResult<Data<List<Category>>>> GetChildFactoryCategory(String ParentID) {
        return ApiRetrofit.getDefault().GetChildFactoryCategory(ParentID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<List<ProductType>>>> GetFactoryProducttype(String FBrandID, String FCategoryID) {
        return ApiRetrofit.getDefault().GetFactoryProducttype(FBrandID, FCategoryID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Accessory>> GetFactoryAccessory(String FProductTypeID) {
        return ApiRetrofit.getDefault().GetFactoryAccessory(FProductTypeID)
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
    public Observable<BaseResult<Data<String>>> AddOrder(String TypeID, String TypeName, String UserID, String FBrandID, String BrandName, String FCategoryID, String CategoryName, String SubCategoryID, String SubCategoryName, String FProductTypeID, String ProductType, String ProvinceCode, String CityCode, String AreaCode, String Address, String UserName, String Phone, String Memo, String OrderMoney, String RecycleOrderHour, String Guarantee, String AccessorySendState, String Extra, String ExtraTime, String ExtraFee) {
        return ApiRetrofit.getDefault().AddOrder(TypeID, TypeName, UserID, FBrandID, BrandName, FCategoryID, CategoryName, SubCategoryID, SubCategoryName, FProductTypeID, ProductType, ProvinceCode, CityCode, AreaCode, Address, UserName, Phone, Memo, OrderMoney, RecycleOrderHour, Guarantee, AccessorySendState, Extra, ExtraTime, ExtraFee)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
