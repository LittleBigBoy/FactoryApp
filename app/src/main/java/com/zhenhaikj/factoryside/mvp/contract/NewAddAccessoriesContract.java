package com.zhenhaikj.factoryside.mvp.contract;


import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.Accessory;
import com.zhenhaikj.factoryside.mvp.bean.Accessory2;
import com.zhenhaikj.factoryside.mvp.bean.GetFactoryData;

import io.reactivex.Observable;

public interface NewAddAccessoriesContract {
    interface Model extends BaseModel {
        //获取工厂配件信息
        Observable<BaseResult<GetFactoryData<Accessory2>>> GetFactoryAccessory(String FProductTypeID);
    }

    interface View extends BaseView {
        //获取工厂配件信息
         void GetFactoryAccessory(BaseResult<GetFactoryData<Accessory2>> baseResult);
    }

    abstract  class Presenter extends BasePresenter<View,Model> {
        //获取工厂配件信息
        public abstract void GetFactoryAccessory(String FProductTypeID);
    }

}
