package com.zhenhaikj.factoryside.mvp.presenter;

import com.zhenhaikj.factoryside.mvp.base.BaseObserver;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Area;
import com.zhenhaikj.factoryside.mvp.bean.Brand;
import com.zhenhaikj.factoryside.mvp.bean.Category;
import com.zhenhaikj.factoryside.mvp.bean.City;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.District;
import com.zhenhaikj.factoryside.mvp.bean.ProductType;
import com.zhenhaikj.factoryside.mvp.bean.Province;
import com.zhenhaikj.factoryside.mvp.contract.CustomerServiceContract;
import com.zhenhaikj.factoryside.mvp.contract.HomeInstallationContract;

import java.util.List;

public class CustomerServicePresenter extends CustomerServiceContract.Presenter {
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
                .subscribe(new BaseObserver<Data<List<Category>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<List<Category>>> value) {
                        mView.GetFactoryCategory(value);
                    }
                });
    }
    @Override
    public void GetChildFactoryCategory(String ParentID) {
        mModel.GetChildFactoryCategory(ParentID)
                .subscribe(new BaseObserver<Data<List<Category>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<List<Category>>> value) {
                        mView.GetChildFactoryCategory(value);
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
    public void AddOrder(String TypeID, String TypeName, String UserID, String FBrandID, String BrandName, String FCategoryID, String CategoryName, String SubCategoryID, String SubCategoryName, String FProductTypeID, String ProductType, String ProvinceCode, String CityCode, String AreaCode,String DistrictCode, String Address, String UserName, String Phone, String Memo, String OrderMoney, String RecycleOrderHour, String Guarantee,String Num) {
        mModel.AddOrder(TypeID, TypeName, UserID, FBrandID, BrandName, FCategoryID, CategoryName, SubCategoryID, SubCategoryName, FProductTypeID, ProductType, ProvinceCode, CityCode, AreaCode,DistrictCode, Address, UserName, Phone, Memo, OrderMoney, RecycleOrderHour, Guarantee,Num)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.AddOrder(value);
                    }
                });
    }

}
