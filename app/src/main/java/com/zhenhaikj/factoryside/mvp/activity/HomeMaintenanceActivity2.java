package com.zhenhaikj.factoryside.mvp.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baidu.aip.asrwakeup3.core.recog.MyRecognizer;
import com.baidu.aip.asrwakeup3.core.recog.listener.ChainRecogListener;
import com.baidu.aip.asrwakeup3.core.recog.listener.IRecogListener;
import com.baidu.aip.asrwakeup3.core.recog.listener.MessageStatusRecogListener;
import com.baidu.aip.asrwakeup3.uiasr.params.OnlineRecogParams;
import com.baidu.voicerecognition.android.ui.BaiduASRDigitalDialog;
import com.baidu.voicerecognition.android.ui.DigitalDialogInput;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.Config;
import com.zhenhaikj.factoryside.mvp.adapter.AccessoryAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.AreaAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.BrandChooseAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.CategoryAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.CategoryAdapter2;
import com.zhenhaikj.factoryside.mvp.adapter.ChooseCategoryAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.CityAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.DistrictAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.Pre_order_Add_Ac_Adapter;
import com.zhenhaikj.factoryside.mvp.adapter.ProdCategoryAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.ProdModelAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.ProdModelCommonAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.ProdSpecificationsAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.ProductTypeAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.ProvinceAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Accessory;
import com.zhenhaikj.factoryside.mvp.bean.Accessory2;
import com.zhenhaikj.factoryside.mvp.bean.AddBrandResult;
import com.zhenhaikj.factoryside.mvp.bean.AddOrderParams;
import com.zhenhaikj.factoryside.mvp.bean.AddOrderResult;
import com.zhenhaikj.factoryside.mvp.bean.AddProdModelResult;
import com.zhenhaikj.factoryside.mvp.bean.Address;
import com.zhenhaikj.factoryside.mvp.bean.Area;
import com.zhenhaikj.factoryside.mvp.bean.Brand;
import com.zhenhaikj.factoryside.mvp.bean.Category;
import com.zhenhaikj.factoryside.mvp.bean.CategoryData;
import com.zhenhaikj.factoryside.mvp.bean.City;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.District;
import com.zhenhaikj.factoryside.mvp.bean.FAccessory;
import com.zhenhaikj.factoryside.mvp.bean.GetCategory;
import com.zhenhaikj.factoryside.mvp.bean.GetFactoryProdResult;
import com.zhenhaikj.factoryside.mvp.bean.GetProdCategoryResult;
import com.zhenhaikj.factoryside.mvp.bean.GetProdModelResult;
import com.zhenhaikj.factoryside.mvp.bean.GetProdSpecificationsResult;
import com.zhenhaikj.factoryside.mvp.bean.GetSingleProdResult;
import com.zhenhaikj.factoryside.mvp.bean.ProductType;
import com.zhenhaikj.factoryside.mvp.bean.Province;
import com.zhenhaikj.factoryside.mvp.bean.Service;
import com.zhenhaikj.factoryside.mvp.contract.HomeMaintenanceContract;
import com.zhenhaikj.factoryside.mvp.model.HomeMaintenanceModel;
import com.zhenhaikj.factoryside.mvp.presenter.HomeMaintenancePresenter;
import com.zhenhaikj.factoryside.mvp.utils.MyUtils;
import com.zhenhaikj.factoryside.mvp.utils.SingleClick;
import com.zhenhaikj.factoryside.mvp.widget.AdderView;
import com.zhenhaikj.factoryside.mvp.widget.CommonDialog_Home;
import com.zhenhaikj.factoryside.mvp.widget.RecyclerViewDivider;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class HomeMaintenanceActivity2 extends BaseActivity<HomeMaintenancePresenter, HomeMaintenanceModel> implements View.OnClickListener, HomeMaintenanceContract.View {


    private static final String TAG = "HomeMaintenanceActivity2";
    @BindView(R.id.view)
    View mView;
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
    @BindView(R.id.tv_choose_type)
    TextView mTvChooseType;
    @BindView(R.id.ll_choose_type)
    LinearLayout mLlChooseType;
    @BindView(R.id.tv_brand)
    TextView mTvBrand;
    @BindView(R.id.tv_brand_name)
    TextView mTvBrandName;
    @BindView(R.id.tv_brand_number)
    TextView mTvBrandNumber;
    @BindView(R.id.ll_product)
    LinearLayout mLlProduct;
    //    @BindView(R.id.et_num)
//    EditText mEtNum;
    @BindView(R.id.tv_choose_property)
    TextView mTvChooseProperty;
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.iv_add_name)
    ImageView mIvAddName;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv_pca)
    TextView mTvPca;
    @BindView(R.id.et_detail)
    EditText mEtDetail;
    @BindView(R.id.iv_microphone)
    ImageView mIvMicrophone;
    @BindView(R.id.ll_microphone)
    LinearLayout mLlMicrophone;
    @BindView(R.id.cb_under_warranty)
    CheckBox mCbUnderWarranty;
    @BindView(R.id.ll_under_warranty)
    LinearLayout mLlUnderWarranty;
    @BindView(R.id.cb_outside_the_warranty)
    CheckBox mCbOutsideTheWarranty;
    @BindView(R.id.ll_outside_the_warranty)
    LinearLayout mLlOutsideTheWarranty;
    @BindView(R.id.et_recovery_time)
    EditText mEtRecoveryTime;
    @BindView(R.id.tv_add)
    TextView mTvAdd;
    @BindView(R.id.tv_delete)
    TextView mTvDelete;
    @BindView(R.id.cb_yes)
    CheckBox mCbYes;
    @BindView(R.id.ll_yes)
    LinearLayout mLlYes;
    @BindView(R.id.cb_no)
    CheckBox mCbNo;
    @BindView(R.id.ll_no)
    LinearLayout mLlNo;
    @BindView(R.id.spinner)
    AppCompatSpinner mSpinner;
    @BindView(R.id.tv_expedited)
    TextView mTvExpedited;
    @BindView(R.id.et_fault_description)
    EditText mEtFaultDescription;
    @BindView(R.id.btn_release)
    Button mBtnRelease;
    @BindView(R.id.tv_category_name)
    TextView mTvCategoryName;
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.ll_accessories)
    LinearLayout mLlAccessories;
    @BindView(R.id.cb_yes_signing)
    CheckBox mCbYesSigning;
    @BindView(R.id.ll_yes_signing)
    LinearLayout mLlYesSigning;
    @BindView(R.id.cb_no_signing)
    CheckBox mCbNoSigning;
    @BindView(R.id.ll_no_signing)
    LinearLayout mLlNoSigning;
    @BindView(R.id.et_expressno)
    EditText mEtExpressno;
    @BindView(R.id.ll_scan)
    LinearLayout mLlScan;
    @BindView(R.id.ll_number)
    LinearLayout mLlNumber;
    @BindView(R.id.ll_signing)
    LinearLayout mLlSigning;
    @BindView(R.id.view_sig)
    View mViewSig;
    @BindView(R.id.tv_choose_category)
    TextView mTvChooseCategory;
    @BindView(R.id.ll_choose_category)
    LinearLayout mLlChooseCategory;
    @BindView(R.id.tv_choose_brand)
    TextView mTvChooseBrand;
    @BindView(R.id.ll_choose_brand)
    LinearLayout mLlChooseBrand;
    @BindView(R.id.iv_n)
    ImageView mIvN;
    @BindView(R.id.ll_n)
    LinearLayout mLlN;
    @BindView(R.id.iv_y)
    ImageView mIvY;
    @BindView(R.id.ll_y)
    LinearLayout mLlY;
    @BindView(R.id.tv_addressback)
    TextView mTvAddressback;
    @BindView(R.id.tv_modify)
    TextView mTvModify;
    @BindView(R.id.iv_pay)
    ImageView mIvPay;
    @BindView(R.id.ll_pay)
    LinearLayout mLlPay;
    @BindView(R.id.iv_pay2)
    ImageView mIvPay2;
    @BindView(R.id.ll_pay2)
    LinearLayout mLlPay2;
    @BindView(R.id.tv_post_money)
    TextView mTvPostMoney;
    @BindView(R.id.ll_post_money)
    LinearLayout mLlPostMoney;
    @BindView(R.id.ll_address_info)
    LinearLayout mLlAddressInfo;
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;
    @BindView(R.id.ll_old_accessory)
    LinearLayout mLlOldAccessory;
    @BindView(R.id.tv_add_accessories)
    TextView mTvAddAccessories;
    @BindView(R.id.rv_accessories)
    RecyclerView mRvAccessories;
    @BindView(R.id.ll_accessory)
    LinearLayout mLlAccessory;
    @BindView(R.id.ll_accessories_new)
    LinearLayout mLlAccessoriesNew;
    @BindView(R.id.et_logistics)
    EditText mEtLogistics;
    @BindView(R.id.ll_scan_logistics)
    LinearLayout mLlScanLogistics;
    @BindView(R.id.ll_logistics)
    LinearLayout mLlLogistics;
    @BindView(R.id.addview)
    AdderView mAddview;
    @BindView(R.id.ll_quantity)
    LinearLayout mLlQuantity;
    @BindView(R.id.iv_microphone_one)
    ImageView mIvMicrophoneOne;
    @BindView(R.id.ll_microphone_one)
    LinearLayout mLlMicrophoneOne;
    @BindView(R.id.tv_description)
    TextView mTvDescription;
    @BindView(R.id.tv_choose_common_type)
    TextView mTvChooseCommonType;
    @BindView(R.id.ll_choose_common_type)
    LinearLayout mLlChooseCommonType;
    @BindView(R.id.tv_choose_gg)
    TextView mTvChooseGg;
    @BindView(R.id.ll_choose_gg)
    LinearLayout mLlChooseGg;
    @BindView(R.id.btn_addbrand)
    Button mBtnAddbrand;
    @BindView(R.id.add_prodmodel)
    Button mAddProdmodel;


    private PopupWindow popupWindow;
    private List<Province> provinceList;
    private List<City> cityList;
    private List<Area> areaList;
    private List<District> districtList;
    private List<Brand> brandList;
    private List<Category> categoryList;
    private List<ProductType> productTypeList;
    private List<Accessory.Item1Bean> accessoryList;
    private String userID;//用户id
    private String FBrandID;//品牌id
    private String FCategoryID;//分类id
    private String FCategoryName;
    private String FProductTypeID;//型号id
    private String FAccessoryID;//配件id
    private String ProvinceCode;//省code
    private String CityCode;//市code
    private String AreaCode;//区code
    private String DistrictCode;//街道code
    private String ProvinceName;
    private String CityName;
    private String AreaName;
    private String DistrictName;
    private String BrandName;
    private String CategoryName;
    private String SubCategoryID;
    private String SubCategoryName;
    private String ProductTypeName;
    private String OrderMoney = "0";
    private String Address;//详细地址
    private String DetailAddress;//详细地址
    private String Name;//客户姓名
    private String Phone;//客户手机
    private String FaultDescription;//故障描述
    private String RecycleOrderHour;//回收时间
    private String Guarantee;//保内Y保外N
    private String AccessorySendState;//是否已发配件 Y是N否
    private String SigningState;//是否已收产品 Y是N否
    private String Extra;//是否加急Y是N否
    private String ExtraTime;//加急时间
    private String ExtraFee;//加急费用
    private BrandChooseAdapter brandsAdapter;
    private CategoryAdapter categoryAdapter;
    private ProductTypeAdapter productTypeAdapter;
    private AccessoryAdapter accessoryAdapter;
    private ProvinceAdapter provinceAdapter;
    private CityAdapter cityAdapter;
    private AreaAdapter areaAdapter;
    private DistrictAdapter districtAdapter;
    private List<Category.DataBean> popularList;
    private List<Category.DataBean> chooseList;
    private CategoryAdapter popularAdapter;
    private CategoryAdapter chooseAdapter;
    private RecyclerView rv_choose;

    private RecyclerView lv_popular;
    private ImageView iv_close;

    private RecyclerView rv_address;
    private RecyclerView rv_address_choose;
    private TextView tv_province;
    private TextView tv_city;
    private TextView tv_area;
    private TextView tv_choose;
    private MyRecognizer myRecognizer;
    protected Handler handler;
    /**
     * 对话框界面的输入参数
     */
    private DigitalDialogInput input;
    private ChainRecogListener chainRecogListener;
    private boolean running;
    private OnlineRecogParams apiParams;
    private String Num;
    private TextView tv_district;
    String brand;
    private Category.DataBean category;
    ZLoadingDialog dialog = new ZLoadingDialog(this); //loading
    private int type;
    private String number;
    private String TypeID;
    private String TypeName;
    private ChooseCategoryAdapter firstAdapter;
    private LinearLayoutManager linearLayoutManager;
    private String PostPayType;
    private String IsReturn;
    private Address address;
    private String AddressBack;
    private List<Address> addressList;
    private List<FAccessory.OrderAccessoryStrBean.OrderAccessoryBean> fAcList = new ArrayList<>();// 用于存放预接单页面显示的数据
    private FAccessory.OrderAccessoryStrBean.OrderAccessoryBean mfAccessory;
    private Pre_order_Add_Ac_Adapter mPre_order_add_ac_adapter;
    private FAccessory.OrderAccessoryStrBean orderAccessoryStrBean;
    private Gson gson = new Gson();
    private Service service;
    private String s;
    private RequestBody body;
    private List<GetProdCategoryResult.DataBean> categoryNewList = new ArrayList<>();
    private CategoryAdapter2 chooseAdapter2;
    private String specificationsID;
    private List<GetProdSpecificationsResult.DataBean> prodSpecificationsResultList;
    private List<GetProdModelResult.DataBean> GetProdModelResultList;
    private List<GetFactoryProdResult.DataBean> GetFactoryProdResultList;
    private AddOrderParams params;
    private String ProdModel;
    private String cityStr;
    private String addStr;
    private String ProdModelID;
    private String backAddrID;
    private View addview;
    private EditText et_brandName;
    private Button btn_next;
    private String brandName;
    private AlertDialog alertDialog;
    private TextView tv_title;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_home_maintenance2;
    }

    @Override
    protected void initData() {
        initPermission();
        SPUtils spUtils = SPUtils.getInstance("token");
        userID = spUtils.getString("userName");
        mPresenter.GetAccountAddress(userID);
//        mPresenter.GetFactoryBrand(userID);

        IRecogListener listener = new MessageStatusRecogListener(handler);
        // DEMO集成步骤 1.1 1.3 初始化：new一个IRecogListener示例 & new 一个 MyRecognizer 示例,并注册输出事件
        if (myRecognizer == null) {
            myRecognizer = new MyRecognizer(mActivity, listener);
        }
        /**
         * 有2个listner，一个是用户自己的业务逻辑，如MessageStatusRecogListener。另一个是UI对话框的。
         * 使用这个ChainRecogListener把两个listener和并在一起
         */
        chainRecogListener = new ChainRecogListener();
        // DigitalDialogInput 输入 ，MessageStatusRecogListener可替换为用户自己业务逻辑的listener
        chainRecogListener.addListener(new MessageStatusRecogListener(handler));
        myRecognizer.setEventListener(chainRecogListener); // 替换掉原来的listener
    }

    @Override
    protected void initView() {
        type = getIntent().getIntExtra("type", -1);
        mTvTitle.setVisibility(View.VISIBLE);

        switch (type) {
            case 0:
                mTvTitle.setText("上门安装");
                mTvDescription.setText("安装说明");
                mEtFaultDescription.setHint("请填写安装说明");
                break;
            case 1:
                mTvTitle.setText("上门维修");
                mLlQuantity.setVisibility(View.GONE);
                break;
            default:
                break;
        }
//        mEtNum.setText("1");

        //默认保内
//        mCbUnderWarranty.setChecked(true);
//        mCbOutsideTheWarranty.setChecked(false);
//        Guarantee = "Y";
        //已发配件默认否
//        mCbYes.setChecked(false);
//        mCbNo.setChecked(true);
//        AccessorySendState = "N";
//        mPresenter.GetFactoryBrand(userID);

        mIvY.setSelected(true);
        IsReturn = "1";

        switch (type) {
            case 0:
                mLlAccessories.setVisibility(View.GONE);
                mLlSigning.setVisibility(View.GONE);
                break;
            case 1:
                mLlAccessories.setVisibility(View.VISIBLE);
                mLlSigning.setVisibility(View.GONE);
                break;

        }

        mPre_order_add_ac_adapter = new Pre_order_Add_Ac_Adapter(R.layout.item_pre_order_add_accessories, fAcList, "0");
        mRvAccessories.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvAccessories.setAdapter(mPre_order_add_ac_adapter);
        mPre_order_add_ac_adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_accessories_delete:
                        mPre_order_add_ac_adapter.remove(position);
//                        if (mPre_order_add_ac_adapter.getData().size() > 0) {
//                            mTvSubmitAddAccessories.setBackgroundResource(R.drawable.ed_order_detail_submit);
//                            mTvSubmitAddAccessories.setTextColor(Color.WHITE);
//                        } else {
//                            mTvSubmitAddAccessories.setBackgroundResource(R.drawable.tv_order_detail_btn);
//                            mTvSubmitAddAccessories.setTextColor(Color.parseColor("#6a6a6a"));
//                        }
                        break;
                    default:
                        break;
                }
            }
        });


    }

    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarView(mView);
        mImmersionBar.keyboardEnable(true);
        mImmersionBar.init();
    }

    @Override
    protected void setListener() {
        mBtnAddbrand.setOnClickListener(this);
        mAddProdmodel.setOnClickListener(this);
        mLlMicrophone.setOnClickListener(this);
        mLlMicrophone.setOnClickListener(this);

        mIconBack.setOnClickListener(this);
        mIconSearch.setOnClickListener(this);
        mTvAddProduct.setOnClickListener(this);
        mLlChooseCategory.setOnClickListener(this);
        mLlChooseBrand.setOnClickListener(this);
        mLlChooseType.setOnClickListener(this);

        mLlChooseCommonType.setOnClickListener(this);
        mLlChooseGg.setOnClickListener(this);

        mTvAddress.setOnClickListener(this);


        mLlUnderWarranty.setOnClickListener(this);
        mLlOutsideTheWarranty.setOnClickListener(this);
        mLlYes.setOnClickListener(this);
        mLlNo.setOnClickListener(this);
        mLlYesSigning.setOnClickListener(this);
        mLlNoSigning.setOnClickListener(this);
        mLlScan.setOnClickListener(this);
        mLlN.setOnClickListener(this);
        mLlY.setOnClickListener(this);
        mLlPay.setOnClickListener(this);
        mLlPay2.setOnClickListener(this);
        mTvModify.setOnClickListener(this);
        mTvAddAccessories.setOnClickListener(this);
        mLlScanLogistics.setOnClickListener(this);
        mLlMicrophoneOne.setOnClickListener(this);

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
                mTvExpedited.setText("加急费用¥" + ExtraFee);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Extra = "N";
                ExtraTime = "0";
                ExtraFee = "0";
            }
        });
