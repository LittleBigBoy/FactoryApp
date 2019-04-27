package com.zhenhaikj.factoryside.mvp.contract;


import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.HomeData;
import com.zhenhaikj.factoryside.mvp.bean.UserInfo;

import io.reactivex.Observable;


public interface HomeContract {
    interface Model extends BaseModel {
        Observable<BaseResult<HomeData>> getData(String userid);
        Observable<BaseResult<UserInfo>> GetUserInfoList(String UserId, String limit);
    }

    interface View extends BaseView {
        void success(BaseResult<HomeData> baseResult);
        void GetUserInfoList(BaseResult<UserInfo> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void getData(String userid);
        public abstract void GetUserInfoList(String UserId,String limit);
    }
}
