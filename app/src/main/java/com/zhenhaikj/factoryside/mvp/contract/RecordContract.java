package com.zhenhaikj.factoryside.mvp.contract;

import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.Bill;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.Data2;
import com.zhenhaikj.factoryside.mvp.bean.Freezing;
import com.zhenhaikj.factoryside.mvp.bean.Recharge;
import com.zhenhaikj.factoryside.mvp.bean.SingleQuantity;

import java.util.List;

import io.reactivex.Observable;

public interface RecordContract {
    interface Model extends BaseModel{
        Observable<BaseResult<Data2<Recharge>>> RechargeRecord(String UserID, String CreateTimeStart, String CreateTimeEnd, String StateName, String State,String page, String limit);
        Observable<BaseResult<Data<Freezing>>> FreezingAmount(String UserID,String CreateTimeStart, String CreateTimeEnd,String page, String limit);
        Observable<BaseResult<Data<SingleQuantity>>> WorkOrderTime( String UserID, String CreateTimeStart, String CreateTimeEnd,String TypeID, String page, String limit);
    }

    interface View extends BaseView{
        void RechargeRecord(BaseResult<Data2<Recharge>> result);
        void FreezingAmount(BaseResult<Data<Freezing>> result);
        void WorkOrderTime(BaseResult<Data<SingleQuantity>> result);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void RechargeRecord( String UserID, String CreateTimeStart, String CreateTimeEnd, String StateName,String State,String page, String limit);
        public abstract void FreezingAmount(String UserID,String CreateTimeStart, String CreateTimeEnd,String page, String limit);
        public abstract void WorkOrderTime(String UserID, String CreateTimeStart, String CreateTimeEnd,String TypeID, String page, String limit);
    }
}
