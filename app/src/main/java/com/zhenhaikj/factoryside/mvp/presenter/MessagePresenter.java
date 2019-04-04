package com.zhenhaikj.factoryside.mvp.presenter;

import com.zhenhaikj.factoryside.mvp.base.BaseObserver;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Message;
import com.zhenhaikj.factoryside.mvp.bean.MessageData;
import com.zhenhaikj.factoryside.mvp.contract.MessageContract;

import java.util.List;

public class MessagePresenter extends MessageContract.Presenter {
    @Override
    public void GetListmessageByType(String UserId, String Type, String SubType, String limit, String page) {
        mModel.GetListmessageByType(UserId, Type, SubType, limit, page)
                .subscribe(new BaseObserver< MessageData<List<Message>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<MessageData<List<Message>>> value) {
                        mView.GetListmessageByType(value);
                    }
                });
    }
}
