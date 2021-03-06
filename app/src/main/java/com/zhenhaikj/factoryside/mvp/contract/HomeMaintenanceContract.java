package com.zhenhaikj.factoryside.mvp.contract;


import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.Accessory;
import com.zhenhaikj.factoryside.mvp.bean.Address;
import com.zhenhaikj.factoryside.mvp.bean.Area;
import com.zhenhaikj.factoryside.mvp.bean.Brand;
import com.zhenhaikj.factoryside.mvp.bean.Category;
import com.zhenhaikj.factoryside.mvp.bean.CategoryData;
import com.zhenhaikj.factoryside.mvp.bean.City;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.District;
import com.zhenhaikj.factoryside.mvp.bean.GetCategory;
import com.zhenhaikj.factoryside.mvp.bean.ProductType;
import com.zhenhaikj.factoryside.mvp.bean.Province;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;


public interface HomeMaintenanceContract {
    interface Model extends BaseModel {
        Observable<BaseResult<List<Brand>>> GetFactoryBrand(String UserID);

        Observable<BaseResult<CategoryData>> GetFactoryCategory(String ParentID);

        Observable<BaseResult<CategoryData>> GetChildFactoryCategory(String ParentID);

        Observable<BaseResult<Data<List<ProductType>>>> GetFactoryProducttype(String FBrandID, String FCategoryID);

        Observable<BaseResult<Accessory>> GetFactoryAccessory(String FProductTypeID);

        Observable<BaseResult<List<Province>>> GetProvince();

        Observable<BaseResult<Data<List<City>>>> GetCity(String parentcode);

        Observable<BaseResult<Data<List<Area>>>> GetArea(String parentcode);

        Observable<BaseResult<Data<List<District>>>> GetDistrict(String parentcode);

        Observable<BaseResult<Data<String>>> AddOrder(
//                String TypeID,
//                String TypeName,
//                String UserID,
//                String FBrandID,
//                String BrandName,
//                String FCategoryID,
//                String CategoryName,
//                String SubCategoryID,
//                String SubCategoryName,
//                String ProvinceCode,
//                String CityCode,
//                String AreaCode,
//                String DistrictCode,
//                String Address,
//                String UserName,
//                String Phone,
//                String Memo,
//                String OrderMoney,
//                String RecycleOrderHour,
//                String Guarantee,
//                String Extra,
//                String ExtraTime,
//                String ExtraFee,
//                String Num,
//                String IsRecevieGoods,
//                String ExpressNo,
                RequestBody json);

        Observable<BaseResult<CategoryData>> GetChildFactoryCategory2(String ParentId);

        Observable<BaseResult<List<Address>>> GetAccountAddress(String UserId);

        Observable<BaseResult<Data<Category>>> GetBrandCategory(String UserID);

        Observable<BaseResult<Data<List<GetCategory>>>> GetBrandWithCategory( String UserID, String BrandID);

        Observable<BaseResult<String>> GetUniqId();

    }

    interface View extends BaseView {
        void GetFactoryBrand(BaseResult<List<Brand>> baseResult);

        void GetFactoryCategory(BaseResult<CategoryData> baseResult);

        void GetChildFactoryCategory(BaseResult<CategoryData> baseResult);

        void GetChildFactoryCategory2(BaseResult<CategoryData> baseResult);

        void GetFactoryProducttype(BaseResult<Data<List<ProductType>>> baseResult);

        void GetFactoryAccessory(BaseResult<Accessory> baseResult);

        void GetProvince(BaseResult<List<Province>> baseResult);

        void GetCity(BaseResult<Data<List<City>>> baseResult);

        void GetArea(BaseResult<Data<List<Area>>> baseResult);

        void GetDistrict(BaseResult<Data<List<District>>> baseResult);

        void AddOrder(BaseResult<Data<String>> baseResult);

        void GetAccountAddress(BaseResult<List<Address>> baseResult);

        void GetBrandCategory(BaseResult<Data<Category>> baseResult);

        void GetBrandWithCategory(BaseResult<Data<List<GetCategory>>> baseResult);

        void GetUniqId(BaseResult<String> baseResult);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void GetFactoryBrand(String UserID);

        public abstract void GetFactoryCategory(String ParentID);

        public abstract void GetChildFactoryCategory(String ParentID);

        public abstract void GetChildFactoryCategory2(String ParentID);

        public abstract void GetFactoryProducttype(String FBrandID, String FCategoryID);

        public abstract void GetFactoryAccessory(String FProductTypeID);

        public abstract void GetProvince();

        public abstract void GetCity(String parentcode);

        public abstract void GetArea(String parentcode);

        public abstract void GetDistrict(String parentcode);

        public abstract void AddOrder(
//                String TypeID,
//                String TypeName,
//                String UserID,
//                String FBrandID,
//                String BrandName,
//                String FCategoryID,
//                String CategoryName,
//                String SubCategoryID,
//                String SubCategoryName,
//                String ProvinceCode,
//                String CityCode,
//                String AreaCode,
//                String DistrictCode,
//                String Address,
//                String UserName,
//                String Phone,
//                String Memo,
//                String OrderMoney,
//                String RecycleOrderHour,
//                String Guarantee,
//                String Extra,
//                String ExtraTime,
//                String ExtraFee,
//                String Num,
//                String IsRecevieGoods,
//                String ExpressNo,
                RequestBody json);

        public abstract void GetAccountAddress(String UserId);

        public abstract void GetBrandCategory(String UserID);

        public abstract void GetBrandWithCategory(String UserID, String BrandID);

        public abstract void GetUniqId();
    }
}
