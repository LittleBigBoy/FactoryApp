package com.zhenhaikj.factoryside.mvp.contract;

import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.Logistics;
import com.zhenhaikj.factoryside.mvp.bean.WorkOrder;

import java.util.List;

import io.reactivex.Observable;

public interface ExpressInfoContract {
    interface Model extends BaseModel{
        Observable<BaseResult<Data<Logistics>>> GetExpressInfo(String ExpressNo);
        Observable<BaseResult<WorkOrder.DataBean>> GetOrderInfo(String OrderID);
    }

    interface View extends BaseView{
        void GetExpressInfo(BaseResult<Data<Logistics>> baseResult);
        void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void GetExpressInfo(String ExpressNo);
        public abstract void GetOrderInfo(String OrderID);
    }
}
