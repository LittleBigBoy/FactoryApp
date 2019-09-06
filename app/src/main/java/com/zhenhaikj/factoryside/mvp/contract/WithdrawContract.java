package com.zhenhaikj.factoryside.mvp.contract;

import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.BankCard;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.UserInfo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;

public interface WithdrawContract  {
    interface Model extends BaseModel{
        Observable<BaseResult<Data<String>>> GetBankNameByCardNo(String CardNo);
        Observable<BaseResult<Data<String>>> WithDrawDeposit( String DrawMoney,
                                                              String CardNo,
                                                              String UserID,
                                                              String CardName);
        Observable<BaseResult<UserInfo>> GetUserInfoList(String UserId, String limit);
        Observable<BaseResult<Data<String>>> AddorUpdateAccountPayInfo(String UserId,String PayInfoCode ,String PayInfoName,String PayNo,String PayName);
        Observable<BaseResult<List<BankCard>>> GetAccountPayInfoList(String UserId);
    }

    interface View extends BaseView{
        void GetBankNameByCardNo(BaseResult<Data<String>> baseResult);
        void WithDrawDeposit(BaseResult<Data<String>> baseResult);
        void GetUserInfoList(BaseResult<UserInfo> baseResult);
        void AddorUpdateAccountPayInfo(BaseResult<Data<String>> baseResult);
        void GetAccountPayInfoList(BaseResult<List<BankCard>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void GetBankNameByCardNo(String CardNo);
        public abstract void WithDrawDeposit(String DrawMoney,
                                             String CardNo,
                                             String UserID,
                                             String CardName);
        public abstract void GetUserInfoList(String UserId,String limit);
        public abstract void AddorUpdateAccountPayInfo(String UserId,String PayInfoCode ,String PayInfoName,String PayNo,String PayName);
        public abstract void GetAccountPayInfoList(String UserId);
    }
}
