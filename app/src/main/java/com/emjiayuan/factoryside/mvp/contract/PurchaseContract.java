package com.emjiayuan.factoryside.mvp.contract;


import com.emjiayuan.nll.base.BaseModel;
import com.emjiayuan.nll.base.BasePresenter;
import com.emjiayuan.nll.base.BaseResult;
import com.emjiayuan.nll.base.BaseView;
import com.emjiayuan.nll.model.Category;

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
