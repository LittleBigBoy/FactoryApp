package com.zhenhaikj.factoryside.mvp.contract;


import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.RedPointData;
import com.zhenhaikj.factoryside.mvp.bean.WorkOrder;

import io.reactivex.Observable;


public interface RedPointContract {
    interface Model extends BaseModel {
        Observable<BaseResult<RedPointData>> FactoryGetOrderRed(String UserId);
    }

    interface View extends BaseView {
        void FactoryGetOrderRed(BaseResult<RedPointData> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void FactoryGetOrderRed(String UserID);
    }
}
