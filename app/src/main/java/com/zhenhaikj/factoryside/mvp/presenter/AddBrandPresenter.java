package com.zhenhaikj.factoryside.mvp.presenter;


import com.zhenhaikj.factoryside.mvp.base.BaseObserver;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Brand;
import com.zhenhaikj.factoryside.mvp.bean.Category;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.ProductType;
import com.zhenhaikj.factoryside.mvp.contract.AddBrandContract;

import java.util.List;

public class AddBrandPresenter extends AddBrandContract.Presenter {


    @Override
    public void AddFactoryBrand(String UserID, String FBrandName) {
        mModel.AddFactoryBrand(UserID, FBrandName)
                .subscribe(new BaseObserver<Data>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data> value) {
                        mView.AddFactoryBrand(value);
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
    public void GetChildFactoryCategory(String ParentId) {
        mModel.GetChildFactoryCategory(ParentId)
                .subscribe(new BaseObserver<Data<List<Category>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<List<Category>>> value) {
                        mView.GetChildFactoryCategory(value);
                    }
                });
    }

//    @Override
//    public void GetBrand(String UserId) {
//        mModel.GetBrand(UserId)
//                .subscribe(new BaseObserver<List<ProductType>>() {
//                    @Override
//                    protected void onHandleSuccess(BaseResult<List<ProductType>> value) {
//                        mView.GetBrand(value);
//                    }
//                });
//    }
//
//    @Override
//    public void GetCategory(String ParentID) {
//        mModel.GetCategory(ParentID)
//                .subscribe(new BaseObserver<Data<List<ProductType>>>() {
//                    @Override
//                    protected void onHandleSuccess(BaseResult<Data<List<ProductType>>> value) {
//                        mView.GetCategory(value);
//                    }
//                });
//    }

    @Override
    public void GetProducttype() {
        mModel.GetProducttype()
                .subscribe(new BaseObserver<Data<List<ProductType>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<List<ProductType>>> value) {
                        mView.GetProducttype(value);
                    }
                });
    }

    @Override
    public void DeleteFactoryProducttype(String FProductTypeID) {
        mModel.DeleteFactoryProducttype(FProductTypeID)
                .subscribe(new BaseObserver<Data<List<ProductType>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<List<ProductType>>> value) {
                        mView.DeleteFactoryProducttype(value);
                    }
                });
    }
}