//        mEtNum.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                String text = s.toString();
//                int len = s.toString().length();
//                if (len > 1 && text.startsWith("0")) {
//                    Num = s.replace(0, 1, "").toString();
//                } else {
//                    Num = s.toString();
//                }
////                mTvActualArrival.setText(value);
//            }
//        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void GetProdModel(GetProdModelResult baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                GetProdModelResultList = baseResult.getData();
                if (GetProdModelResultList.size() == 0) {
                    ToastUtils.showShort("无型号，请联系管理员！");
                } else {
                    showPopWindowGetProdModelResult(mTvChooseType, GetProdModelResultList);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void AddBrand(AddBrandResult baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isStatus()){
                    ToastUtils.showShort("添加成功");
                    alertDialog.dismiss();
                }else{
                    ToastUtils.showShort("添加失败");
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void AddProdModel(AddProdModelResult baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isStatus()){
                    ToastUtils.showShort("添加成功");
                    alertDialog.dismiss();
                }else{
                    ToastUtils.showShort("添加失败");
                }
                break;
            default:
                break;
        }
    }

    @SingleClick
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_addbrand:
                addview = LayoutInflater.from(mActivity).inflate(R.layout.dialog_brand_name, null);
                et_brandName = addview.findViewById(R.id.et_enter);
                tv_title = addview.findViewById(R.id.tv_title);
                tv_title.setText("添加品牌");
                et_brandName.setHint("请输入品牌名称");
                btn_next = addview.findViewById(R.id.btn_next);
                btn_next.setText("添加");
                btn_next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        brandName = et_brandName.getText().toString();
                        if ("".equals(brandName) || brandName == null) {
                            MyUtils.showToast(mActivity, "请输入品牌名称！");
                            return;
                        }
                        mPresenter.AddBrand(brandName);
                    }
                });
                alertDialog = new AlertDialog.Builder(mActivity).setView(addview).create();
                alertDialog.show();
                break;
            case R.id.add_prodmodel:
                if (specificationsID == null) {
                    MyUtils.showToast(mActivity, "请先选择规格！");
                    return;
                }
                addview = LayoutInflater.from(mActivity).inflate(R.layout.dialog_brand_name, null);
                et_brandName = addview.findViewById(R.id.et_enter);
                tv_title = addview.findViewById(R.id.tv_title);
                tv_title.setText("添加型号");
                et_brandName.setHint("请输入型号名称");
                btn_next = addview.findViewById(R.id.btn_next);
                btn_next.setText("添加");
                btn_next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        brandName = et_brandName.getText().toString();
                        if ("".equals(brandName) || brandName == null) {
                            MyUtils.showToast(mActivity, "请输入型号名称！");
                            return;
                        }
                        mPresenter.AddProdModel(specificationsID, brandName);
                    }
                });
                alertDialog = new AlertDialog.Builder(mActivity).setView(addview).create();
                alertDialog.show();
                break;
            case R.id.icon_back:
                finish();
                break;
            case R.id.tv_add_product:
                startActivity(new Intent(mActivity, ModelActivity.class));
                break;
            case R.id.ll_choose_gg://选择规格
                if (SubCategoryID == null) {
                    ToastUtils.showShort("请先选择分类");
                    return;
                }
                mPresenter.GetProdSpecifications(SubCategoryID);
                break;
            case R.id.ll_choose_brand://选择品牌
                mPresenter.GetFactoryBrand(userID);
                break;
            case R.id.ll_choose_common_type://选择常用型号
                mPresenter.GetFactoryProd();
                break;
            case R.id.ll_choose_category://选择分类
                mPresenter.GetProdCategory("", "");
                break;
            case R.id.ll_choose_type://选择型号
                if (specificationsID == null) {
                    ToastUtils.showShort("请先选择规格");
                    return;
                }
                mPresenter.GetProdModel(specificationsID);
                break;
            case R.id.tv_address:
                mPresenter.GetProvince();
                break;
            case R.id.tv_province:
                mPresenter.GetProvince();
                break;
            case R.id.tv_city:
                mPresenter.GetCity(ProvinceCode);
                break;
            case R.id.tv_area:
                mPresenter.GetArea(CityCode);
                break;
            case R.id.ll_under_warranty:
                mCbUnderWarranty.setChecked(true);
                mCbOutsideTheWarranty.setChecked(false);
                Guarantee = "1";
                break;
            case R.id.ll_outside_the_warranty:
                mCbUnderWarranty.setChecked(false);
                mCbOutsideTheWarranty.setChecked(true);
                Guarantee = "2";
                break;
            case R.id.ll_yes:
                mCbYes.setChecked(true);
                mCbNo.setChecked(false);
                AccessorySendState = "Y";
                mLlAccessoriesNew.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_no:
                mCbYes.setChecked(false);
                mCbNo.setChecked(true);
                AccessorySendState = "N";
                mLlAccessoriesNew.setVisibility(View.GONE);
                break;
            case R.id.ll_y:
                mIvY.setSelected(true);
                mIvN.setSelected(false);
                mLlAddressInfo.setVisibility(View.VISIBLE);
                IsReturn = "1";
                break;
            case R.id.ll_n:
                mIvY.setSelected(false);
                mIvN.setSelected(true);
                mLlAddressInfo.setVisibility(View.GONE);
                IsReturn = "2";
                break;
            case R.id.ll_pay:
                mIvPay.setSelected(true);
                mIvPay2.setSelected(false);
                PostPayType = "1";
                break;
            case R.id.ll_pay2:
                mIvPay.setSelected(false);
                mIvPay2.setSelected(true);
                PostPayType = "2";
                break;
            case R.id.ll_yes_signing:
                mCbYesSigning.setChecked(true);
                mCbNoSigning.setChecked(false);
                SigningState = "Y";
                mViewSig.setVisibility(View.GONE);
                mLlNumber.setVisibility(View.GONE);
                break;
            case R.id.ll_no_signing:
                mCbYesSigning.setChecked(false);
                mCbNoSigning.setChecked(true);
                SigningState = "N";
                mViewSig.setVisibility(View.VISIBLE);
                mLlNumber.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_scan:
                IntentIntegrator integrator = new IntentIntegrator(HomeMaintenanceActivity2.this);
                // 设置要扫描的条码类型，ONE_D_CODE_TYPES：一维码，QR_CODE_TYPES-二维码
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
                integrator.setCaptureActivity(ScanActivity.class); //设置打开摄像头的Activity
                integrator.setPrompt("请扫描快递码"); //底部的提示文字，设为""可以置空
                integrator.setCameraId(0); //前置或者后置摄像头
                integrator.setBeepEnabled(true); //扫描成功的「哔哔」声，默认开启
                integrator.setBarcodeImageEnabled(true);
                integrator.initiateScan();
                break;
            case R.id.ll_scan_logistics:
                IntentIntegrator integrator1 = new IntentIntegrator(HomeMaintenanceActivity2.this);
                // 设置要扫描的条码类型，ONE_D_CODE_TYPES：一维码，QR_CODE_TYPES-二维码
                integrator1.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
                integrator1.setCaptureActivity(ScanActivity.class); //设置打开摄像头的Activity
                integrator1.setPrompt("请扫描快递码"); //底部的提示文字，设为""可以置空
                integrator1.setCameraId(0); //前置或者后置摄像头
                integrator1.setBeepEnabled(true); //扫描成功的「哔哔」声，默认开启
                integrator1.setBarcodeImageEnabled(true);
                integrator1.initiateScan();
                break;
            case R.id.tv_modify:
                Intent intent1 = new Intent(mActivity, ShippingAddressActivity.class);
                intent1.putExtra("type", "0");
                startActivityForResult(intent1, 1000);
                break;
            case R.id.tv_add_accessories:
                if (specificationsID == null) {
                    ToastUtils.showShort("请先选择规格");
                } else if ("".equals(mEtLogistics.getText().toString())) {
                    ToastUtils.showShort("请填写物流信息");
                } else {
                    Intent intent2 = new Intent(mActivity, NewAddAccessoriesActivity.class);
                    intent2.putExtra("SubCategoryID", specificationsID);
                    startActivityForResult(intent2, Config.APPLY_REQUEST);
                }

                break;
            case R.id.btn_release:
                addorder("0");
                break;

            case R.id.ll_microphone:

                // 此处params可以打印出来，直接写到你的代码里去，最终的json一致即可。
                final Map<String, Object> params = fetchParams();

                // BaiduASRDigitalDialog的输入参数
                input = new DigitalDialogInput(myRecognizer, chainRecogListener, params);
                BaiduASRDigitalDialog.setInput(input); // 传递input信息，在BaiduASRDialog中读取,
                Intent intent = new Intent(this, BaiduASRDigitalDialog.class);

                // 修改对话框样式
                // intent.putExtra(BaiduASRDigitalDialog.PARAM_DIALOG_THEME, BaiduASRDigitalDialog.THEME_ORANGE_DEEPBG);

                running = true;
                startActivityForResult(intent, 2);
