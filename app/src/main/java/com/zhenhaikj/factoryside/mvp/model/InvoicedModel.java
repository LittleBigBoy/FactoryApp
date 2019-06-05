package com.zhenhaikj.factoryside.mvp.model;

import com.zhenhaikj.factoryside.mvp.ApiRetrofit;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.CanInvoice;
import com.zhenhaikj.factoryside.mvp.bean.CompanyInfo;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.UserInfo;
import com.zhenhaikj.factoryside.mvp.contract.InvoicedContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class InvoicedModel implements InvoicedContract.Model {
    @Override
    public Observable<BaseResult<Data<List<CanInvoice>>>> GetCanInvoiceByUserid(String UserId) {
        return ApiRetrofit.getDefault().GetCanInvoiceByUserid(UserId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
    @Override
    public Observable<BaseResult<Data<CompanyInfo>>> GetmessageBytype(String UserId) {
        return ApiRetrofit.getDefault().GetmessageBytype(UserId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
    @Override
    public Observable<BaseResult<UserInfo>> GetUserInfoList(String UserId, String limit) {
        return ApiRetrofit.getDefault().GetUserInfoList(UserId,limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> AddInvoice(String UserID, String Heads, String Credit, String Content, String Money, String State, String Emails, String Approve,String PuID,String Count) {
        return ApiRetrofit.getDefault().AddInvoice(UserID, Heads, Credit, Content, Money, State, Emails, Approve,PuID,Count)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<List<CanInvoice>>>> GetInvoiceByUserid(String UserId) {
        return ApiRetrofit.getDefault().GetInvoiceByUserid(UserId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

}
