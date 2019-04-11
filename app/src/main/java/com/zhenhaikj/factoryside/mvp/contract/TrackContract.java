package com.zhenhaikj.factoryside.mvp.contract;

import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.Track;

import java.util.List;

import io.reactivex.Observable;

public interface TrackContract {
    interface Model extends BaseModel{
        Observable<BaseResult<List<Track>>> GetOrderRecordByOrderID(String OrderId);
    }

    interface View extends BaseView{
        void GetOrderRecordByOrderID(BaseResult<List<Track>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void GetOrderRecordByOrderID(String OrderId);
    }
}
