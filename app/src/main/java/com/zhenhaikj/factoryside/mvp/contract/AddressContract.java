package com.zhenhaikj.factoryside.mvp.contract;


import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.Address;
import com.zhenhaikj.factoryside.mvp.bean.Area;
import com.zhenhaikj.factoryside.mvp.bean.Brand;
import com.zhenhaikj.factoryside.mvp.bean.City;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.District;
import com.zhenhaikj.factoryside.mvp.bean.Province;

import java.util.List;

import io.reactivex.Observable;


public interface AddressContract{
    interface Model extends BaseModel {
        Observable<BaseResult<Data<String>>> AddAccountAddress(
                String UserID,
                String Province,
                String City,
                String Area,
                String District,
                String Address,
                String Default,
                String UserName,
                String Phone
        );
        Observable<BaseResult<Data<String>>> UpdateAccountAddress(
                String ID,
                String UserID,
                String Province,
                String City,
                String Area,
                String District,
                String Address,
                String Default,
                String UserName,
                String Phone
        );
        Observable<BaseResult<Data<String>>> DeleteAccountAddress(
                String ID
        );
        Observable<BaseResult<List<Address>>> GetAccountAddress(String UserId);
        Observable<BaseResult<List<Province>>> GetProvince();
        Observable<BaseResult<Data<List<City>>>> GetCity(String parentcode);
        Observable<BaseResult<Data<List<Area>>>> GetArea(String parentcode);
        Observable<BaseResult<Data<List<District>>>> GetDistrict(String parentcode);
    }

    interface View extends BaseView {
        void AddAccountAddress(BaseResult<Data<String>> baseResult);
        void UpdateAccountAddress(BaseResult<Data<String>> baseResult);
        void DeleteAccountAddress(BaseResult<Data<String>> baseResult);
        void GetAccountAddress(BaseResult<List<Address>> baseResult);
        void GetProvince(BaseResult<List<Province>> baseResult);
        void GetCity(BaseResult<Data<List<City>>> baseResult);
        void GetArea(BaseResult<Data<List<Area>>> baseResult);
        void GetDistrict(BaseResult<Data<List<District>>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void AddAccountAddress(
                String UserID,
                String Province,
                String City,
                String Area,
                String District,
                String Address,
                String Default,
                String UserName,
                String Phone
        );
        public abstract void UpdateAccountAddress(
                String ID,
                String UserID,
                String Province,
                String City,
                String Area,
                String District,
                String Address,
                String Default,
                String UserName,
                String Phone
        );
        public abstract void DeleteAccountAddress(
                String ID
        );
        public abstract void GetAccountAddress(String UserId);
        public abstract void GetProvince();

        public abstract void GetCity(String parentcode);

        public abstract void GetArea(String parentcode);

        public abstract void GetDistrict(String parentcode);
    }
}
