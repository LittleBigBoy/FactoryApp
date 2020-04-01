package com.zhenhaikj.factoryside.mvp.v3.mvp.contract;

import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.Article;
import com.zhenhaikj.factoryside.mvp.bean.CompanyInfo;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.FactoryNavigationBarNumber;
import com.zhenhaikj.factoryside.mvp.bean.UserInfo;

import java.util.List;

import io.reactivex.Observable;

public interface HomeContract {
    interface Model extends BaseModel{
        Observable<BaseResult<UserInfo>> GetUserInfoList(String UserId, String limit);
        Observable<BaseResult<Article>> GetListCategoryContentByCategoryID(String CategoryID, String page, String limit);
        Observable<BaseResult<Data<CompanyInfo>>> GetmessageBytype(String UserId);
        Observable<BaseResult<Data<FactoryNavigationBarNumber>>> FactoryNavigationBarNumber(String UserID,  String state,  String page,String limit);
        Observable<BaseResult<Data<String>>> messgIsOrNo(String UserID, String limit, String page);
    }

    interface View extends BaseView{
        void GetUserInfoList(BaseResult<UserInfo> baseResult);
        void GetListCategoryContentByCategoryID(BaseResult<Article> baseResult);
        void GetmessageBytype(BaseResult<Data<CompanyInfo>> baseResult);
        void FactoryNavigationBarNumber(BaseResult<Data<FactoryNavigationBarNumber>> baseResult);
        void messgIsOrNo(BaseResult<Data<String>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void GetUserInfoList(String UserId,String limit);
        public abstract void GetListCategoryContentByCategoryID(String CategoryID,String page, String limit);
        public abstract void GetmessageBytype(String UserId);
        public abstract void FactoryNavigationBarNumber(String UserID,  String state,  String page,String limit);
        public abstract void messgIsOrNo(String UserID, String limit, String page);
    }
}
