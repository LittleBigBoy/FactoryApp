package com.zhenhaikj.factoryside.mvp.contract;

import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.Data;

import io.reactivex.Observable;

public interface WithdrawContract  {
    interface Model extends BaseModel{
        Observable<BaseResult<Data<String>>> GetBankNameByCardNo(String CardNo);
    }

    interface View extends BaseView{
        void GetBankNameByCardNo(BaseResult<Data<String>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void GetBankNameByCardNo(String CardNo);
    }
}
