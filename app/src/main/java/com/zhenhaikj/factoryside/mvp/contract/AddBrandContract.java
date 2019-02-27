package com.zhenhaikj.factoryside.mvp.contract;


import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.Brand;
import com.zhenhaikj.factoryside.mvp.bean.Category;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.ProductType;

import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;


public interface AddBrandContract {
    interface Model extends BaseModel {
        Observable<BaseResult<Data>> AddFactoryBrand(String UserID, String FBrandName);
        Observable<BaseResult<Data<List<Category>>>> GetFactoryCategory(String ParentID);
//        Observable<BaseResult<List<ProductType>>> GetBrand(String UserId);
//        Observable<BaseResult<Data<List<ProductType>>>> GetCategory(String ParentID);
        Observable<BaseResult<Data<List<ProductType>>>> GetProducttype();
        Observable<BaseResult<Data<List<ProductType>>>> DeleteFactoryProducttype(String FProductTypeID);
    }

    interface View extends BaseView {
        void AddFactoryBrand(BaseResult<Data> baseResult);
        void GetFactoryCategory(BaseResult<Data<List<Category>>> baseResult);
//        void GetBrand(BaseResult<List<ProductType>> baseResult);
//        void GetCategory(BaseResult<Data<List<ProductType>>> baseResult);
        void GetProducttype(BaseResult<Data<List<ProductType>>> baseResult);
        void DeleteFactoryProducttype(BaseResult<Data<List<ProductType>>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void AddFactoryBrand(String UserID, String FBrandName);
        public abstract void GetFactoryCategory(String ParentID);
//        public abstract void GetBrand(String UserId);
//        public abstract void GetCategory(String ParentID);
        public abstract void GetProducttype();
        public abstract void DeleteFactoryProducttype(String FProductTypeID);
    }
}
