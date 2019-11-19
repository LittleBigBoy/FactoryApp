package com.zhenhaikj.factoryside.mvp.contract;

import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BaseObserver;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.Message;
import com.zhenhaikj.factoryside.mvp.bean.MessageData;

import java.util.List;

import io.reactivex.Observable;

public interface MessageContract  {
    interface Model extends BaseModel{
        Observable<BaseResult<MessageData<List<Message>>>> GetMessageList(String UserID, String Type, String SubType,String limit, String page);

        Observable<BaseResult<MessageData<List<Message>>>> GetReadMessageList(String UserID, String Type, String SubType,String limit, String page);
        Observable<BaseResult<MessageData<List<Message>>>> AllRead(String UserID, String Type, String SubType);

        /*更改为已读*/
        Observable<BaseResult<Data<String>>> AddOrUpdatemessage(String MessageID, String IsLook);

    }

    interface View extends BaseView{
        void GetMessageList(BaseResult<MessageData<List<Message>>> baseResult);
        void AllRead(BaseResult<MessageData<List<Message>>> baseResult);

        void GetReadMessageList(BaseResult<MessageData<List<Message>>> baseResult);
        void AddOrUpdatemessage(BaseResult<Data<String>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void GetMessageList(String UserID,String Type,String SubType,String limit,String page);
        public abstract void AllRead(String UserID,String Type,String SubType);
        public abstract void GetReadMessageList(String UserID,String Type,String SubType,String limit,String page);
        public abstract void AddOrUpdatemessage(String MessageID,String IsLook);
    }
}
