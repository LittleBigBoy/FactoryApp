package com.zhenhaikj.factoryside.mvp.v3.mvp.contract;


import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.Article;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.GetMessagePag;
import com.zhenhaikj.factoryside.mvp.bean.LeaveMessage;
import com.zhenhaikj.factoryside.mvp.bean.Message;
import com.zhenhaikj.factoryside.mvp.bean.MessageData;

import java.util.List;

import io.reactivex.Observable;

public interface MessageContract {
    interface Model extends BaseModel {
        Observable<BaseResult<Article>> GetListCategoryContentByCategoryID(String CategoryID, String page, String limit);
        Observable<BaseResult<MessageData<List<Message>>>> GetOrderMessageList(String UserID, String SubType, String limit, String page);
        Observable<BaseResult<MessageData<List<Message>>>> GetTicketMessageList(String UserID, String SubType, String limit, String page);
        Observable<BaseResult<MessageData<List<Message>>>> GetReviewMessageList(String UserID, String SubType, String limit, String page);
        Observable<BaseResult<MessageData<List<Message>>>> GetComplaintMessageList(String UserID, String SubType, String limit, String page);
        Observable<BaseResult<MessageData<List<Message>>>> GetTransactionMessageList(String UserID, String SubType, String limit, String page);
        Observable<BaseResult<Data<LeaveMessage>>> GetNewsLeaveMessage(String UserID, String limit, String page);
        Observable<BaseResult<Data<GetMessagePag>>> GetmessagePag(String UserID);

    }

    interface View extends BaseView {
        void GetListCategoryContentByCategoryID(BaseResult<Article> baseResult);

        void GetOrderMessageList(BaseResult<MessageData<List<Message>>> baseResult);
        void GetTicketMessageList(BaseResult<MessageData<List<Message>>> baseResult);
        void GetReviewMessageList(BaseResult<MessageData<List<Message>>> baseResult);
        void GetComplaintMessageList(BaseResult<MessageData<List<Message>>> baseResult);
        void GetTransactionMessageList(BaseResult<MessageData<List<Message>>> baseResult);
        void GetNewsLeaveMessage(BaseResult<Data<LeaveMessage>> baseResult);
        void GetmessagePag(BaseResult<Data<GetMessagePag>> baseResult);

    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void GetListCategoryContentByCategoryID(String CategoryID,String page, String limit);
        public abstract void GetOrderMessageList(String UserID,String SubType,String limit,String page);
        public abstract void GetTicketMessageList(String UserID,String SubType,String limit,String page);
        public abstract void GetReviewMessageList(String UserID,String SubType,String limit,String page);
        public abstract void GetComplaintMessageList(String UserID,String SubType,String limit,String page);
        public abstract void GetTransactionMessageList(String UserID,String SubType,String limit,String page);
        public abstract void GetNewsLeaveMessage(String UserID, String limit, String page);
        public abstract void GetmessagePag(String UserID);
    }
}