//                startActivity(new Intent(mActivity,ActivityUiDialog.class));
                break;
            case R.id.ll_microphone_one:
                final Map<String, Object> params1 = fetchParams();

                // BaiduASRDigitalDialog的输入参数
                input = new DigitalDialogInput(myRecognizer, chainRecogListener, params1);
                BaiduASRDigitalDialog.setInput(input); // 传递input信息，在BaiduASRDialog中读取,
                Intent intent2 = new Intent(this, BaiduASRDigitalDialog.class);

                // 修改对话框样式
                // intent.putExtra(BaiduASRDigitalDialog.PARAM_DIALOG_THEME, BaiduASRDigitalDialog.THEME_ORANGE_DEEPBG);

                running = true;
                startActivityForResult(intent2, 3);
                break;
        }
    }

    public void addorder(String ContinueIssuing) {
        showLoading();
        if (SubCategoryID == null) {
            ToastUtils.showShort("请选择分类！");
            cancleLoading();
            return;
        }
        if (specificationsID == null) {
            ToastUtils.showShort("请选择规格！");
            cancleLoading();
            return;
        }
        if (FBrandID == null) {
            ToastUtils.showShort("请选择品牌！");
            cancleLoading();
            return;
        }
        if (ProdModelID == null) {
            ToastUtils.showShort("请选择型号！");
            cancleLoading();
            return;
        }
        addStr = mEtDetail.getText().toString();
        cityStr = mTvPca.getText().toString();
        Name = mEtName.getText().toString();
        Phone = mEtPhone.getText().toString();
        RecycleOrderHour = mEtRecoveryTime.getText().toString();
        FaultDescription = mEtFaultDescription.getText().toString();

        if (Name == null || "".equals(Name)) {
            MyUtils.showToast(mActivity, "请输入客户姓名！");
            cancleLoading();
            return;
        }
        if (Phone == null || "".equals(Phone)) {
            MyUtils.showToast(mActivity, "请输入客户手机！");
            cancleLoading();
            return;
        }
        if (!RegexUtils.isMobileExact(Phone)) {
            MyUtils.showToast(mActivity, "手机号格式不正确！");
            cancleLoading();
            return;
        }
        if (cityStr == null || "".equals(cityStr)) {
            MyUtils.showToast(mActivity, "请选择所在地区！");
            cancleLoading();
            return;
        }
        if (addStr == null || "".equals(addStr)) {
            MyUtils.showToast(mActivity, "请输入详细地址！");
            cancleLoading();
            return;
        }
        if (Guarantee == null || "".equals(Guarantee)) {
            MyUtils.showToast(mActivity, "请选择保修期内或保修期外！");
            cancleLoading();
            return;
        }
        if (FaultDescription == null || "".equals(FaultDescription)) {
            switch (type) {
                case 0:
                    MyUtils.showToast(mActivity, "请输入备注！");
                    break;
                case 1:
                    MyUtils.showToast(mActivity, "请输入故障描述！");
                    break;
                default:
                    break;
            }

            cancleLoading();
            return;
        }
        switch (type) {
            case 0:
//                if (SigningState == null || "".equals(SigningState)) {
//                    MyUtils.showToast(mActivity, "请选择客户是否为已签收产品");
//                    cancleLoading();
//                    return;
//                }
//                number = mEtExpressno.getText().toString();
//                if (mCbNoSigning.isChecked()) {
//                    if ("".equals(number)) {
//                        MyUtils.showToast(mActivity, "请填写快递单号");
//                        cancleLoading();
//                        return;
//                    }
//                }
                Num = String.valueOf(mAddview.getValue());
                if (Num == null) {
                    MyUtils.showToast(mActivity, "请输入数量！");
                    cancleLoading();
                    return;
                }
                if ("".equals(Num)) {
                    MyUtils.showToast(mActivity, "请输入数量！");
                    cancleLoading();
                    return;
                }
                params = new AddOrderParams();

                params.setSubCategoryID(SubCategoryID);//分类
                params.setSpecifications(specificationsID);//规格
                params.setFactoryBrandName(FBrandID);//品牌名
                params.setProdModel(ProdModelID);//型号
                params.setPhone(Phone);//手机号
                params.setName(Name);//用户姓名
                params.setCity(cityStr);//省市区街道
                params.setAddstr(addStr);//详细地址

                params.setServicetype("2");//2安装单 1维修单
                params.setGuaranteetype(Guarantee);//1保内 2保外

                params.setNum(Num);//安装单数量
                params.setExpressNo(number);//快递单号
                params.setPartsVal(null);
                params.setParts("N");
                params.setPostpaytype("");
                params.setBak(FaultDescription);//服务要求
                params.setBackAddress("");//返件地址
                params.setBackParts("");//是否返件
                params.setContinueIssuing(ContinueIssuing);

                s = gson.toJson(params);
                body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s);
                mPresenter.AddOrder(body);
                break;
            case 1:
                number = mEtLogistics.getText().toString();
                if (AccessorySendState == null || "".equals(AccessorySendState)) {
                    MyUtils.showToast(mActivity, "请选择是否为已发配件！");
                    cancleLoading();
                    return;
                } else if ("Y".equals(AccessorySendState)) {
                    if ("".equals(IsReturn) || IsReturn == null) {
                        MyUtils.showToast(mActivity, "请选择是否需要旧件返厂！");
                        cancleLoading();
                        return;
                    } else if ("1".equals(IsReturn)) {
                        if ("".equals(mTvAddressback.getText().toString())) {
                            MyUtils.showToast(mActivity, "请选择寄件地址！");
                            cancleLoading();
                            return;
                        }
                        if ("".equals(PostPayType) || PostPayType == null) {
                            MyUtils.showToast(mActivity, "请选择邮费支付方式！");
                            cancleLoading();
                            return;
                        }

                    }

                    if (fAcList.size() == 0) {
                        MyUtils.showToast(mActivity, "请添加配件！");
                        cancleLoading();
                        return;
                    }

                    if ("".equals(number)) {
                        MyUtils.showToast(mActivity, "请输入快递单号！");
                        cancleLoading();
                        return;
                    }

                    params = new AddOrderParams();

                    params.setSubCategoryID(SubCategoryID);//分类
                    params.setSpecifications(specificationsID);//规格
                    params.setFactoryBrandName(FBrandID);//品牌名
                    params.setProdModel(ProdModelID);//型号
                    params.setPhone(Phone);//手机号
                    params.setName(Name);//用户姓名
                    params.setCity(cityStr);//省市区街道
                    params.setAddstr(addStr);//详细地址

                    params.setServicetype("1");//2安装单 1维修单
                    params.setGuaranteetype(Guarantee);//1保内 2保外

                    params.setNum(Num);//安装单数量
                    params.setExpressNo(number);//快递单号
                    List<Acc> list = new ArrayList<>();
                    for (int i = 0; i < fAcList.size(); i++) {
                        list.add(new Acc(fAcList.get(i).getFAccessoryName(), fAcList.get(i).getFAccessoryID()));
                    }

                    params.setPartsVal(list);
                    params.setParts("Y");
                    params.setPostpaytype(PostPayType);
                    params.setBak(FaultDescription);//服务要求
                    params.setBackAddress(backAddrID);//返件地址
                    params.setBackParts(IsReturn);//是否返件
                    params.setContinueIssuing(ContinueIssuing);

                    s = gson.toJson(params);
                    body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s);
                    mPresenter.AddOrder(body);

                } else {
                    params = new AddOrderParams();

                    params.setSubCategoryID(SubCategoryID);//分类
                    params.setSpecifications(specificationsID);//规格
                    params.setFactoryBrandName(FBrandID);//品牌名
                    params.setProdModel(ProdModelID);//型号
                    params.setPhone(Phone);//手机号
                    params.setName(Name);//用户姓名
                    params.setCity(cityStr);//省市区街道
                    params.setAddstr(addStr);//详细地址

                    params.setServicetype("1");//2安装单 1维修单
                    params.setGuaranteetype(Guarantee);//1保内 2保外

                    params.setNum(Num);//安装单数量
                    params.setExpressNo(number);//快递单号
                    params.setPartsVal(null);
                    params.setParts("N");
                    params.setPostpaytype("");
                    params.setBak(FaultDescription);//服务要求
                    params.setBackAddress("");//返件地址
                    params.setBackParts("");//是否返件
                    params.setContinueIssuing(ContinueIssuing);

                    s = gson.toJson(params);
                    body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s);
                    mPresenter.AddOrder(body);
                }
                break;
            default:
                break;
        }
    }

    protected Map<String, Object> fetchParams() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(mActivity);
        //  上面的获取是为了生成下面的Map， 自己集成时可以忽略
        apiParams = new OnlineRecogParams();
        Map<String, Object> params = apiParams.fetch(sp);
        //  集成时不需要上面的代码，只需要params参数。
        return params;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (scanResult != null) {
            String result = scanResult.getContents();
            if (result == null) {
                return;
            } else {
                mEtExpressno.setText(result);
                mEtLogistics.setText(result);
            }

        }

        running = false;
        if (requestCode == 2) {
            String message = "";
            if (resultCode == RESULT_OK) {
                ArrayList results = data.getStringArrayListExtra("results");
                if (results != null && results.size() > 0) {
                    message += results.get(0);
                }
            } else {
                message += "";
            }
            mEtDetail.setText(message);
        }
        if (requestCode == 3) {
            String message = "";
            if (resultCode == RESULT_OK) {
                ArrayList results = data.getStringArrayListExtra("results");
                if (results != null && results.size() > 0) {
                    message += results.get(0);
                }
            } else {
                message += "";
            }
            mEtFaultDescription.setText(message);
        }
        if (requestCode == 100) {
            if (data != null) {
                category = (Category.DataBean) data.getSerializableExtra("type");
                if (category != null) {
                    mLlProduct.setVisibility(View.VISIBLE);
                    mTvBrand.setText(category.getBrandName());
                    mTvBrandNumber.setText(category.getFCategoryName());
                    mTvCategoryName.setText(category.getParentName());
                    mTvPrice.setText("¥" + category.getInitPrice());
                } else {
                    mLlProduct.setVisibility(View.GONE);
                }
            }
        }

        if (requestCode == 1000) {
            if (data != null) {
                address = (Address) data.getSerializableExtra("address");
                if (address != null) {
                    AddressBack = address.getProvince() + address.getCity() + address.getArea() + address.getDistrict() + address.getAddress() + "(" + address.getUserName() + "收)" + address.getPhone();
                    mTvAddressback.setText(AddressBack);
                }
            }
        }


        /*获取返回的配件*/
        if (requestCode == Config.APPLY_REQUEST) {
            if (resultCode == Config.APPLY_RESULT) {
                ArrayList<Accessory2> list = (ArrayList<Accessory2>) data.getSerializableExtra("list_collect");
                fAcList.clear();
                String expressno = mEtLogistics.getText().toString();
                /*商城*/
                for (int i = 0; i < list.size(); i++) {
                    mfAccessory = new FAccessory.OrderAccessoryStrBean.OrderAccessoryBean();
                    mfAccessory.setFAccessoryID(list.get(i).getFAccessoryID());//获取id
                    mfAccessory.setFAccessoryName(list.get(i).getAccessoryName()); //获取名字
                    mfAccessory.setFCategoryID(list.get(i).getFCategoryID() + ""); //分类id
                    mfAccessory.setQuantity(list.get(i).getCount() + ""); //数量 默认数字为1
                    mfAccessory.setPrice(Double.valueOf("0"));//原价
                    mfAccessory.setDiscountPrice(Double.valueOf("0"));//折扣价
//                    mfAccessory.setSizeID("1");//小修中修大修
                    mfAccessory.setSendState("Y");
                    mfAccessory.setRelation("");
                    mfAccessory.setState("1");
                    mfAccessory.setIsPay("Y");
                    mfAccessory.setExpressNo(expressno);
                    mfAccessory.setNeedPlatformAuth("N");
//                   if (select_state == 0) {//厂家自购
//                       mfAccessory.setPrice(list.get(i).getAccessoryPrice());//原价
//                       mfAccessory.setDiscountPrice(list.get(i).getAccessoryPrice());//原价
//                   }
                    fAcList.add(mfAccessory);
                }
                mPre_order_add_ac_adapter.notifyDataSetChanged();
//               if (mPre_order_add_ac_adapter.getData().size() > 0) {
//                   mTvSubmitAddAccessories.setBackgroundResource(R.drawable.ed_order_detail_submit);
//                   mTvSubmitAddAccessories.setTextColor(Color.WHITE);
//               } else {
//                   mTvSubmitAddAccessories.setBackgroundResource(R.drawable.tv_order_detail_btn);
//                   mTvSubmitAddAccessories.setTextColor(Color.parseColor("#6a6a6a"));
//               }
            }
        }

    }


    public void showPopWindowGetAddress(final TextView tv) {

        View contentView = LayoutInflater.from(mActivity).inflate(R.layout.address_pop, null);
        InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEtFaultDescription.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(mEtLogistics.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(mEtName.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(mEtPhone.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(mEtExpressno.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(mEtDetail.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(mEtRecoveryTime.getWindowToken(), 0);
        tv_province = contentView.findViewById(R.id.tv_province);
        tv_city = contentView.findViewById(R.id.tv_city);
        tv_area = contentView.findViewById(R.id.tv_area);
        tv_district = contentView.findViewById(R.id.tv_district);
        tv_city.setOnClickListener(this);
        tv_province.setOnClickListener(this);
        tv_area.setOnClickListener(this);
        tv_choose = contentView.findViewById(R.id.tv_choose);
        tv_choose.setText("选择省份/地区");
        rv_address_choose = contentView.findViewById(R.id.rv_address_choose);
        iv_close = contentView.findViewById(R.id.iv_close);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        rv_address_choose.setLayoutManager(new LinearLayoutManager(mActivity));
        rv_address_choose.setAdapter(provinceAdapter);
        provinceAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ProvinceName = provinceList.get(position).getName();
                ProvinceCode = provinceList.get(position).getCode();
                mPresenter.GetCity(ProvinceCode);
                tv_province.setText(ProvinceName);
                tv_province.setVisibility(View.VISIBLE);
                tv_city.setVisibility(View.VISIBLE);
            }
        });
        popupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, ScreenUtils.getScreenHeight() - 700);
//        popupWindow.setWidth(tv.getWidth());
        popupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
//                MyUtils.backgroundAlpha(mActivity,1);
                MyUtils.setWindowAlpa(mActivity, false);
            }
        });
        if (popupWindow != null && !popupWindow.isShowing()) {
//            popupWindow.showAsDropDown(tv, 0, 10);
            popupWindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);
