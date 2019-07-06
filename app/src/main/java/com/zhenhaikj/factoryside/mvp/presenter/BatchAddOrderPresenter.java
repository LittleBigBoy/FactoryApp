package com.zhenhaikj.factoryside.mvp.presenter;

import com.zhenhaikj.factoryside.mvp.base.BaseObserver;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Article;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.contract.BatchAddOrderContract;

import okhttp3.RequestBody;

public class BatchAddOrderPresenter extends BatchAddOrderContract.Presenter {
    @Override
    public void BatchAddOrder(RequestBody json) {
        mModel.BatchAddOrder(json)
                .subscribe(new BaseObserver<String>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<String> value) {
                        mView.BatchAddOrder(value);
                    }
                });
    }
}
