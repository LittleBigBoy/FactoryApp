package com.zhenhaikj.factoryside.mvp.contract;


import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.Product;


import java.util.List;

import io.reactivex.Observable;


public interface ProductContract {
    interface Model extends BaseModel {
        Observable<BaseResult<List<Product>>> getProduct(String categoryid, String pageindex, String pagesize);
        Observable<BaseResult> addCart(String userid, String productid, String option, String num);
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
