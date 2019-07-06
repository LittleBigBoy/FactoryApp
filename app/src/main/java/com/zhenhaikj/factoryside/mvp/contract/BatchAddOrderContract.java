package com.zhenhaikj.factoryside.mvp.contract;

import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.Data;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Field;

public interface BatchAddOrderContract {
    interface Model extends BaseModel{
        Observable<BaseResult<String>> BatchAddOrder(RequestBody json);

    }

    interface View extends BaseView{
        void BatchAddOrder(BaseResult<String> baseResult);
    }


    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void BatchAddOrder(RequestBody json);

    }

}
