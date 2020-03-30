package com.zhenhaikj.factoryside.mvp.v3.mvp.model;

import com.zhenhaikj.factoryside.mvp.ApiRetrofit;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Article;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.GetMessagePag;
import com.zhenhaikj.factoryside.mvp.bean.LeaveMessage;
import com.zhenhaikj.factoryside.mvp.bean.Message;
import com.zhenhaikj.factoryside.mvp.bean.MessageData;
import com.zhenhaikj.factoryside.mvp.v3.mvp.contract.MessageContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MessageModel implements MessageContract.Model {
    @Override
    public Observable<BaseResult<Article>> GetListCategoryContentByCategoryID(String CategoryID, String page, String limit) {
        return ApiRetrofit.getDefault().GetListCategoryContentByCategoryID(CategoryID, page, limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<MessageData<List<Message>>>> GetOrderMessageList(String UserID, String SubType, String limit, String page) {
        return ApiRetrofit.getDefault().GetMessageList(UserID, "2",SubType,limit,page,"1")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<MessageData<List<Message>>>> GetTicketMessageList(String UserID, String SubType, String limit, String page) {
        return  ApiRetrofit.getDefault().GetMessageList(UserID, "2",SubType,limit,page,"1")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<MessageData<List<Message>>>> GetReviewMessageList(String UserID, String SubType, String limit, String page) {
        return  ApiRetrofit.getDefault().GetMessageList(UserID, "2",SubType,limit,page,"1")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<MessageData<List<Message>>>> GetComplaintMessageList(String UserID, String SubType, String limit, String page) {
        return ApiRetrofit.getDefault().GetMessageList(UserID, "2",SubType,limit,page,"1")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<MessageData<List<Message>>>> GetTransactionMessageList(String UserID, String SubType, String limit, String page) {
        return  ApiRetrofit.getDefault().GetMessageList(UserID, "1",SubType,limit,page,"1")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<LeaveMessage>>> GetNewsLeaveMessage(String UserID, String limit, String page) {
        return ApiRetrofit.getDefault().GetNewsLeaveMessage(UserID,"1",limit,page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<GetMessagePag>>> GetmessagePag(String UserID) {
        return ApiRetrofit.getDefault().GetmessagePag(UserID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
