package com.zhenhaikj.factoryside.mvp.presenter;

import com.zhenhaikj.factoryside.mvp.base.BaseObserver;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Message;
import com.zhenhaikj.factoryside.mvp.bean.MessageData;
import com.zhenhaikj.factoryside.mvp.contract.MainContract;

import java.util.List;

public class MainPresenter extends MainContract.Presenter{
    @Override
    public void GetMessageList(String UserID, String SubType, String limit, String page) {
        mModel.GetMessageList(UserID,SubType,limit,page)
                .subscribe(new BaseObserver<MessageData<List<Message>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<MessageData<List<Message>>> value) {
                        mView.GetMessageList(value);
                    }
                });
    }

    @Override
    public void GetTransactionMessageList(String UserID, String SubType, String limit, String page) {
        mModel.GetMessageList(UserID,SubType,limit,page)
                .subscribe(new BaseObserver<MessageData<List<Message>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<MessageData<List<Message>>> value) {
                        mView.GetMessageList(value);
                    }
                });
    }
}
