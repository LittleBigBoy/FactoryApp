package com.zhenhaikj.factoryside.mvp.contract;

import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.ComplaintList;
import com.zhenhaikj.factoryside.mvp.bean.Data;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;

public interface ComplaintContract {
    interface Model extends BaseModel{
        Observable<BaseResult<Data<String>>> FactoryComplaint(String OrderID, String Content, String ComplaintType,String photo);
        Observable<BaseResult<Data<String>>> ComPlaintImg(RequestBody json);
        Observable<BaseResult<List<ComplaintList>>> GetComplaintListByOrderId(String OrderId,String UserID);
    }

    interface View extends BaseView{
        void FactoryComplaint(BaseResult<Data<String>> baseResult);
        void ComPlaintImg(BaseResult<Data<String>> baseResult);
        void GetComplaintListByOrderId(BaseResult<List<ComplaintList>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void FactoryComplaint(String OrderID,String Content,String ComplaintType,String photo);
        public abstract void ComPlaintImg(RequestBody json);
        public abstract void GetComplaintListByOrderId(String OrderId,String UserID);
    }
}
