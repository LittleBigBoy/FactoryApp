package com.zhenhaikj.factoryside.mvp.contract;



import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.Data;

import io.reactivex.Observable;

public interface OpinionContract {
    interface Model extends BaseModel{
        Observable<BaseResult<Data<String>>> AddOpinion(String UserId, String BackType, String Content );
    }

    interface View extends BaseView{
        void AddOpinion(BaseResult<Data<String>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void AddOpinion(String UserId,String BackType,String Content);
    }
}
