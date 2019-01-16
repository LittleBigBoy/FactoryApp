package com.zhenhaikj.factoryside.mvp.contract;


import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.Accessory;
import com.zhenhaikj.factoryside.mvp.bean.Brand;
import com.zhenhaikj.factoryside.mvp.bean.Category;
import com.zhenhaikj.factoryside.mvp.bean.City;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.ProductType;
import com.zhenhaikj.factoryside.mvp.bean.Province;

import java.util.List;

import io.reactivex.Observable;


public interface HomeMaintenanceContract {
    interface Model extends BaseModel {
        Observable<BaseResult<List<Brand>>> GetFactoryBrand(String UserID);
        Observable<BaseResult<Data<List<Category>>>> GetFactoryCategory();
        Observable<BaseResult<Data<List<ProductType>>>> GetFactoryProducttype(String FBrandID, String FCategoryID);
        Observable<BaseResult<Accessory>> GetFactoryAccessory(String FProductTypeID);
        Observable<BaseResult<List<Province>>> GetProvince();
        Observable<BaseResult<Data<List<City>>>> GetCity(String parentcode);
        Observable<BaseResult<Data<List<City>>>> GetArea(String parentcode);
    }

    interface View extends BaseView {
        void GetFactoryBrand(BaseResult<List<Brand>> baseResult);
        void GetFactoryCategory(BaseResult<Data<List<Category>>> baseResult);
        void GetFactoryProducttype(BaseResult<Data<List<ProductType>>> baseResult);
        void GetFactoryAccessory(BaseResult<Accessory> baseResult);
        void GetProvince(BaseResult<List<Province>> baseResult);
        void GetCity(BaseResult<Data<List<City>>> baseResult);
        void GetArea(BaseResult<Data<List<City>>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetFactoryBrand(String UserID);
        public abstract void GetFactoryCategory();
        public abstract void GetFactoryProducttype(String FBrandID, String FCategoryID);
        public abstract void GetFactoryAccessory(String FProductTypeID);
        public abstract void GetProvince();
        public abstract void GetCity(String parentcode);
        public abstract void GetArea(String parentcode);
    }
}
