package com.zhenhaikj.factoryside.mvp.presenter;

import com.zhenhaikj.factoryside.mvp.base.BaseObserver;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.Message;
import com.zhenhaikj.factoryside.mvp.bean.MessageData;
import com.zhenhaikj.factoryside.mvp.contract.MessageContract;

import java.util.List;

public class MessagePresenter extends MessageContract.Presenter {

    @Override
    public void GetMessageList(String UserID, String Type,String SubType,String limit,String page) {
        mModel.GetMessageList(UserID, Type,SubType,limit,page)
                .subscribe(new BaseObserver<MessageData<List<Message>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<MessageData<List<Message>>> value) {
                        mView.GetMessageList(value);
                    }
                });
    }

    @Override
    public void GetReadMessageList(String UserID, String Type, String SubType, String limit, String page) {
        mModel.GetReadMessageList(UserID, Type,SubType,limit,page)
                .subscribe(new BaseObserver<MessageData<List<Message>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<MessageData<List<Message>>> value) {
                        mView.GetReadMessageList(value);
                    }
                });
    }

    @Override
    public void AddOrUpdatemessage(String MessageID, String IsLook) {
        mModel.AddOrUpdatemessage(MessageID,IsLook)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.AddOrUpdatemessage(value);
                    }
                });
    }
}
