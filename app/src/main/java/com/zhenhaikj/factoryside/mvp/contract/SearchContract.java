package com.zhenhaikj.factoryside.mvp.contract;

import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BaseObserver;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.Search;

import io.reactivex.Observable;

public interface SearchContract  {
    interface Model extends BaseModel{
        Observable<BaseResult<Search>> GetOrderInfoList(String Phone, String OrderID,String UserID, String limit, String page);
        Observable<BaseResult<Data<String>>> GetFStarOrder(String OrderID, String FStarOrder);
    }

    interface View extends BaseView{
        void GetOrderInfoList(BaseResult<Search> baseResult);
        void GetFStarOrder(BaseResult<Data<String>> baseResult);

    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void GetOrderInfoList(String Phone, String OrderID, String UserID,String limit, String page);
        public abstract void GetFStarOrder(String OrderID, String FStarOrder);

    }
}
