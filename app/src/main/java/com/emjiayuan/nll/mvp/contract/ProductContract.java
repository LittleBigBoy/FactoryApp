package com.emjiayuan.nll.mvp.contract;


import com.emjiayuan.nll.base.BaseModel;
import com.emjiayuan.nll.base.BasePresenter;
import com.emjiayuan.nll.base.BaseResult;
import com.emjiayuan.nll.base.BaseView;
import com.emjiayuan.nll.model.Product;

import java.util.List;

import io.reactivex.Observable;


public interface ProductContract {
    interface Model extends BaseModel {
        Observable<BaseResult<List<Product>>> getProduct(String categoryid, String pageindex,String pagesize);
        Observable<BaseResult> addCart(String userid, String productid,String option,String num);
    }

    interface View extends BaseView {
        void getProduct(BaseResult<List<Product>> baseResult);
        void getProductFail(BaseResult<List<Product>> baseResult);
        void addCart(BaseResult baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void addCart(String userid, String productid,String option,String num);
        public abstract void getProduct(String categoryid, String pageindex,String pagesize);
    }
}