//            MyUtils.backgroundAlpha(mActivity,0.5f);
        }
        MyUtils.setWindowAlpa(mActivity, true);
    }

    //分类弹框
    public void showPopWindowGetCategory(final TextView tv, List<GetProdCategoryResult.DataBean> categoryNewList) {
        View contentView = LayoutInflater.from(mActivity).inflate(R.layout.category_pop2, null);
        final RecyclerView rv = contentView.findViewById(R.id.rv);
        TextView tv_add = contentView.findViewById(R.id.tv_add);
        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity, ModelActivity.class));
            }
        });
        ProdCategoryAdapter firstAdapter = new ProdCategoryAdapter(R.layout.item_category, categoryNewList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        rv.setLayoutManager(linearLayoutManager);
        rv.addItemDecoration(new RecyclerViewDivider(mActivity, LinearLayoutManager.HORIZONTAL));
        rv.setAdapter(firstAdapter);

        firstAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SubCategoryID = categoryNewList.get(position).getSpecificationsID() + "";
                specificationsID=null;
                ProdModelID=null;
                mTvChooseGg.setText("");
                mTvChooseType.setText("");
                tv.setText(categoryNewList.get(position).getFCategoryName());
                popupWindow.dismiss();
                mPresenter.GetProdSpecifications(SubCategoryID);
            }
        });

        popupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        popupWindow.setWidth(tv.getWidth());
        popupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                MyUtils.setWindowAlpa(mActivity, false);
            }
        });
        if (popupWindow != null && !popupWindow.isShowing()) {
//            popupWindow.showAsDropDown(tv, 0, 10);
            popupWindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);
        }
        MyUtils.setWindowAlpa(mActivity, true);
    }

    //规格弹框
    public void showPopWindowGetProdSpecificationsResult(final TextView tv, List<GetProdSpecificationsResult.DataBean> categoryNewList) {
        View contentView = LayoutInflater.from(mActivity).inflate(R.layout.category_pop2, null);
        final RecyclerView rv = contentView.findViewById(R.id.rv);
        TextView tv_add = contentView.findViewById(R.id.tv_add);
        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity, ModelActivity.class));
            }
        });
        ProdSpecificationsAdapter firstAdapter = new ProdSpecificationsAdapter(R.layout.item_category, categoryNewList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        rv.setLayoutManager(linearLayoutManager);
        rv.addItemDecoration(new RecyclerViewDivider(mActivity, LinearLayoutManager.HORIZONTAL));
        rv.setAdapter(firstAdapter);

        firstAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                specificationsID = categoryNewList.get(position).getSpecificationsID() + "";
                ProdModelID=null;
                mTvChooseType.setText("");
                tv.setText(categoryNewList.get(position).getFCategoryName());
                popupWindow.dismiss();
                mPresenter.GetFactoryBrand(userID);

            }
        });

        popupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        popupWindow.setWidth(tv.getWidth());
        popupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                MyUtils.setWindowAlpa(mActivity, false);
            }
        });
        if (popupWindow != null && !popupWindow.isShowing()) {
//            popupWindow.showAsDropDown(tv, 0, 10);
            popupWindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);
        }
        MyUtils.setWindowAlpa(mActivity, true);
    }

    //型号弹框
    public void showPopWindowGetProdModelResult(final TextView tv, List<GetProdModelResult.DataBean> categoryNewList) {
        View contentView = LayoutInflater.from(mActivity).inflate(R.layout.category_pop2, null);
        final RecyclerView rv = contentView.findViewById(R.id.rv);
        TextView tv_add = contentView.findViewById(R.id.tv_add);
        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity, ModelActivity.class));
            }
        });
        ProdModelAdapter firstAdapter = new ProdModelAdapter(R.layout.item_category, categoryNewList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        rv.setLayoutManager(linearLayoutManager);
        rv.addItemDecoration(new RecyclerViewDivider(mActivity, LinearLayoutManager.HORIZONTAL));
        rv.setAdapter(firstAdapter);

        firstAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ProdModelID = categoryNewList.get(position).getID() + "";
                ProdModel = categoryNewList.get(position).getModelName();
                tv.setText(ProdModel);
                popupWindow.dismiss();
            }
        });

        popupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        popupWindow.setWidth(tv.getWidth());
        popupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                MyUtils.setWindowAlpa(mActivity, false);
            }
        });
        if (popupWindow != null && !popupWindow.isShowing()) {
//            popupWindow.showAsDropDown(tv, 0, 10);
            popupWindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);
        }
        MyUtils.setWindowAlpa(mActivity, true);
    }

    //常用型号弹框
    public void showPopWindowGetFactoryProdResult(final TextView tv, List<GetFactoryProdResult.DataBean> categoryNewList) {
        View contentView = LayoutInflater.from(mActivity).inflate(R.layout.category_pop2, null);
        final RecyclerView rv = contentView.findViewById(R.id.rv);
        TextView tv_add = contentView.findViewById(R.id.tv_add);
        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity, ModelActivity.class));
            }
        });
        ProdModelCommonAdapter firstAdapter = new ProdModelCommonAdapter(R.layout.item_category, categoryNewList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        rv.setLayoutManager(linearLayoutManager);
        rv.addItemDecoration(new RecyclerViewDivider(mActivity, LinearLayoutManager.HORIZONTAL));
        rv.setAdapter(firstAdapter);

        firstAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SubCategoryID = categoryNewList.get(position).getSubCategoryID() + "";
                SubCategoryName = categoryNewList.get(position).getSubCategoryName();
                specificationsID = categoryNewList.get(position).getProductTypeID() + "";
                ProductTypeName = categoryNewList.get(position).getProductTypeName();
                FBrandID = categoryNewList.get(position).getBrandID() + "";
                BrandName = categoryNewList.get(position).getBrandName();
                ProdModelID = categoryNewList.get(position).getProdModelID() + "";
                ProdModel = categoryNewList.get(position).getProdModel();
                mTvChooseCategory.setText(SubCategoryName);
                mTvChooseGg.setText(ProductTypeName);
                mTvChooseBrand.setText(BrandName);
                mTvChooseType.setText(ProdModel);
                tv.setText(BrandName + "--" + ProdModel);
                popupWindow.dismiss();
            }
        });

        popupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        popupWindow.setWidth(tv.getWidth());
        popupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                MyUtils.setWindowAlpa(mActivity, false);
            }
        });
        if (popupWindow != null && !popupWindow.isShowing()) {
//            popupWindow.showAsDropDown(tv, 0, 10);
            popupWindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);
        }
        MyUtils.setWindowAlpa(mActivity, true);
    }

    public void setSelect(int position) {
        for (int i = 0; i < popularList.size(); i++) {
            if (i == position) {
                popularList.get(i).setSelected(true);
            } else {
                popularList.get(i).setSelected(false);
            }
        }
        firstAdapter.setNewData(popularList);
    }


    private void scrollToMiddleH(View view, int position) {

        int vHeight = view.getHeight();

        Rect rect = new Rect();

        lv_popular.getGlobalVisibleRect(rect);

//        int reHeight = rect.top- rect.bottom - vHeight;
        int reHeight = rect.bottom - rect.top - vHeight;


        final int firstPosition = linearLayoutManager.findFirstVisibleItemPosition();

        int top = lv_popular.getChildAt(position - firstPosition).getTop();

        int half = reHeight / 2;

        lv_popular.smoothScrollBy(0, top - half);

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
                if (list.get(position) instanceof Province) {
                    ProvinceName = ((Province) list.get(position)).getName();
                    tv.setText(ProvinceName + ">");
                    ProvinceCode = ((Province) list.get(position)).getCode();
                    CityCode = null;
                    CityName = null;
                    mTvPca.setText(ProvinceName);
                }
                if (list.get(position) instanceof City) {
                    CityName = ((City) list.get(position)).getName();
                    tv.setText(CityName + ">");
                    CityCode = ((City) list.get(position)).getCode();
                    AreaCode = null;
                    AreaName = null;
                    mTvPca.setText(ProvinceName + CityName);
                }
                if (list.get(position) instanceof Area) {
                    AreaName = ((Area) list.get(position)).getName();
                    tv.setText(AreaName + ">");
                    AreaCode = ((Area) list.get(position)).getCode();
                    DistrictCode = null;
                    DistrictName = null;
                    mTvPca.setText(ProvinceName + CityName + AreaName);
                }
                if (list.get(position) instanceof District) {
                    DistrictName = ((District) list.get(position)).getName();
                    tv.setText(DistrictName + ">");
                    DistrictCode = ((District) list.get(position)).getCode();
                    mTvPca.setText(ProvinceName + CityName + AreaName + DistrictName);
                }
            }
        });
        popupWindow = new PopupWindow(contentView);
        popupWindow.setWidth(1000);
