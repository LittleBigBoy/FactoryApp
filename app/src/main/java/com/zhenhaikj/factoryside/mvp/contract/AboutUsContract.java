package com.zhenhaikj.factoryside.mvp.contract;

import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.Article;

import io.reactivex.Observable;

public interface AboutUsContract {
    interface Model extends BaseModel{
        Observable<BaseResult<Article>> GetListCategoryContentByCategoryID(String CategoryID, String page, String limit);
    }

    interface View extends BaseView{
        void GetListCategoryContentByCategoryID(BaseResult<Article> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void GetListCategoryContentByCategoryID(String CategoryID,String page, String limit);

    }
}
