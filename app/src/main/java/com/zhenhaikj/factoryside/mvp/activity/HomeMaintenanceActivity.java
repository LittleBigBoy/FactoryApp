package com.zhenhaikj.factoryside.mvp.activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.adapter.AccessoryAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.AreaAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.BrandsAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.CategoryAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.CityAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.ProductTypeAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.ProvinceAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Accessory;
import com.zhenhaikj.factoryside.mvp.bean.Area;
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

import androidx.appcompat.widget.AppCompatSpinner;
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
    @BindView(R.id.tv_add)
    TextView mTvAdd;
    @BindView(R.id.tv_delete)
    TextView mTvDelete;
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
    @BindView(R.id.et_detail)
    EditText mEtDetail;
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.cb_under_warranty)
    CheckBox mCbUnderWarranty;
    @BindView(R.id.ll_under_warranty)
    LinearLayout mLlUnderWarranty;
    @BindView(R.id.cb_outside_the_warranty)
    CheckBox mCbOutsideTheWarranty;
    @BindView(R.id.ll_outside_the_warranty)
    LinearLayout mLlOutsideTheWarranty;
    @BindView(R.id.cb_yes)
    CheckBox mCbYes;
    @BindView(R.id.ll_yes)
    LinearLayout mLlYes;
    @BindView(R.id.cb_no)
    CheckBox mCbNo;
    @BindView(R.id.ll_no)
    LinearLayout mLlNo;
    @BindView(R.id.et_fault_description)
    EditText mEtFaultDescription;
    @BindView(R.id.tv_expedited)
    TextView mTvExpedited;
    @BindView(R.id.btn_release)
    Button mBtnRelease;
    @BindView(R.id.et_recovery_time)
    EditText mEtRecoveryTime;
    @BindView(R.id.spinner)
    AppCompatSpinner mSpinner;
    @BindView(R.id.tv_pca)
    TextView mTvPca;
    private PopupWindow popupWindow;
    private List<Province> provinceList;
    private List<City> cityList;
    private List<Area> areaList;
    private List<Brand> brandList;
    private List<Category> categoryList;
    private List<ProductType> productTypeList;
    private List<Accessory.Item1Bean> accessoryList;
    private String userID;//用户id
    private String FBrandID;//品牌id
    private String FCategoryID;//分类id
    private String FProductTypeID;//型号id
    private String FAccessoryID;//配件id
    private String ProvinceCode;//省code
    private String CityCode;//市code
    private String AreaCode;//区code
    private String ProvinceName;
    private String CityName;
    private String AreaName;
    private String BrandName;
    private String CategoryName;
    private String ProductTypeName;
    private String OrderMoney;
    private String Address;//详细地址
    private String DetailAddress;//详细地址
    private String Name;//客户姓名
    private String Phone;//客户手机
    private String FaultDescription;//故障描述
    private String RecycleOrderHour;//回收时间
    private String Guarantee;//保内Y保外N
    private String AccessorySendState;//是否已发配件 Y是N否
    private String Extra;//是否加急Y是N否
    private String ExtraTime;//加急时间
    private String ExtraFee;//加急费用
    private BrandsAdapter brandsAdapter;
    private CategoryAdapter categoryAdapter;
    private ProductTypeAdapter productTypeAdapter;
    private AccessoryAdapter accessoryAdapter;
    private ProvinceAdapter provinceAdapter;
    private CityAdapter cityAdapter;
    private AreaAdapter areaAdapter;

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
        mTvChooseProperty.setVisibility(View.GONE);
        mTvChooseProperty.setOnClickListener(this);

        mTvProvince.setOnClickListener(this);
        mTvCity.setOnClickListener(this);
        mTvArea.setOnClickListener(this);

        mLlUnderWarranty.setOnClickListener(this);
        mLlOutsideTheWarranty.setOnClickListener(this);
        mLlYes.setOnClickListener(this);
        mLlNo.setOnClickListener(this);

        mBtnRelease.setOnClickListener(this);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Extra = "N";
                        ExtraTime = "0";
                        ExtraFee = "0";
                        break;
                    case 1:
                        Extra = "Y";
                        ExtraTime = "12";
                        ExtraFee = "60";
                        break;
                    case 2:
                        Extra = "Y";
                        ExtraTime = "24";
                        ExtraFee = "40";
                        break;
                    case 3:
                        Extra = "Y";
                        ExtraTime = "48";
                        ExtraFee = "20";
                        break;
                }
                mTvExpedited.setText("加急费用￥" + ExtraFee);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Extra = "N";
                ExtraTime = "0";
                ExtraFee = "0";
            }
        });
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
            case R.id.ll_under_warranty:
                mCbUnderWarranty.setChecked(true);
                mCbOutsideTheWarranty.setChecked(false);
                Guarantee = "Y";
                break;
            case R.id.ll_outside_the_warranty:
                mCbUnderWarranty.setChecked(false);
                mCbOutsideTheWarranty.setChecked(true);
                Guarantee = "N";
                break;
            case R.id.ll_yes:
                mCbYes.setChecked(true);
                mCbNo.setChecked(false);
                AccessorySendState = "Y";
                break;
            case R.id.ll_no:
                mCbYes.setChecked(false);
                mCbNo.setChecked(true);
                AccessorySendState = "N";
                break;

            case R.id.btn_release:
                if (FBrandID == null) {
                    MyUtils.showToast(mActivity, "请选择品牌！");
                    return;
                }
                if (FCategoryID == null) {
                    MyUtils.showToast(mActivity, "请选择分类！");
                    return;
                }
                if (FProductTypeID == null) {
                    MyUtils.showToast(mActivity, "请选择型号！");
                    return;
                }
                /*if (FAccessoryID == null) {
                    MyUtils.showToast(mActivity, "请选择属性！");
                    return;
                }*/
                if (ProvinceCode == null) {
                    MyUtils.showToast(mActivity, "请选择省！");
                    return;
                }
                if (CityCode == null) {
                    MyUtils.showToast(mActivity, "请选择市！");
                    return;
                }
                if (AreaCode == null) {
                    MyUtils.showToast(mActivity, "请选择区！");
                    return;
                }
                DetailAddress = mEtDetail.getText().toString();
                Address=mTvPca.getText().toString()+DetailAddress;
                Name = mEtName.getText().toString();
                Phone = mEtPhone.getText().toString();
                RecycleOrderHour = mEtRecoveryTime.getText().toString();
                FaultDescription = mEtFaultDescription.getText().toString();
                if (DetailAddress == null || "".equals(DetailAddress)) {
                    MyUtils.showToast(mActivity, "请输入详细地址！");
                    return;
                }
                if (Name == null || "".equals(Name)) {
                    MyUtils.showToast(mActivity, "请输入客户姓名！");
                    return;
                }
                if (Phone == null || "".equals(Phone)) {
                    MyUtils.showToast(mActivity, "请输入客户手机！");
                    return;
                }
                if (!RegexUtils.isMobileExact(Phone)) {
                    MyUtils.showToast(mActivity, "手机号格式不正确！");
                    return;
                }
                if (Guarantee == null || "".equals(Guarantee)) {
                    MyUtils.showToast(mActivity, "请选择保修期内或保修期外！");
                    return;
                }
                if (RecycleOrderHour == null || "".equals(RecycleOrderHour)) {
                    MyUtils.showToast(mActivity, "请输入回收时间！");
                    return;
                }
                if (!(Integer.parseInt(RecycleOrderHour) >= 12 || Integer.parseInt(RecycleOrderHour) <= 48)) {
                    MyUtils.showToast(mActivity, "回收时间需大于等于12小于等于48！");
                    return;
                }
                if (AccessorySendState == null || "".equals(AccessorySendState)) {
                    MyUtils.showToast(mActivity, "请选择是否为已发配件！");
                    return;
                }
                if (FaultDescription == null || "".equals(FaultDescription)) {
                    MyUtils.showToast(mActivity, "请输入故障描述！");
                    return;
                }
                mPresenter.AddOrder("1","维修",userID, FBrandID,BrandName, FCategoryID,CategoryName, FProductTypeID ,ProductTypeName, ProvinceCode, CityCode, AreaCode, Address, Name, Phone, FaultDescription,OrderMoney,RecycleOrderHour, Guarantee, AccessorySendState, Extra, ExtraTime, ExtraFee);
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
                    FBrandID = ((Brand) list.get(position)).getFBrandID();
                    BrandName = ((Brand) list.get(position)).getFBrandName();
                    tv.setText(BrandName);
                    mTvChooseCategory.setText("");
                    mTvChooseType.setText("");
                    mTvChooseProperty.setText("");
                    FCategoryID = null;
                    FProductTypeID = null;
                    FAccessoryID = null;
                    CategoryName = null;
                    ProductTypeName = null;
                }
                if (list.get(position) instanceof Category) {
                    FCategoryID = ((Category) list.get(position)).getFCategoryID();
                    CategoryName = ((Category) list.get(position)).getFCategoryName();
                    tv.setText(CategoryName);
                    mTvChooseType.setText("");
                    mTvChooseProperty.setText("");
                    FProductTypeID = null;
                    FAccessoryID = null;
                    ProductTypeName = null;
                    OrderMoney=null;
                }
                if (list.get(position) instanceof ProductType) {
                    FProductTypeID = ((ProductType) list.get(position)).getFProductTypeID();
                    ProductTypeName = ((ProductType) list.get(position)).getFProductTypeName();
                    OrderMoney = ((ProductType) list.get(position)).getInitPrice();
                    tv.setText(ProductTypeName);
                    mTvChooseProperty.setText("");
                    FAccessoryID = null;
                }
                if (list.get(position) instanceof Accessory.Item1Bean) {
                    tv.setText(((Accessory.Item1Bean) list.get(position)).getAccessoryName());
                    FAccessoryID = ((Accessory.Item1Bean) list.get(position)).getFAccessoryID();
                }
                if (list.get(position) instanceof Province) {
                    ProvinceName = ((Province) list.get(position)).getName();
                    tv.setText(ProvinceName + ">");
                    ProvinceCode = ((Province) list.get(position)).getCode();
                    mTvCity.setText("");
                    mTvArea.setText("");
                    CityCode = null;
                    CityName = null;
                    mTvPca.setText(ProvinceName);
                }
                if (list.get(position) instanceof City) {
                    CityName = ((City) list.get(position)).getName();
                    tv.setText(CityName + ">");
                    CityCode = ((City) list.get(position)).getCode();
                    mTvArea.setText("");
                    AreaCode = null;
                    AreaName = null;
                    mTvPca.setText(ProvinceName + CityName);
                }
                if (list.get(position) instanceof Area) {
                    AreaName = ((Area) list.get(position)).getName();
                    tv.setText(AreaName + ">");
                    AreaCode = ((Area) list.get(position)).getCode();
                    mTvPca.setText(ProvinceName + CityName + AreaName);
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
                    MyUtils.showToast(mActivity, "获取市失败！");
                }

                break;
            case 401:
//                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }

    @Override
    public void GetArea(BaseResult<Data<List<Area>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<List<Area>> data = baseResult.getData();
                if (data.isItem1()) {
                    areaList = data.getItem2();
                    areaAdapter = new AreaAdapter(R.layout.category_item, areaList);
                    showPopWindow(mTvArea, areaAdapter, areaList);
                } else {
                    MyUtils.showToast(mActivity, "获取区失败！");
                }

                break;
            case 401:
//                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }

    @Override
    public void AddOrder(Data<String> baseResult) {
        if (baseResult.isItem1()){
            ToastUtils.showShort("发布成功！");
            finish();
        }else{
            ToastUtils.showShort("发布失败！");
        }
    }
}