//        if (list.size() > 5) {
//            popupWindow.setHeight(600);
//        } else {
        popupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
//        }
//        popupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                MyUtils.setWindowAlpa(mActivity, false);
            }
        });
        if (popupWindow != null && !popupWindow.isShowing()) {
//            popupWindow.showAsDropDown(tv, 0, 10);
            popupWindow.showAtLocation(contentView, Gravity.CENTER, 0, 0);
        }
        MyUtils.setWindowAlpa(mActivity, true);
    }

    @Override
    public void GetFactoryCategory(BaseResult<CategoryData> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                CategoryData data = baseResult.getData();
                if ("0".equals(data.getCode())) {
                    popularList = data.getData();
                    if (popularList.size() == 0) {
                        ToastUtils.showShort("无分类，请联系管理员添加！");
                    } else {
//                        showPopWindowGetCategory(mTvChooseCategory);

                    }
                } else {
                    ToastUtils.showShort("获取分类失败！");
                }
                break;
            default:
//                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }

    @Override
    public void GetChildFactoryCategory(BaseResult<CategoryData> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                CategoryData data = baseResult.getData();
                if ("0".equals(data.getCode())) {
                    chooseList = data.getData();
                    if (chooseList.size() == 0) {
                        ToastUtils.showShort("无分类，请联系管理员添加！");
                    } else {
                        rv_choose.setLayoutManager(new LinearLayoutManager(mActivity));
                        chooseAdapter = new CategoryAdapter(R.layout.item_choose, chooseList);
                        rv_choose.setAdapter(chooseAdapter);
                        chooseAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                SubCategoryName = chooseList.get(position).getFCategoryName();
                                mTvChooseCategory.setText(SubCategoryName);
                                TypeID = null;
                                TypeName = null;
                                mTvChooseType.setText("");
                                SubCategoryID = chooseList.get(position).getFCategoryID();
                                popupWindow.dismiss();
                                mPresenter.GetFactoryBrand(userID);
                            }
                        });
                    }
                } else {
                    ToastUtils.showShort("获取分类失败！");
                }
                break;
            default:
