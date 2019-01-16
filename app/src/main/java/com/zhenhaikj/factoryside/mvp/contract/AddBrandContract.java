package com.zhenhaikj.factoryside.mvp.contract;


import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.Category;
import com.zhenhaikj.factoryside.mvp.bean.Data;

import java.util.List;

import io.reactivex.Observable;


public interface AddBrandContract {
    interface Model extends BaseModel {
        Observable<BaseResult<Data>> AddFactoryBrand(String UserID, String FBrandName);
        Observable<BaseResult<Data<List<Category>>>> GetFactoryCategory();
    }

    interface View extends BaseView {
        void AddFactoryBrand(BaseResult<Data> baseResult);
        void GetFactoryCategory(BaseResult<Data<List<Category>>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void AddFactoryBrand(String UserID, String FBrandName);
        public abstract void GetFactoryCategory();
    }
}
