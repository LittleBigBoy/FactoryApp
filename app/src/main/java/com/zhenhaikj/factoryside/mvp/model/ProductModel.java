package com.zhenhaikj.factoryside.mvp.model;

import com.zhenhaikj.factoryside.mvp.contract.ProductContract;
import com.zhenhaikj.factoryside.mvp.ApiRetrofit;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Product;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class ProductModel implements ProductContract.Model {
    @Override
    public Observable<BaseResult<List<Product>>> getProduct(String categoryid, String pageindex,String pagesize) {
        return ApiRetrofit.getDefault("product.getProductList").getProduct(  categoryid,  pageindex, pagesize)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
    @Override
    public Observable<BaseResult> addCart(String userid, String productid,String option,String num) {
        return ApiRetrofit.getDefault("cart.addOrUpdateCart").addCart( userid,  productid, option, num)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
