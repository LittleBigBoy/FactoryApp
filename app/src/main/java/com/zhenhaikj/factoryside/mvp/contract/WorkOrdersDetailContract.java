package com.zhenhaikj.factoryside.mvp.contract;


import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.WorkOrder;

import io.reactivex.Observable;


public interface WorkOrdersDetailContract {
    interface Model extends BaseModel {
        Observable<BaseResult<WorkOrder.DataBean>> GetOrderInfo(String OrderID);
    }

    interface View extends BaseView {
        void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetOrderInfo(String OrderID);
    }
}
