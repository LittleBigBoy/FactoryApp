package com.zhenhaikj.factoryside.mvp.model;


import com.zhenhaikj.factoryside.mvp.ApiRetrofit;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Accessory;
import com.zhenhaikj.factoryside.mvp.bean.Accessory2;
import com.zhenhaikj.factoryside.mvp.bean.GetFactoryData;
import com.zhenhaikj.factoryside.mvp.contract.NewAddAccessoriesContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NewAddAccessoriesModel implements NewAddAccessoriesContract.Model {

    @Override
    public Observable<BaseResult<GetFactoryData<Accessory2>>> GetFactoryAccessory(String FProductTypeID) {
        return ApiRetrofit.getDefault().GetFactoryAccessory2(FProductTypeID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

}
