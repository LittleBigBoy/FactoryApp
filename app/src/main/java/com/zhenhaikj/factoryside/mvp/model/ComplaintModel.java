package com.zhenhaikj.factoryside.mvp.model;

import android.widget.ScrollView;

import com.huawei.hms.api.Api;
import com.zhenhaikj.factoryside.mvp.ApiRetrofit;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.ComplaintList;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.contract.ComplaintContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class ComplaintModel implements ComplaintContract.Model {
    @Override
    public Observable<BaseResult<Data<String>>> FactoryComplaint(String OrderID, String Content,String ComplaintType,String photo) {
        return ApiRetrofit.getDefault().FactoryComplaint2(OrderID, Content,ComplaintType,photo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> ComPlaintImg(RequestBody json) {
        return ApiRetrofit.getDefault().ComPlaintImg(json)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<List<ComplaintList>>> GetComplaintListByOrderId(String OrderId,String UserID) {
        return ApiRetrofit.getDefault().GetComplaintListByOrderId(OrderId,UserID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
