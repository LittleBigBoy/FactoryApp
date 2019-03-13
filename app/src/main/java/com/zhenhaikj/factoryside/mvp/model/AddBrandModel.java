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

    @Override
    public Observable<BaseResult<Data<List<Category>>>> GetChildFactoryCategory(String ParentId) {
        return ApiRetrofit.getDefault().GetChildFactoryCategory(ParentId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }


    @Override
    public Observable<BaseResult<List<Brand>>> GetBrand(String UserId) {
        return ApiRetrofit.getDefault().GetFactoryBrand(UserId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
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
    public Observable<BaseResult<Data>> DeleteFactoryProducttype(String FProductTypeID) {
        return ApiRetrofit.getDefault().DeleteFactoryProducttype(FProductTypeID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data>> AddFactoryProducttype(String FProductTypeName, String FBrandID, String FBrandName, String FCategoryID, String FCategoryName,String InitPrice) {
        return ApiRetrofit.getDefault().AddFactoryProducttype(FProductTypeName, FBrandID, FBrandName, FCategoryID, FCategoryName,InitPrice)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<List<ProductType>>> GetProductTypeByUserID(String UserID) {
        return ApiRetrofit.getDefault().GetProductTypeByUserID(UserID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data>> DeleteFactoryBrand(String FBrandID) {
        return ApiRetrofit.getDefault().DeleteFactoryBrand(FBrandID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