//                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }

    @Override
    public void GetChildFactoryCategory2(BaseResult<CategoryData> baseResult) {

    }

    @Override
    public void GetFactoryBrand(BaseResult<List<Brand>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData() != null) {
                    brandList = baseResult.getData();
                }
                if (brandList.size() == 0) {
//                    ToastUtils.showShort("你还没添加品牌，请先添加品牌！");
                    final CommonDialog_Home dialog = new CommonDialog_Home(mActivity);
                    dialog.setMessage("你还没添加品牌，请先添加品牌！")
                            //.setImageResId(R.mipmap.ic_launcher)
                            .setTitle("提示")
                            .setPositive("去添加")
                            .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                        @Override
                        public void onPositiveClick() {//拨打电话
                            dialog.dismiss();
                            startActivity(new Intent(mActivity, BrandActivity.class));
                        }

                        @Override
                        public void onNegtiveClick() {//取消
                            dialog.dismiss();
                            // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                        }
                    }).show();

                } else {
                    brandsAdapter = new BrandChooseAdapter(R.layout.item_category, brandList);
                    showBrand(mTvChooseBrand, brandsAdapter, brandList, "brand");
//                    showPopWindow(mTvChooseBrand, brandsAdapter, brandList);
                }
                break;
            default:
//                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }

    private void showBrand(TextView tv, BaseQuickAdapter adapter, final List list, String judge) {
        View contentView = LayoutInflater.from(mActivity).inflate(R.layout.category_pop2, null);
        final RecyclerView rv = contentView.findViewById(R.id.rv);
        TextView tv_add = contentView.findViewById(R.id.tv_add);
        TextView tv_title = contentView.findViewById(R.id.tv_title);
        if ("brand".equals(judge)) {
            tv_title.setText("选择品牌");
            tv_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(mActivity, BrandActivity.class));
                }
            });
            tv_add.setText("添加品牌");
        } else {
            tv_title.setText("选择型号");
            tv_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(mActivity, ModelActivity.class));
                }
            });
            tv_add.setText("添加型号");
        }

        rv.setLayoutManager(new LinearLayoutManager(mActivity));
        rv.addItemDecoration(new RecyclerViewDivider(mActivity, LinearLayoutManager.HORIZONTAL));
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (list.get(position) instanceof Brand) {
                    FBrandID = ((Brand) list.get(position)).getFBrandID();
                    BrandName = ((Brand) list.get(position)).getFBrandName();
                    tv.setText(BrandName);
                    mPresenter.GetProdModel(specificationsID);
                    popupWindow.dismiss();
                }
                if (list.get(position) instanceof GetCategory) {
                    FCategoryID = ((GetCategory) list.get(position)).getCategoryID();
                    FCategoryName = ((GetCategory) list.get(position)).getCategoryName();
                    SubCategoryID = ((GetCategory) list.get(position)).getSubCategoryID();
                    SubCategoryName = ((GetCategory) list.get(position)).getSubCategoryName();
                    TypeID = ((GetCategory) list.get(position)).getProductTypeID();
                    TypeName = ((GetCategory) list.get(position)).getProductTypeName();
                    tv.setText(TypeName);
                    popupWindow.dismiss();
                }
            }
        });

        popupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        popupWindow.setWidth(tv.getWidth());
        popupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                MyUtils.setWindowAlpa(mActivity, false);
            }
        });
        if (popupWindow != null && !popupWindow.isShowing()) {
//            popupWindow.showAsDropDown(tv, 0, 10);
            popupWindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);
        }
        MyUtils.setWindowAlpa(mActivity, true);
    }

    @Override
    public void GetFactoryProducttype(BaseResult<Data<List<ProductType>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<List<ProductType>> data = baseResult.getData();
                if (data.isItem1()) {
                    productTypeList = data.getItem2();
                    if (productTypeList.size() == 0) {
                        MyUtils.showToast(mActivity, "无型号，请联系管理员！");
                    } else {
                        productTypeAdapter = new ProductTypeAdapter(R.layout.category_item, productTypeList);
                        showPopWindow(mTvChooseType, productTypeAdapter, productTypeList);
                    }
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
//                showPopWindow(mTvProvince, provinceAdapter, provinceList);
                if (popupWindow != null) {
                    if (popupWindow.isShowing()) {
                        rv_address_choose.setAdapter(provinceAdapter);
                        provinceAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                ProvinceName = provinceList.get(position).getName();
                                ProvinceCode = provinceList.get(position).getCode();
                                mPresenter.GetCity(ProvinceCode);
                                tv_province.setText(ProvinceName);
                                tv_province.setVisibility(View.VISIBLE);
                                tv_city.setVisibility(View.VISIBLE);
                            }
                        });
                    } else {
                        showPopWindowGetAddress(mTvAddress);
                    }
                } else {
                    showPopWindowGetAddress(mTvAddress);
                }

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
                    rv_address_choose.setAdapter(cityAdapter);
                    tv_choose.setText("选择城市");
                    cityAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            CityName = cityList.get(position).getName();
                            CityCode = cityList.get(position).getCode();
                            mPresenter.GetArea(CityCode);
                            tv_city.setText(CityName);
                            tv_province.setVisibility(View.VISIBLE);
                            tv_city.setVisibility(View.VISIBLE);
                            tv_area.setVisibility(View.VISIBLE);
                        }
                    });
