package com.zhenhaikj.factoryside.mvp.v3.mvp.contract;

import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.Article;
import com.zhenhaikj.factoryside.mvp.bean.UserInfo;

import io.reactivex.Observable;

public interface HomeContract {
    interface Model extends BaseModel{
        Observable<BaseResult<UserInfo>> GetUserInfoList(String UserId, String limit);
        Observable<BaseResult<Article>> GetListCategoryContentByCategoryID(String CategoryID, String page, String limit);
    }

    interface View extends BaseView{
        void GetUserInfoList(BaseResult<UserInfo> baseResult);
        void GetListCategoryContentByCategoryID(BaseResult<Article> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void GetUserInfoList(String UserId,String limit);
        public abstract void GetListCategoryContentByCategoryID(String CategoryID,String page, String limit);
    }
}