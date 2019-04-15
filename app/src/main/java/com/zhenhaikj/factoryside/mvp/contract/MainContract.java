package com.zhenhaikj.factoryside.mvp.contract;

import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.Message;
import com.zhenhaikj.factoryside.mvp.bean.MessageData;

import java.util.List;

import io.reactivex.Observable;

public interface MainContract {
    interface Model extends BaseModel {

        Observable<BaseResult<MessageData<List<Message>>>> GetMessageList(String UserID, String SubType, String limit, String page);

        Observable<BaseResult<MessageData<List<Message>>>> GetTransactionMessageList(String UserID, String SubType, String limit, String page);

    }

    interface View extends BaseView {

        void GetMessageList(BaseResult<MessageData<List<Message>>> baseResult);
        void GetTransactionMessageList(BaseResult<MessageData<List<Message>>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {

        public abstract void GetMessageList(String UserID,String SubType,String limit,String page);
        public abstract void GetTransactionMessageList(String UserID,String SubType,String limit,String page);
    }
}
