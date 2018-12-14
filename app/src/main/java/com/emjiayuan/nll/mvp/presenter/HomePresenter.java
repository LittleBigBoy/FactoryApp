package com.emjiayuan.nll.mvp.presenter;


import com.emjiayuan.nll.base.BaseResult;
import com.emjiayuan.nll.model.HomeData;
import com.emjiayuan.nll.mvp.contract.HomeContract;

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
                        if ("200".equals(baseResult.getCode())){
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
