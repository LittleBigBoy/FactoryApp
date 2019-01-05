package com.emjiayuan.factoryside.mvp.contract;


import com.emjiayuan.nll.base.BaseModel;
import com.emjiayuan.nll.base.BasePresenter;
import com.emjiayuan.nll.base.BaseResult;
import com.emjiayuan.nll.base.BaseView;
import com.emjiayuan.nll.model.HomeData;

import io.reactivex.Observable;


public interface HomeContract {
    interface Model extends BaseModel {
        Observable<BaseResult<HomeData>> getData(String userid);
    }

    interface View extends BaseView {
        void success(BaseResult<HomeData> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void getData(String userid);
    }
}
