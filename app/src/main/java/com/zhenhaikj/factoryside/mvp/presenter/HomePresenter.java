package com.zhenhaikj.factoryside.mvp.presenter;


import com.zhenhaikj.factoryside.mvp.contract.HomeContract;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.HomeData;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class HomePresenter extends HomeContract.Presenter {

    @Override
    public void getData(String userid) {
        mModel.getData(userid)
                .subscribe(new Observer<BaseResult<HomeData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult<HomeData> baseResult) {
                        if ("200".equals(baseResult.getStatusCode())){
                            mView.hideProgress();
                            mView.success(baseResult);
                        }else{

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.hideProgress();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
