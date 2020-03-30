package com.zhenhaikj.factoryside.mvp.v3.mvp.presenter;


import com.zhenhaikj.factoryside.mvp.base.BaseObserver;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Article;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.GetMessagePag;
import com.zhenhaikj.factoryside.mvp.bean.LeaveMessage;
import com.zhenhaikj.factoryside.mvp.bean.Message;
import com.zhenhaikj.factoryside.mvp.bean.MessageData;
import com.zhenhaikj.factoryside.mvp.v3.mvp.contract.MessageContract;

import java.util.List;

public class MessagePresenter extends MessageContract.Presenter {
    @Override
    public void GetListCategoryContentByCategoryID(String CategoryID, String page, String limit) {
        mModel.GetListCategoryContentByCategoryID(CategoryID,page,limit)
                .subscribe(new BaseObserver<Article>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Article> value) {
                        mView.GetListCategoryContentByCategoryID(value);
                    }
                });
    }

    @Override
    public void GetOrderMessageList(String UserID, String SubType, String limit, String page) {
        mModel.GetOrderMessageList(UserID,SubType,limit,page)
                .subscribe(new BaseObserver<MessageData<List<Message>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<MessageData<List<Message>>> value) {
                        mView.GetOrderMessageList(value);
                    }
                });
    }

    @Override
    public void GetTicketMessageList(String UserID, String SubType, String limit, String page) {
        mModel.GetTicketMessageList(UserID,SubType,limit,page)
                .subscribe(new BaseObserver<MessageData<List<Message>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<MessageData<List<Message>>> value) {
                        mView.GetTicketMessageList(value);
                    }
                });
    }

    @Override
    public void GetReviewMessageList(String UserID, String SubType, String limit, String page) {
        mModel.GetReviewMessageList(UserID,SubType,limit,page)
                .subscribe(new BaseObserver<MessageData<List<Message>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<MessageData<List<Message>>> value) {
                        mView.GetReviewMessageList(value);
                    }
                });
    }

    @Override
    public void GetComplaintMessageList(String UserID, String SubType, String limit, String page) {
        mModel.GetComplaintMessageList(UserID,SubType,limit,page)
                .subscribe(new BaseObserver<MessageData<List<Message>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<MessageData<List<Message>>> value) {
                        mView.GetComplaintMessageList(value);
                    }
                });
    }

    @Override
    public void GetTransactionMessageList(String UserID, String SubType, String limit, String page) {
        mModel.GetTransactionMessageList(UserID,SubType,limit,page)
                .subscribe(new BaseObserver<MessageData<List<Message>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<MessageData<List<Message>>> value) {
                        mView.GetTransactionMessageList(value);
                    }
                });
    }

    @Override
    public void GetNewsLeaveMessage(String UserID, String limit, String page) {
        mModel.GetNewsLeaveMessage(UserID, limit, page)
                .subscribe(new BaseObserver<Data<LeaveMessage>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<LeaveMessage>> value) {
                        mView.GetNewsLeaveMessage(value);
                    }
                });
    }

    @Override
    public void GetmessagePag(String UserID) {
        mModel.GetmessagePag(UserID)
                .subscribe(new BaseObserver<Data<GetMessagePag>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<GetMessagePag>> value) {
                        mView.GetmessagePag(value);
                    }
                });
    }
}
