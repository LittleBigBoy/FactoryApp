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
        Observable<BaseResult<Data<List<Category>>>> GetChildFactoryCategory(String ParentId);
        Observable<BaseResult<List<Brand>>> GetBrand(String UserId);
//        Observable<BaseResult<Data<List<ProductType>>>> GetCategory(String ParentID);
        Observable<BaseResult<Data<List<ProductType>>>> GetProducttype();
        Observable<BaseResult<List<ProductType>>> GetProductTypeByUserID(String UserID);
        Observable<BaseResult<Data>> DeleteFactoryProducttype(String FProductTypeID);
        Observable<BaseResult<Data>> DeleteFactoryBrand(String FBrandID);
        Observable<BaseResult<Data>> AddFactoryProducttype(
                String FProductTypeName,
                String FBrandID,
                String FBrandName,
                String FCategoryID,
                String FCategoryName,
                String InitPrice
        );
    }

    interface View extends BaseView {
        void AddFactoryBrand(BaseResult<Data> baseResult);
        void GetFactoryCategory(BaseResult<Data<List<Category>>> baseResult);
        void GetChildFactoryCategory(BaseResult<Data<List<Category>>> baseResult);
        void GetBrand(BaseResult<List<Brand>> baseResult);
//        void GetCategory(BaseResult<Data<List<ProductType>>> baseResult);
        void GetProducttype(BaseResult<Data<List<ProductType>>> baseResult);
        void GetProductTypeByUserID(BaseResult<List<ProductType>> baseResult);
        void DeleteFactoryProducttype(BaseResult<Data> baseResult);
        void DeleteFactoryBrand(BaseResult<Data> baseResult);
        void AddFactoryProducttype(BaseResult<Data> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void AddFactoryBrand(String UserID, String FBrandName);
        public abstract void GetFactoryCategory(String ParentID);
        public abstract void GetChildFactoryCategory(String ParentId);
        public abstract void GetBrand(String UserId);
//        public abstract void GetCategory(String ParentID);
        public abstract void GetProducttype();
        public abstract void GetProductTypeByUserID(String UserID);
        public abstract void DeleteFactoryProducttype(String FProductTypeID);
        public abstract void DeleteFactoryBrand(String FBrandID);
        public abstract void AddFactoryProducttype(
                String FProductTypeName,
                String FBrandID,
                String FBrandName,
                String FCategoryID,
                String FCategoryName,
                String InitPrice
        );
    }
}
