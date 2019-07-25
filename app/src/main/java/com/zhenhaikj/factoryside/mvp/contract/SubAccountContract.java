package com.zhenhaikj.factoryside.mvp.contract;

import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.SubUserInfo;
import com.zhenhaikj.factoryside.mvp.bean.UserInfo;

import java.util.List;

import io.reactivex.Observable;

public interface SubAccountContract {
    interface Model extends BaseModel {
        /*获取账号信息列表*/
        Observable <BaseResult<UserInfo>> GetUserInfoList(String UserID, String limit);

        //获取子账号
        Observable<BaseResult<List<SubUserInfo.SubUserInfoDean>>> GetChildAccountByParentUserID(String ParentUserID);


        //注销子账号
        Observable<BaseResult<Data<String>>> CancelChildAccount(String UserID, String ParentUserID);
    }

    interface View extends BaseView {
        /*获取账号信息列表*/
        void GetUserInfoList(BaseResult<UserInfo> baseResult);

        /*获取子账号*/
        void GetChildAccountByParentUserID(BaseResult<List<SubUserInfo.SubUserInfoDean>> baseResult);
        //注销子账号
        void CancelChildAccount(BaseResult<Data<String>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        /*获取账号信息列表*/
        public abstract void GetUserInfoList(String UserID, String limit);

        /*获取子账号*/
        public abstract void GetChildAccountByParentUserID(String ParentUserID);

        //注销子账号
        public abstract void CancelChildAccount(String UserID,String ParentUserID);
    }

}
