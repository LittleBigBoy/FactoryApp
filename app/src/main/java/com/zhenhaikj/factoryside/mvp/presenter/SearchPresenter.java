package com.zhenhaikj.factoryside.mvp.presenter;

import com.zhenhaikj.factoryside.mvp.base.BaseObserver;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.Search;
import com.zhenhaikj.factoryside.mvp.contract.SearchContract;

public class SearchPresenter extends SearchContract.Presenter {
    @Override
    public void GetOrderInfoList(String Phone, String OrderID,String UserID ,String UserName,String limit, String page) {
        mModel.GetOrderInfoList(Phone, OrderID, UserID,UserName,limit, page)
                .subscribe(new BaseObserver<Search>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Search> value) {
                        mView.GetOrderInfoList(value);
                    }
                });
    }

    @Override
    public void GetFStarOrder(String OrderID, String FStarOrder) {
        mModel.GetFStarOrder(OrderID, FStarOrder)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.GetFStarOrder(value);
                    }
                });
    }
}
