package com.zhenhaikj.factoryside.mvp.model;

import com.zhenhaikj.factoryside.mvp.ApiRetrofit;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Brand;
import com.zhenhaikj.factoryside.mvp.bean.Category;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.ProductType;
import com.zhenhaikj.factoryside.mvp.contract.AddBrandContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class AddBrandModel implements AddBrandContract.Model {


    @Override
    public Observable<BaseResult<Data>> AddFactoryBrand(String UserID, String FBrandName) {
        return ApiRetrofit.getDefault().AddFactoryBrand(UserID, FBrandName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<List<Category>>>> GetFactoryCategory(String ParentID) {
        return ApiRetrofit.getDefault().GetFactoryCategory(ParentID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

//    @Override
//    public Observable<BaseResult<List<ProductType>>> GetBrand(String UserId) {
//        return ApiRetrofit.getDefault().GetBrand(UserId)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io());
//    }
//
//    @Override
//    public Observable<BaseResult<Data<List<ProductType>>>> GetCategory( String ParentID) {
//        return ApiRetrofit.getDefault().GetCategory(ParentID)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io());
//    }

    @Override
    public Observable<BaseResult<Data<List<ProductType>>>> GetProducttype() {
        return ApiRetrofit.getDefault().GetProducttype()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<List<ProductType>>>> DeleteFactoryProducttype(String FProductTypeID) {
        return ApiRetrofit.getDefault().DeleteFactoryProducttype(FProductTypeID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
