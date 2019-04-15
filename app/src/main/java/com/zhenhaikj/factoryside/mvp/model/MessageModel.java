package com.zhenhaikj.factoryside.mvp.model;

import com.zhenhaikj.factoryside.mvp.ApiRetrofit;
import com.zhenhaikj.factoryside.mvp.ApiService;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.Message;
import com.zhenhaikj.factoryside.mvp.bean.MessageData;
import com.zhenhaikj.factoryside.mvp.contract.MessageContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MessageModel implements MessageContract.Model {

    @Override
    public Observable<BaseResult<MessageData<List<Message>>>> GetMessageList(String UserID, String Type,String SubType, String limit, String page) {
        return ApiRetrofit.getDefault().GetMessageList(UserID, Type,SubType,limit,page,"1")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    /*已读*/
    @Override
    public Observable<BaseResult<MessageData<List<Message>>>> GetReadMessageList(String UserID, String Type, String SubType, String limit, String page) {
        return ApiRetrofit.getDefault().GetMessageList(UserID, Type,SubType,limit,page,"2")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> AddOrUpdatemessage(String MessageID, String IsLook) {
        return ApiRetrofit.getDefault().AddOrUpdatemessage(MessageID,IsLook)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
