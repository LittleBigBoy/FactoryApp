package com.zhenhaikj.factoryside.mvp.contract;


import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.HomeData;

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
