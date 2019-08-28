package com.zhenhaikj.factoryside.mvp.presenter;

import com.zhenhaikj.factoryside.mvp.base.BaseObserver;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.contract.WithdrawContract;

public class WithdrawPresenter extends WithdrawContract.Presenter {

    @Override
    public void GetBankNameByCardNo(String CardNo) {
        mModel.GetBankNameByCardNo(CardNo)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.GetBankNameByCardNo(value);
                    }
                });
    }
}
