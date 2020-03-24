package com.zhenhaikj.factoryside.mvp.v3.mvp.model;

import com.zhenhaikj.factoryside.mvp.ApiRetrofit;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Article;
import com.zhenhaikj.factoryside.mvp.bean.UserInfo;
import com.zhenhaikj.factoryside.mvp.v3.mvp.contract.HomeContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeModel implements HomeContract.Model {
    @Override
    public Observable<BaseResult<UserInfo>> GetUserInfoList(String UserId, String limit) {
        return ApiRetrofit.getDefault().GetUserInfoList(UserId, limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Article>> GetListCategoryContentByCategoryID(String CategoryID, String page, String limit) {
        return  ApiRetrofit.getDefault().GetListCategoryContentByCategoryID(CategoryID,page,limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}