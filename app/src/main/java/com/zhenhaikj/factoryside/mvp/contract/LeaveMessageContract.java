package com.zhenhaikj.factoryside.mvp.contract;


import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.ReadMessage;
import com.zhenhaikj.factoryside.mvp.bean.WorkOrder;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;

public interface LeaveMessageContract {
    interface Model extends BaseModel {
        Observable<BaseResult<Data<String>>> AddLeaveMessageForOrder(String UserID, String OrderId, String Content,String photo);
        //根据工单号获取工单详情
        Observable<BaseResult<WorkOrder.DataBean>> GetOrderInfo(String OrderID);
        Observable<BaseResult<Data<String>>> LeaveMessageImg(RequestBody json);
        Observable<BaseResult<Data<List<ReadMessage>>>> LeaveMessageWhetherLook(String OrderID);
    }

    interface View extends BaseView {
        void AddLeaveMessageForOrder(BaseResult<Data<String>> baseResult);
        //根据工单号获取工单详情
        void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult);
        void LeaveMessageImg(BaseResult<Data<String>> baseResult);
        void LeaveMessageWhetherLook(BaseResult<Data<List<ReadMessage>>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void AddLeaveMessageForOrder(String UserID, String OrderId, String Content,String photo);
        //根据工单号获取工单详情
        public abstract void GetOrderInfo(String OrderID);
        public abstract void LeaveMessageImg(RequestBody json);
        public abstract void LeaveMessageWhetherLook(String OrderID);
    }
}
