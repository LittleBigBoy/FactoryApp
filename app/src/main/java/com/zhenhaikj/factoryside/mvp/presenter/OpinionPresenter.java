package com.zhenhaikj.factoryside.mvp.presenter;

import com.zhenhaikj.factoryside.mvp.base.BaseObserver;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.contract.OpinionContract;

public class OpinionPresenter extends OpinionContract.Presenter {
    @Override
    public void AddOpinion(String UserId, String BackType, String Content) {
        mModel.AddOpinion(UserId, BackType, Content)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.AddOpinion(value);
                    }
                });
    }
}
