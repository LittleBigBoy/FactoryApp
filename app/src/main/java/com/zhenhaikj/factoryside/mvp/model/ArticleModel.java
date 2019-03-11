package com.zhenhaikj.factoryside.mvp.model;
import com.zhenhaikj.factoryside.mvp.ApiRetrofit;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Article;
import com.zhenhaikj.factoryside.mvp.contract.ArticleContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ArticleModel implements ArticleContract.Model {

    @Override
    public Observable<BaseResult<Article>> GetListCategoryContentByCategoryID(String CategoryID) {
        return ApiRetrofit.getDefault().GetListCategoryContentByCategoryID(CategoryID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
