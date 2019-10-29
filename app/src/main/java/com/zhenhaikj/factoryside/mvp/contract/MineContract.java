package com.zhenhaikj.factoryside.mvp.contract;

import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.CompanyInfo;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.Search;
import com.zhenhaikj.factoryside.mvp.bean.UserInfo;

import io.reactivex.Observable;
import okhttp3.RequestBody;


public interface MineContract {
    interface Model extends BaseModel{
        Observable<BaseResult<UserInfo>> GetUserInfoList(String UserId, String limit);
        Observable<BaseResult<Data<String>>> UploadAvator(RequestBody josn);
        Observable<BaseResult<Data<CompanyInfo>>> GetmessageBytype(String UserId);
        Observable<BaseResult<Search>> GetOrderInfoList(String Phone, String OrderID, String UserID, String limit, String page);
        Observable<BaseResult<Search>> GetUserOrderNum( String UserID);

    }

    interface View extends BaseView{
        void GetUserInfoList(BaseResult<UserInfo> baseResult);
        void UploadAvator(BaseResult<Data<String>> baseResult);
        void GetmessageBytype(BaseResult<Data<CompanyInfo>> baseResult);
        void GetOrderInfoList(BaseResult<Search> baseResult);
        void GetUserOrderNum(BaseResult<Search> baseResult);

    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void GetUserInfoList(String UserId,String limit);
        public abstract void UploadAvator(RequestBody josn);
        public abstract void GetmessageBytype(String UserId);
        public abstract void GetOrderInfoList(String Phone, String OrderID, String UserID,String limit, String page);
        public abstract void GetUserOrderNum( String UserID);

    }
}
