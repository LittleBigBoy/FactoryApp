package com.zhenhaikj.factoryside.mvp.presenter;


import com.zhenhaikj.factoryside.mvp.base.BaseObserver;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Accessory;
import com.zhenhaikj.factoryside.mvp.bean.Accessory2;
import com.zhenhaikj.factoryside.mvp.bean.GetFactoryData;
import com.zhenhaikj.factoryside.mvp.contract.NewAddAccessoriesContract;

public class NewAddAccessoriesPresenter extends NewAddAccessoriesContract.Presenter {

     @Override
     public void GetFactoryAccessory(String FProductTypeID) {
         mModel.GetFactoryAccessory(FProductTypeID).subscribe(new BaseObserver<GetFactoryData<Accessory2>>() {
             @Override
             protected void onHandleSuccess(BaseResult<GetFactoryData<Accessory2>> value) {
                 mView.GetFactoryAccessory(value);
             }
         });
     }

}
