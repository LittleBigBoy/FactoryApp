package com.zhenhaikj.factoryside.mvp.activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.adapter.AccessoryAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.BrandsAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.CategoryAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.CityAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.ProductTypeAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.ProvinceAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Accessory;
import com.zhenhaikj.factoryside.mvp.bean.Brand;
import com.zhenhaikj.factoryside.mvp.bean.Category;
import com.zhenhaikj.factoryside.mvp.bean.City;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.ProductType;
import com.zhenhaikj.factoryside.mvp.bean.Province;
import com.zhenhaikj.factoryside.mvp.contract.HomeMaintenanceContract;
import com.zhenhaikj.factoryside.mvp.model.HomeMaintenanceModel;
import com.zhenhaikj.factoryside.mvp.presenter.HomeMaintenancePresenter;
import com.zhenhaikj.factoryside.mvp.utils.MyUtils;

import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeMaintenanceActivity extends BaseActivity<HomeMaintenancePresenter, HomeMaintenanceModel> implements View.OnClickListener, HomeMaintenanceContract.View {


    @BindView(R.id.icon_back)
    ImageView mIconBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.icon_search)
    ImageView mIconSearch;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_register)
    TextView mTvRegister;
    @BindView(R.id.tv_add_product)
    TextView mTvAddProduct;
    @BindView(R.id.tv_detail)
    TextView mTvDetail;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.rb1)
    RadioButton mRb1;
    @BindView(R.id.rb2)
    RadioButton mRb2;
    @BindView(R.id.rg1)
    RadioGroup mRg1;
    @BindView(R.id.tv_add)
    TextView mTvAdd;
    @BindView(R.id.tv_delete)
    TextView mTvDelete;
    @BindView(R.id.rb_yes)
    RadioButton mRbYes;
    @BindView(R.id.rb_no)
    RadioButton mRbNo;
    @BindView(R.id.rg2)
    RadioGroup mRg2;
    @BindView(R.id.tv_fault_description)
    TextView mTvFaultDescription;
    @BindView(R.id.login)
    Button mLogin;
    @BindView(R.id.tv_choose_brand)
    TextView mTvChooseBrand;
    @BindView(R.id.tv_choose_category)
    TextView mTvChooseCategory;
    @BindView(R.id.tv_choose_type)
    TextView mTvChooseType;
    @BindView(R.id.tv_choose_property)
    TextView mTvChooseProperty;
    @BindView(R.id.tv_province)
    TextView mTvProvince;
    @BindView(R.id.tv_city)
    TextView mTvCity;
    @BindView(R.id.tv_area)
    TextView mTvArea;
    private PopupWindow popupWindow;
    private List<Province> provinceList;
    private List<City> cityList;
    private List<City> areaList;
    private List<Brand> brandList;
    private List<Category> categoryList;
    private List<ProductType> productTypeList;
    private List<Accessory.Item1Bean> accessoryList;
    private String userID;
    private String FBrandID;
    private String FCategoryID;
    private String FProductTypeID;
    private String FAccessoryID;
    private String ProvinceCode;
    private String CityCode;
    private BrandsAdapter brandsAdapter;
    private CategoryAdapter categoryAdapter;
    private ProductTypeAdapter productTypeAdapter;
    private AccessoryAdapter accessoryAdapter;
    private ProvinceAdapter provinceAdapter;
    private CityAdapter cityAdapter;
    private CityAdapter areaAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_home_maintenance;
    }

    @Override
    protected void initData() {
        SPUtils spUtils = SPUtils.getInstance("token");
        userID = spUtils.getString("userName");
    }

    @Override
    protected void initView() {
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText("上门维修");
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mIconSearch.setOnClickListener(this);

        mTvChooseBrand.setOnClickListener(this);
        mTvChooseCategory.setOnClickListener(this);
        mTvChooseType.setOnClickListener(this);
        mTvChooseProperty.setOnClickListener(this);

        mTvProvince.setOnClickListener(this);
        mTvCity.setOnClickListener(this);
        mTvArea.setOnClickListener(this);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.tv_choose_brand:
                mPresenter.GetFactoryBrand(userID);
                break;
            case R.id.tv_choose_category:
                mPresenter.GetFactoryCategory();
                break;
            case R.id.tv_choose_type:
                if (FBrandID == null) {
                    MyUtils.showToast(mActivity, "请选择品牌！");
                    return;
                }
                if (FCategoryID == null) {
                    MyUtils.showToast(mActivity, "请选择分类！");
                    return;
                }
                mPresenter.GetFactoryProducttype(FBrandID, FCategoryID);
                break;
            case R.id.tv_choose_property:
                if (FProductTypeID == null) {
                    MyUtils.showToast(mActivity, "请选择型号！");
                    return;
                }
                mPresenter.GetFactoryAccessory(FProductTypeID);
                break;
            case R.id.tv_province:
                mPresenter.GetProvince();
                break;
            case R.id.tv_city:
                if (ProvinceCode == null) {
                    MyUtils.showToast(mActivity, "请选择省！");
                    return;
                }
                mPresenter.GetCity(ProvinceCode);
                break;
            case R.id.tv_area:
                if (CityCode == null) {
                    MyUtils.showToast(mActivity, "请选择市！");
                    return;
                }
                mPresenter.GetArea(CityCode);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public void showPopWindow(final TextView tv, BaseQuickAdapter adapter, final List list) {

        View contentView = LayoutInflater.from(mActivity).inflate(R.layout.category_pop, null);
        final RecyclerView rv = contentView.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(mActivity));
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                popupWindow.dismiss();
                if (list.get(position) instanceof Brand) {
                    tv.setText(((Brand) list.get(position)).getFBrandName());
                    FBrandID = ((Brand) list.get(position)).getFBrandID();
                }
                if (list.get(position) instanceof Category) {
                    tv.setText(((Category) list.get(position)).getFCategoryName());
                    FCategoryID = ((Category) list.get(position)).getFCategoryID();
                }
                if (list.get(position) instanceof ProductType) {
                    tv.setText(((ProductType) list.get(position)).getFProductTypeName());
                    FProductTypeID = ((ProductType) list.get(position)).getFProductTypeID();
                }
                if (list.get(position) instanceof Accessory.Item1Bean) {
                    tv.setText(((Accessory.Item1Bean) list.get(position)).getAccessoryName());
                    FAccessoryID = ((Accessory.Item1Bean) list.get(position)).getFAccessoryID();
                }
                if (list.get(position) instanceof Province) {
                    tv.setText(((Province) list.get(position)).getName());
                    ProvinceCode = ((Province) list.get(position)).getCode();
                }
                if (list.get(position) instanceof City) {
                    tv.setText(((City) list.get(position)).getName());
                    CityCode = ((City) list.get(position)).getCode();
                }
            }
        });
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        popupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
//                MyUtils.setWindowAlpa(mActivity, false);
            }
        });
        if (popupWindow != null && !popupWindow.isShowing()) {
            popupWindow.showAsDropDown(tv, 0, 10);
//            popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        }

    }

    @Override
    public void GetFactoryBrand(BaseResult<List<Brand>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                brandList = baseResult.getData();
                brandsAdapter = new BrandsAdapter(R.layout.category_item, brandList);
                showPopWindow(mTvChooseBrand, brandsAdapter, brandList);
                break;
            case 401:
//                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }

    @Override
    public void GetFactoryCategory(BaseResult<Data<List<Category>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<List<Category>> data = baseResult.getData();
                if (data.isItem1()) {
                    categoryList = data.getItem2();
                    categoryAdapter = new CategoryAdapter(R.layout.category_item, categoryList);
                    showPopWindow(mTvChooseCategory, categoryAdapter, categoryList);
                } else {
                    MyUtils.showToast(mActivity, "获取分类失败！");
                }
                break;
            case 401:
//                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }

    @Override
    public void GetFactoryProducttype(BaseResult<Data<List<ProductType>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<List<ProductType>> data = baseResult.getData();
                if (data.isItem1()) {
                    productTypeList = data.getItem2();
                    productTypeAdapter = new ProductTypeAdapter(R.layout.category_item, productTypeList);
                    showPopWindow(mTvChooseType, productTypeAdapter, productTypeList);
                } else {
                    MyUtils.showToast(mActivity, "获取型号失败！");
                }
                break;
            case 401:
//                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }

    @Override
    public void GetFactoryAccessory(BaseResult<Accessory> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Accessory data = baseResult.getData();
                accessoryList = data.getItem1();
                accessoryAdapter = new AccessoryAdapter(R.layout.category_item, accessoryList);
                showPopWindow(mTvChooseProperty, accessoryAdapter, accessoryList);
                break;
            case 401:
//                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }

    @Override
    public void GetProvince(BaseResult<List<Province>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                provinceList = baseResult.getData();
                provinceAdapter = new ProvinceAdapter(R.layout.category_item, provinceList);
                showPopWindow(mTvProvince, provinceAdapter, provinceList);
                break;
            case 401:
//                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }

    @Override
    public void GetCity(BaseResult<Data<List<City>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<List<City>> data = baseResult.getData();
                if (data.isItem1()) {
                    cityList = data.getItem2();
                    cityAdapter = new CityAdapter(R.layout.category_item, cityList);
                    showPopWindow(mTvCity, cityAdapter, cityList);
                } else {
                    MyUtils.showToast(mActivity, "获取型号失败！");
                }

                break;
            case 401:
//                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }

    @Override
    public void GetArea(BaseResult<Data<List<City>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<List<City>> data = baseResult.getData();
                if (data.isItem1()) {
                    areaList = data.getItem2();
                    areaAdapter = new CityAdapter(R.layout.category_item, areaList);
                    showPopWindow(mTvArea, areaAdapter, areaList);
                } else {
                    MyUtils.showToast(mActivity, "获取型号失败！");
                }

                break;
            case 401:
//                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }
}