//                    showPopWindow(mTvCity, cityAdapter, cityList);
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
                    rv_address_choose.setAdapter(areaAdapter);
                    tv_choose.setText("选择区/县");
                    areaAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            AreaName = areaList.get(position).getName();
                            AreaCode = areaList.get(position).getCode();
                            mPresenter.GetDistrict(AreaCode);
                            tv_area.setText(AreaName);
                            tv_province.setVisibility(View.VISIBLE);
                            tv_city.setVisibility(View.VISIBLE);
                            tv_area.setVisibility(View.VISIBLE);
                        }
                    });
//                    showPopWindow(mTvArea, areaAdapter, areaList);
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
    public void GetDistrict(BaseResult<Data<List<District>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<List<District>> data = baseResult.getData();
                if (data.isItem1()) {
                    districtList = data.getItem2();
                    districtAdapter = new DistrictAdapter(R.layout.category_item, districtList);
                    rv_address_choose.setAdapter(districtAdapter);
                    tv_choose.setText("选择街道/乡/镇");
                    districtAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            DistrictName = districtList.get(position).getName();
                            DistrictCode = districtList.get(position).getCode();
                            tv_district.setText(DistrictName);
                            tv_province.setVisibility(View.VISIBLE);
                            tv_city.setVisibility(View.VISIBLE);
                            tv_area.setVisibility(View.VISIBLE);
                            tv_district.setVisibility(View.VISIBLE);
                            popupWindow.dismiss();
                            mTvAddress.setText(ProvinceName + " " + CityName + " " + AreaName + " " + DistrictName);
                            mTvPca.setText(ProvinceName + "/" + CityName + "/" + AreaName + "/" + DistrictName);
                        }
                    });
                } else {
                    MyUtils.showToast(mActivity, "获取街道/乡/镇失败");
                }
                break;
            case 401:
                break;
        }
    }

    @Override
    public void AddOrder(AddOrderResult baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                AddOrderResult.DataBean data = baseResult.getData();
                if (data.isStatus()) {
                    ToastUtils.showShort(data.getMsg());
                    cancleLoading();
                    EventBus.getDefault().post(11);
                    EventBus.getDefault().post(6);
                    finish();
                } else {
                    ToastUtils.showShort(data.getMsg());
                }
                break;
        }

    }

    @Override
    public void GetAccountAddress(BaseResult<List<com.zhenhaikj.factoryside.mvp.bean.Address>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                addressList = baseResult.getData();
                if (addressList.size() != 0) {
                    for (int i = 0; i < addressList.size(); i++) {
                        if ("1".equals(addressList.get(i).getIsDefault())) {
                            AddressBack = addressList.get(i).getProvince() + addressList.get(i).getCity() + addressList.get(i).getArea() + addressList.get(i).getDistrict() + addressList.get(i).getAddress() + "(" + addressList.get(i).getUserName() + "收)" + addressList.get(i).getPhone();
                            backAddrID = addressList.get(i).getId();
                            mTvAddressback.setText(AddressBack);
                            mTvModify.setText("修改地址");
                        } else {
                            AddressBack = addressList.get(0).getProvince() + addressList.get(0).getCity() + addressList.get(0).getArea() + addressList.get(0).getDistrict() + addressList.get(0).getAddress() + "(" + addressList.get(0).getUserName() + "收)" + addressList.get(0).getPhone();
                            backAddrID = addressList.get(i).getId();
                            mTvAddressback.setText(AddressBack);
                            mTvModify.setText("修改地址");
                        }
                    }
                } else {
                    AddressBack = "";
                    mTvAddressback.setText(AddressBack);
                    mTvModify.setText("添加地址");
                }
                break;
            default:
                ToastUtils.showShort("获取失败");
                break;
        }
    }

    @Override
    public void GetBrandCategory(BaseResult<Data<Category>> baseResult) {

    }

    @Override
    public void GetBrandWithCategory(BaseResult<Data<List<GetCategory>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                List<GetCategory> data = baseResult.getData().getItem2();
                if (data.size() == 0) {
//                    ToastUtils.showShort("请添加型号");
                    final CommonDialog_Home dialog = new CommonDialog_Home(mActivity);
                    dialog.setMessage("该品牌下面无型号，请添加型号")
                            //.setImageResId(R.mipmap.ic_launcher)
                            .setTitle("提示")
                            .setPositive("去添加")
                            .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                        @Override
                        public void onPositiveClick() {//拨打电话
                            dialog.dismiss();
                            startActivity(new Intent(mActivity, AddModelActivity.class));
                        }

                        @Override
                        public void onNegtiveClick() {//取消
                            dialog.dismiss();
                            // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                        }
                    }).show();

                } else {

//                        rv_choose.setLayoutManager(new LinearLayoutManager(mActivity));
                    chooseAdapter2 = new CategoryAdapter2(R.layout.item_category, data);
//                        rv_choose.setAdapter(chooseAdapter);
//                        showPopWindow(mTvChooseType, chooseAdapter, chooseList);
                    showBrand(mTvChooseType, chooseAdapter2, data, "category");
                }

                break;
            default:
