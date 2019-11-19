package com.zhenhaikj.factoryside.mvp.presenter;


import com.zhenhaikj.factoryside.mvp.ApiRetrofit;
import com.zhenhaikj.factoryside.mvp.ApiService;
import com.zhenhaikj.factoryside.mvp.base.BaseObserver;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Accessory;
import com.zhenhaikj.factoryside.mvp.bean.Address;
import com.zhenhaikj.factoryside.mvp.bean.Area;
import com.zhenhaikj.factoryside.mvp.bean.Brand;
import com.zhenhaikj.factoryside.mvp.bean.Category;
import com.zhenhaikj.factoryside.mvp.bean.CategoryData;
import com.zhenhaikj.factoryside.mvp.bean.City;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.District;
import com.zhenhaikj.factoryside.mvp.bean.GetCategory;
import com.zhenhaikj.factoryside.mvp.bean.ProductType;
import com.zhenhaikj.factoryside.mvp.bean.Province;
import com.zhenhaikj.factoryside.mvp.contract.HomeMaintenanceContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import okhttp3.RequestBody;

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
    public void GetFactoryCategory(String ParentID) {
        mModel.GetFactoryCategory(ParentID)
                .subscribe(new BaseObserver<CategoryData>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<CategoryData> value) {
                        mView.GetFactoryCategory(value);
                    }
                });
    }
    @Override
    public void GetChildFactoryCategory(String ParentID) {
        mModel.GetChildFactoryCategory(ParentID)
                .subscribe(new BaseObserver<CategoryData>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<CategoryData> value) {
                        mView.GetChildFactoryCategory(value);
                    }
                });
    }
    @Override
    public void GetChildFactoryCategory2(String ParentID) {
        mModel.GetChildFactoryCategory2(ParentID)
                .subscribe(new BaseObserver<CategoryData>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<CategoryData> value) {
                        mView.GetChildFactoryCategory2(value);
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

    /*@Override
    public void GetProvince() {
        mModel.GetProvince()
                .flatMap(new Function<BaseResult<List<Province>>, ObservableSource<BaseResult<Data<List<City>>>>>() {
                    @Override
                    public ObservableSource<BaseResult<Data<List<City>>>> apply(BaseResult<List<Province>> listBaseResult) throws Exception {
                        return ApiRetrofit.getDefault().GetCity(listBaseResult.getData().get(0).getCode());
                    }
                }).toList()
                .subscribe(new BaseObserver<List<Province>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<List<Province>> value) {
                        mView.GetProvince(value);
                    }
                });
    }*/
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

    @Override
    public void AddOrder(RequestBody json){
        mModel.AddOrder(json)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.AddOrder(value);
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
    public void GetBrandCategory(String UserID) {
        mModel.GetBrandCategory(UserID)
                .subscribe(new BaseObserver<Data<List<Category>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<List<Category>>> value) {
                        mView.GetBrandCategory(value);
                    }
                });
    }

    @Override
    public void GetBrandWithCategory(String UserID, String BrandID) {
        mModel.GetBrandWithCategory(UserID, BrandID)
                .subscribe(new BaseObserver<Data<List<GetCategory>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<List<GetCategory>>> value) {
                        mView.GetBrandWithCategory(value);
                    }
                });
    }

    @Override
    public void GetUniqId() {
        mModel.GetUniqId()
                .subscribe(new BaseObserver<String>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<String> value) {
                        mView.GetUniqId(value);
                    }
                });
    }

}
