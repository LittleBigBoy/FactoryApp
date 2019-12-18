package com.zhenhaikj.factoryside.mvp.presenter;

import com.zhenhaikj.factoryside.mvp.base.BaseObserver;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.ComplaintList;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.contract.ComplaintContract;

import java.util.List;

import okhttp3.RequestBody;

public class ComplaintPresenter extends ComplaintContract.Presenter {
    @Override
    public void FactoryComplaint(String OrderID, String Content, String ComplaintType,String photo) {
        mModel.FactoryComplaint(OrderID, Content,ComplaintType,photo)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.FactoryComplaint(value);
                    }
                });
    }

    @Override
    public void ComPlaintImg(RequestBody json) {
        mModel.ComPlaintImg(json)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.ComPlaintImg(value);
                    }
                });
    }

    @Override
    public void GetComplaintListByOrderId(String OrderId,String UserID) {
        mModel.GetComplaintListByOrderId(OrderId,UserID)
                .subscribe(new BaseObserver<List<ComplaintList>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<List<ComplaintList>> value) {
                        mView.GetComplaintListByOrderId(value);
                    }
                });
    }
}
