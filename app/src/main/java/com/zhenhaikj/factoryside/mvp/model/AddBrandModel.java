package com.zhenhaikj.factoryside.mvp.model;

import com.zhenhaikj.factoryside.mvp.ApiRetrofit;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Brand;
import com.zhenhaikj.factoryside.mvp.bean.Category;
import com.zhenhaikj.factoryside.mvp.bean.Category;
import com.zhenhaikj.factoryside.mvp.bean.CategoryData;
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
    public Observable<BaseResult<CategoryData>> GetFactoryCategory(String ParentID) {
        return ApiRetrofit.getDefault().GetFactoryCategory(ParentID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<CategoryData>> GetChildFactoryCategory(String ParentId) {
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
    public Observable<BaseResult<Data>> AddBrandCategory(String BrandID, String Categorys, String SubCategoryID, String  ProductTypeID) {
        return ApiRetrofit.getDefault().AddBrandCategory(BrandID,Categorys,SubCategoryID,ProductTypeID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<Category>>> GetBrandCategory(String UserID) {
        return ApiRetrofit.getDefault().GetBrandCategory(UserID,"1","999")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data>> DeleteFactoryBrand(String FBrandID) {
        return ApiRetrofit.getDefault().DeleteFactoryBrand(FBrandID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<CategoryData>> GetChildFactoryCategory2(String ParentID) {
        return ApiRetrofit.getDefault().GetChildFactoryCategory(ParentID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data>> DeleteFactoryProduct(String UserID, String BrandID, String ProductTypeID) {
        return ApiRetrofit.getDefault().DeleteFactoryProduct(UserID, BrandID, ProductTypeID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

}
