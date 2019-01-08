package com.zhenhaikj.factoryside.mvp.contract;


import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.Category;

import java.util.List;

import io.reactivex.Observable;


public interface PurchaseContract {
    interface Model extends BaseModel {
        Observable<BaseResult<List<Category>>> getData();
    }

    interface View extends BaseView {
        void success(BaseResult<List<Category>> baseResult);
        void fail(BaseResult<List<Category>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void getData();
    }
}
