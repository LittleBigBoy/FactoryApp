package com.emjiayuan.factoryside.mvp.model;

import com.emjiayuan.factoryside.mvp.contract.ProductContract;
import com.emjiayuan.nll.ApiRetrofit;
import com.emjiayuan.nll.base.BaseResult;
import com.emjiayuan.nll.model.Product;

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
