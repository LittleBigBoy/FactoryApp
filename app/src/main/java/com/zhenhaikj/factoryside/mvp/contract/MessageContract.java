package com.zhenhaikj.factoryside.mvp.contract;

import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BaseObserver;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.Message;
import com.zhenhaikj.factoryside.mvp.bean.MessageData;

import java.util.List;

import io.reactivex.Observable;

public interface MessageContract  {
    interface Model extends BaseModel{
        Observable<BaseResult<MessageData<List<Message>>>> GetListmessageByType(String UserID, String Type, String SubType,String limit, String page);
    }

    interface View extends BaseView{
        void GetListmessageByType(BaseResult<MessageData<List<Message>>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void GetListmessageByType(String UserID, String Type, String SubType,String limit, String page);
    }
}
