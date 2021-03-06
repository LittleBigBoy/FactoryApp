package com.zhenhaikj.factoryside.mvp.contract;

import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.Area;
import com.zhenhaikj.factoryside.mvp.bean.Brand;
import com.zhenhaikj.factoryside.mvp.bean.Category;
import com.zhenhaikj.factoryside.mvp.bean.CategoryData;
import com.zhenhaikj.factoryside.mvp.bean.City;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.District;
import com.zhenhaikj.factoryside.mvp.bean.ProductType;
import com.zhenhaikj.factoryside.mvp.bean.Province;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;

public interface HomeInstallationContract {
    interface Model extends BaseModel {
        Observable<BaseResult<List<Brand>>> GetFactoryBrand(String UserID);

        Observable<BaseResult<CategoryData>> GetFactoryCategory(String ParentID);

        Observable<BaseResult<CategoryData>> GetChildFactoryCategory(String ParentID);

        Observable<BaseResult<Data<List<ProductType>>>> GetFactoryProducttype(String FBrandID, String FCategoryID);

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
    }

    interface View extends BaseView {
        void GetFactoryBrand(BaseResult<List<Brand>> baseResult);

        void GetFactoryCategory(BaseResult<CategoryData> baseResult);

        void GetChildFactoryCategory(BaseResult<CategoryData> baseResult);

        void GetFactoryProducttype(BaseResult<Data<List<ProductType>>> baseResult);

        void GetProvince(BaseResult<List<Province>> baseResult);

        void GetCity(BaseResult<Data<List<City>>> baseResult);

        void GetArea(BaseResult<Data<List<Area>>> baseResult);

        void GetDistrict(BaseResult<Data<List<District>>> baseResult);

        void AddOrder(BaseResult<Data<String>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void GetFactoryBrand(String UserID);

        public abstract void GetFactoryCategory(String ParentID);

        public abstract void GetChildFactoryCategory(String ParentID);

        public abstract void GetFactoryProducttype(String FBrandID, String FCategoryID);

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
    }
}
