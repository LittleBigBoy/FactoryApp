package com.zhenhaikj.factoryside.mvp.presenter;


import com.zhenhaikj.factoryside.mvp.contract.PurchaseContract;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Category;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class PurchasePresenter extends PurchaseContract.Presenter {

    @Override
    public void getData() {
        mModel.getData()
                .subscribe(new Observer<BaseResult<List<Category>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult<List<Category>> baseResult) {
                        if ("200".equals(baseResult.getCode())){
                            mView.hideProgress();
                            mView.success(baseResult);
                        }else{
                            mView.fail(baseResult);
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