//                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }

    @Override
    public void GetUniqId(BaseResult<String> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                String key = baseResult.getData();
                break;
        }
    }

    @Override
    public void GetFactoryProd(GetFactoryProdResult baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                GetFactoryProdResultList = baseResult.getData();
                if (GetFactoryProdResultList.size() == 0) {
                    ToastUtils.showShort("无常用型号！");
                } else {
                    showPopWindowGetFactoryProdResult(mTvChooseCommonType, GetFactoryProdResultList);

                }

                break;
            default:
//                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }

    @Override
    public void GetProdCategory(GetProdCategoryResult baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                categoryNewList = baseResult.getData();
                if (categoryNewList.size() == 0) {
                    ToastUtils.showShort("无分类，请添加分类！");
                } else {
                    showPopWindowGetCategory(mTvChooseCategory, categoryNewList);

                }

                break;
            default:
//                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }

    @Override
    public void GetSingleProd(GetSingleProdResult baseResult) {

    }

    @Override
    public void GetProdSpecifications(GetProdSpecificationsResult baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                prodSpecificationsResultList = baseResult.getData();
                if (prodSpecificationsResultList.size() == 0) {
                    ToastUtils.showShort("无规格，请联系管理员！");
                } else {
                    showPopWindowGetProdSpecificationsResult(mTvChooseGg, prodSpecificationsResultList);
                }
                break;
            default:
                break;
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (!running) {
            myRecognizer.release();
//            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!running) {
            myRecognizer.release();
//            finish();
        }
    }

    /**
     * android 6.0 以上需要动态申请权限
     */
    private void initPermission() {
        String[] permissions = {
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.INTERNET,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        ArrayList<String> toApplyList = new ArrayList<String>();

        for (String perm : permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, perm)) {
                toApplyList.add(perm);
                // 进入到这里代表没有权限.

            }
        }
        String[] tmpList = new String[toApplyList.size()];
        if (!toApplyList.isEmpty()) {
            ActivityCompat.requestPermissions(this, toApplyList.toArray(tmpList), 123);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // 此处为android 6.0以上动态授权的回调，用户自行实现。
        int count = 0;
        if (requestCode == 123) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    count++;
                } else {
                    // Permission Denied
                }
            }
            if (count == permissions.length) {

            } else {
                ToastUtils.showShort("没有相关权限！");
            }
        }
    }


    public void showLoading() {
        dialog.setLoadingBuilder(Z_TYPE.SINGLE_CIRCLE)//设置类型
                .setLoadingColor(Color.BLACK)//颜色
                .setHintText("发单中...")
                .setHintTextSize(14) // 设置字体大小 dp
                .setHintTextColor(Color.BLACK)  // 设置字体颜色
                .setDurationTime(0.5) // 设置动画时间百分比 - 0.5倍
                .setCanceledOnTouchOutside(false)//点击外部无法取消
                .show();
    }

    public void cancleLoading() {
        dialog.dismiss();
    }
}