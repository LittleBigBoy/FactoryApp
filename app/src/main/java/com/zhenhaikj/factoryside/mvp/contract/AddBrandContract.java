package com.zhenhaikj.factoryside.mvp.contract;


import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.Brand;
import com.zhenhaikj.factoryside.mvp.bean.Category;
import com.zhenhaikj.factoryside.mvp.bean.Category;
import com.zhenhaikj.factoryside.mvp.bean.CategoryData;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.ProductType;

import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;


public interface AddBrandContract {
    interface Model extends BaseModel {
        Observable<BaseResult<Data>> AddFactoryBrand(String UserID, String FBrandName);
        Observable<BaseResult<CategoryData>> GetFactoryCategory(String ParentID);
        Observable<BaseResult<CategoryData>> GetChildFactoryCategory(String ParentId);
        Observable<BaseResult<List<Brand>>> GetBrand(String UserId);
//        Observable<BaseResult<Data<List<ProductType>>>> GetCategory(String ParentID);
        Observable<BaseResult<Data<List<ProductType>>>> GetProducttype();
        Observable<BaseResult<Data<Category>>> GetBrandCategory(String UserID);
        Observable<BaseResult<Data>> DeleteFactoryProducttype(String FProductTypeID);
        Observable<BaseResult<Data>> DeleteFactoryBrand(String FBrandID);
        Observable<BaseResult<Data>> AddBrandCategory(
                String BrandID,
                String Categorys
                , String SubCategoryID, String ProductTypeID
        );
        Observable<BaseResult<CategoryData>> GetChildFactoryCategory2(String ParentId);
        Observable<BaseResult<Data>> DeleteFactoryProduct(String UserID, String BrandID, String ProductTypeID);
    }

    interface View extends BaseView {
        void AddFactoryBrand(BaseResult<Data> baseResult);
        void GetFactoryCategory(BaseResult<CategoryData> baseResult);
        void GetChildFactoryCategory(BaseResult<CategoryData> baseResult);
        void GetBrand(BaseResult<List<Brand>> baseResult);
//        void GetCategory(BaseResult<Data<List<ProductType>>> baseResult);
        void GetProducttype(BaseResult<Data<List<ProductType>>> baseResult);
        void GetBrandCategory(BaseResult<Data<Category>> baseResult);
        void DeleteFactoryProducttype(BaseResult<Data> baseResult);
        void DeleteFactoryBrand(BaseResult<Data> baseResult);
        void AddBrandCategory(BaseResult<Data> baseResult);
        void GetChildFactoryCategory2(BaseResult<CategoryData> baseResult);
        void DeleteFactoryProduct(BaseResult<Data> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void AddFactoryBrand(String UserID, String FBrandName);
        public abstract void GetFactoryCategory(String ParentID);
        public abstract void GetChildFactoryCategory(String ParentId);
        public abstract void GetBrand(String UserId);
//        public abstract void GetCategory(String ParentID);
        public abstract void GetProducttype();
        public abstract void GetBrandCategory(String UserID);
        public abstract void DeleteFactoryProducttype(String FProductTypeID);
        public abstract void DeleteFactoryBrand(String FBrandID);
        public abstract void AddBrandCategory(
                String BrandID,
                String Categorys, String SubCategoryID, String  ProductTypeID
        );
        public abstract void GetChildFactoryCategory2(String ParentID);
        public abstract void DeleteFactoryProduct(String UserID, String BrandID, String ProductTypeID);
    }
}
