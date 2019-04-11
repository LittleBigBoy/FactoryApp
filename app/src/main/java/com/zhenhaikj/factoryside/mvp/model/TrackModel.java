package com.zhenhaikj.factoryside.mvp.model;

import com.zhenhaikj.factoryside.mvp.ApiRetrofit;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.Track;
import com.zhenhaikj.factoryside.mvp.contract.TrackContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TrackModel implements TrackContract.Model {
    @Override
    public Observable<BaseResult<List<Track>>> GetOrderRecordByOrderID(String OrderId) {
        return ApiRetrofit.getDefault().GetOrderRecordByOrderID(OrderId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
