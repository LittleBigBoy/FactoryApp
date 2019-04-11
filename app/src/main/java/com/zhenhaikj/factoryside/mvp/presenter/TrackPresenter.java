package com.zhenhaikj.factoryside.mvp.presenter;

import com.zhenhaikj.factoryside.mvp.base.BaseObserver;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.Track;
import com.zhenhaikj.factoryside.mvp.contract.TrackContract;

import java.util.List;

public class TrackPresenter extends TrackContract.Presenter {
    @Override
    public void GetOrderRecordByOrderID(String OrderId) {
        mModel.GetOrderRecordByOrderID(OrderId)
                .subscribe(new BaseObserver<List<Track>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<List<Track>> value) {
                        mView.GetOrderRecordByOrderID(value);
                    }
                });
    }
}
