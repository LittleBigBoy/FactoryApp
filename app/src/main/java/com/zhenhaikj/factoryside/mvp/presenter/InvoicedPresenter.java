package com.zhenhaikj.factoryside.mvp.presenter;

import com.zhenhaikj.factoryside.mvp.base.BaseObserver;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.CanInvoice;
import com.zhenhaikj.factoryside.mvp.bean.CompanyInfo;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.UserInfo;
import com.zhenhaikj.factoryside.mvp.contract.InvoicedContract;

import java.util.List;

public class InvoicedPresenter extends InvoicedContract.Presenter {
    @Override
    public void GetCanInvoiceByUserid(String UserId,String IsInvoice) {
        mModel.GetCanInvoiceByUserid(UserId,IsInvoice)
                .subscribe(new BaseObserver<Data<List<CanInvoice>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<List<CanInvoice>>> value) {
                        mView.GetCanInvoiceByUserid(value);
                    }
                });
    }
    @Override
    public void GetmessageBytype(String UserId) {
        mModel.GetmessageBytype(UserId)
                .subscribe(new BaseObserver<Data<CompanyInfo>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<CompanyInfo>> value) {
                        mView.GetmessageBytype(value);
                    }
                });
    }

    @Override
    public void GetUserInfoList(String UserId, String limit) {
        mModel.GetUserInfoList(UserId, limit)
                .subscribe(new BaseObserver<UserInfo>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<UserInfo> value) {
                        mView.GetUserInfoList(value);
                    }
                });
    }

    @Override
    public void AddInvoice(String UserID, String Heads, String Credit, String Content, String Money, String State, String Emails, String Approve,String PuID,String Count) {
        mModel.AddInvoice(UserID, Heads, Credit, Content, Money, State, Emails, Approve,PuID,Count)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.AddInvoice(value);
                    }
                });
    }

    @Override
    public void GetInvoiceByUserid(String UserId) {
        mModel.GetInvoiceByUserid(UserId)
                .subscribe(new BaseObserver<Data<List<CanInvoice>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<List<CanInvoice>>> value) {
                        mView.GetInvoiceByUserid(value);
                    }
                });
    }
}
