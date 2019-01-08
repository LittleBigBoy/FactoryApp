package com.zhenhaikj.factoryside.mvp.presenter;


import com.zhenhaikj.factoryside.mvp.contract.ProductContract;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Product;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ProductPresenter extends ProductContract.Presenter {

    @Override
    public void addCart(String userid, String productid, String option, String num) {
        mModel.addCart( userid,  productid, option, num)
                .subscribe(new Observer<BaseResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult baseResult) {
                        mView.hideProgress();
                        mView.addCart(baseResult);
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

    @Override
    public void getProduct(String categoryid, String pageindex, String pagesize) {
        mModel.getProduct( categoryid,  pageindex, pagesize)
                .subscribe(new Observer<BaseResult<List<Product>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult<List<Product>> baseResult) {
                        if ("200".equals(baseResult.getCode())){
                            mView.hideProgress();
                            mView.getProduct(baseResult);
                        }else{
                            mView.getProductFail(baseResult);
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
