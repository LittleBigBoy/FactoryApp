package com.zhenhaikj.factoryside.mvp.model;

import com.zhenhaikj.factoryside.mvp.ApiRetrofit;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Area;
import com.zhenhaikj.factoryside.mvp.bean.Brand;
import com.zhenhaikj.factoryside.mvp.bean.Category;
import com.zhenhaikj.factoryside.mvp.bean.CategoryData;
import com.zhenhaikj.factoryside.mvp.bean.City;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.District;
import com.zhenhaikj.factoryside.mvp.bean.ProductType;
import com.zhenhaikj.factoryside.mvp.bean.Province;
import com.zhenhaikj.factoryside.mvp.contract.HomeInstallationContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class HomeInstallationModel implements HomeInstallationContract.Model {
    @Override
    public Observable<BaseResult<List<Brand>>> GetFactoryBrand(String UserID) {
        return ApiRetrofit.getDefault().GetFactoryBrand(UserID)
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
    public Observable<BaseResult<CategoryData>> GetChildFactoryCategory(String ParentID) {
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

    @Override
    public Observable<BaseResult<Data<String>>> AddOrder(RequestBody json) {
        return ApiRetrofit.getDefault().AddOrder(json)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

}
