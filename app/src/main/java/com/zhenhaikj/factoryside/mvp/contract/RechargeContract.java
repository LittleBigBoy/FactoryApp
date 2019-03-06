package com.zhenhaikj.factoryside.mvp.contract;


import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.Data;

import io.reactivex.Observable;


public interface RechargeContract {
    interface Model extends BaseModel {
        Observable<BaseResult<Data<String>>> GetOrderStr(String userid,String TotalAmount);
    }

    interface View extends BaseView {
        void GetOrderStr(BaseResult<Data<String>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetOrderStr(String userid,String TotalAmount);
    }
}
