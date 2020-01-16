package com.zhenhaikj.factoryside.mvp.activity;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.searchdemo.MainActivity;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.contract.VerifiedContract;
import com.zhenhaikj.factoryside.mvp.model.VerifiedModel;
import com.zhenhaikj.factoryside.mvp.presenter.VerifiedPresenter;
import com.zhenhaikj.factoryside.mvp.utils.MyUtils;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.core.content.PermissionChecker;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class VerifiedActivity extends BaseActivity<VerifiedPresenter, VerifiedModel> implements View.OnClickListener, VerifiedContract.View {
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
    @BindView(R.id.bank_card_ll)
    LinearLayout mBankCardLl;
    @BindView(R.id.actual_name_et)
    EditText mActualNameEt;
    @BindView(R.id.id_number_et)
    EditText mIdNumberEt;
    @BindView(R.id.iv_positive)
    ImageView mIvPositive;
    @BindView(R.id.tv_shop_address)
    TextView mTvShopAddress;
    @BindView(R.id.ll_shop_address)
    LinearLayout mLlShopAddress;
    @BindView(R.id.submit_application_bt)
    Button mSubmitApplicationBt;
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_consumer_hotline)
    EditText mEtConsumerHotline;
    @BindView(R.id.et_financial_phone)
    EditText mEtFinancialPhone;
    @BindView(R.id.et_technical_phone)
    EditText mEtTechnicalPhone;
    private ArrayList<String> permissions;
    private PopupWindow mPopupWindow;
    private int size;
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    ZLoadingDialog dialog = new ZLoadingDialog(this); //loading
    private int mLocationType;
    private double mLatitude;
    private double mLongitude;
    private float mAccuracy;
    private String mAddress = "";
    private String mCountry;
    private String mProvince;
    private String mCity;
    private String mDistrict;
    private String mStreet;
    private String mStreetNum;
    private String mCityCode;
    private String mAdCode;
    private String mAoiName;
    private String mBuildingId;
    private String mFloor;
    private int mGpsAccuracyStatus;
    private String mTime;
    private ObjectAnimator animator; //刷新图片属性动画
    private List<Uri> mSelected;
    private String mPositiveCard = "";
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            //定位结果回调
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
//可在其中解析amapLocation获取相应内容。
                    mLocationType = aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                    mLatitude = aMapLocation.getLatitude();//获取纬度
                    mLongitude = aMapLocation.getLongitude();//获取经度
                    mAccuracy = aMapLocation.getAccuracy();//获取精度信息
                    mAddress = aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                    mCountry = aMapLocation.getCountry();//国家信息
                    mProvince = aMapLocation.getProvince();//省信息
                    mCity = aMapLocation.getCity();//城市信息
                    mDistrict = aMapLocation.getDistrict();//城区信息
                    mStreet = aMapLocation.getStreet();//街道信息
                    mStreetNum = aMapLocation.getStreetNum();//街道门牌号信息
                    mCityCode = aMapLocation.getCityCode();//城市编码
                    mAdCode = aMapLocation.getAdCode();//地区编码
                    mAoiName = aMapLocation.getAoiName();//获取当前定位点的AOI信息
                    mBuildingId = aMapLocation.getBuildingId();//获取当前室内定位的建筑物Id
                    mFloor = aMapLocation.getFloor();//获取当前室内定位的楼层
                    mGpsAccuracyStatus = aMapLocation.getGpsAccuracyStatus();//获取GPS的当前状态
                    //获取定位时间
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date(aMapLocation.getTime());
                    mTime = df.format(date);
                    if (mAddress != null) {
                        mTvShopAddress.setText(mAddress);
                    } else {
                        mTvShopAddress.setText(aMapLocation.getAddress());
                    }

                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
                mLocationClient.stopLocation();
            }
        }
    };
    private String FilePath;
    private String userID;
    private Uri uri;
    private View popupWindow_view;

    public void Location() {
        //初始化定位
        mLocationClient = new AMapLocationClient(mActivity);
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
/**
 * 设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
 */
        /*mLocationOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
        if (null != mLocationClient) {
            mLocationClient.setLocationOption(mLocationOption);
            //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
            mLocationClient.stopLocation();
            mLocationClient.startLocation();
        }*/
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);

        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initData() {
        if (requestLocationPermissions()) {
            Location();
        } else {
            requestPermissions(permissions.toArray(new String[permissions.size()]), 20002);
        }


    }


    @Override
    protected int setLayoutId() {
        return R.layout.activity_verified;
    }

    @Override
    protected void initView() {
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText("实名认证");
        SPUtils spUtils = SPUtils.getInstance("token");
        userID = spUtils.getString("userName");
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mIvPositive.setOnClickListener(this);
        mLlShopAddress.setOnClickListener(this);
        mSubmitApplicationBt.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.iv_positive:
                if (requestPermissions()) {
                    showPopupWindow(101, 102);
                } else {
                    requestPermissions(permissions.toArray(new String[permissions.size()]), 10001);
                }
                break;
            case R.id.ll_shop_address:
                startActivityForResult(new Intent(mActivity, MainActivity.class), 100);
                break;
            case R.id.submit_application_bt:
                String mActualName = mActualNameEt.getText().toString();
                String mIdNumber = mIdNumberEt.getText().toString();
                String name =mEtName.getText().toString();
                String phone=mEtPhone.getText().toString();
                String consumer_hotline=mEtConsumerHotline.getText().toString();
                String financial_phone=mEtFinancialPhone.getText().toString();
                String technical_phone=mEtTechnicalPhone.getText().toString();

                if ("".equals(mActualName)) {
                    ToastUtils.showShort("请输入真实公司名！");
                    return;
                }

                if ("".equals(mIdNumber)) {
                    ToastUtils.showShort("请输入营业执照号！");
                    return;
                }
//                if (!RegexUtils.isIDCard18Exact(mIdNumber)) {
//                    ToastUtils.showShort("身份证号码格式错误！");
//                    return;
//                }

                if ("".equals(mPositiveCard)) {
                    ToastUtils.showShort("请添加营业执照照片！");
                    return;
                }

                if ("".equals(mAddress)) {
                    ToastUtils.showShort("未定位到地址！");
                    return;
                }
                showLoading();
//                mPresenter.ApplyAuthInfo(UserID, mActualName, Sex, mIdNumber, mAddress, NodeIds, mProvince, mCity, mDistrict, mStreet, Double.toString(mLongitude), Double.toString(mLatitude), codestr, "1");
                mPresenter.FactoryApplyAuthInfo(userID, mActualName, mIdNumber,name,phone,consumer_hotline,financial_phone,technical_phone,null,mProvince, mCity, mDistrict, mStreet,mStreetNum,"0","Y");
                break;
        }
    }

    //请求定位权限
    private boolean requestLocationPermissions() {
        if (Build.VERSION.SDK_INT >= 23) {
            permissions = new ArrayList<>();
            if (mActivity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if (permissions.size() == 0) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    //请求权限
    private boolean requestPermissions() {
        if (Build.VERSION.SDK_INT >= 23) {
            permissions = new ArrayList<>();

            if (PermissionChecker.checkSelfPermission(mActivity,Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
            if (PermissionChecker.checkSelfPermission(mActivity,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (PermissionChecker.checkSelfPermission(mActivity,Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.CAMERA);
            }
            if (permissions.size() == 0) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @Override
    public void IDCardUpload(BaseResult<Data<String>> baseResult, int code) {
        switch (code) {
            case 0:
                switch (baseResult.getStatusCode()) {
                    case 200:
                        if (baseResult.getData().isItem1()) {
                            mPositiveCard = baseResult.getData().getItem2();
                        }
                        break;
                    default:
                        ToastUtils.showShort("图片上传失败");
                        break;
                }
                break;
        }

    }

    @Override
    public void FactoryApplyAuthInfo(BaseResult<Data<String>> baseObserver) {
        switch (baseObserver.getStatusCode()) {
            case 200:
                if (baseObserver.getData().isItem1()) {
                    ToastUtils.showShort("提交成功");
                    EventBus.getDefault().post("GetUserInfoList");
                    finish();
                }
                break;
            default:
                cancleLoading();
                ToastUtils.showShort("提交失败");
                break;
        }
    }


    /**
     * 弹出Popupwindow
     */
    public void showPopupWindow(final int code1, final int code2) {
        popupWindow_view = LayoutInflater.from(mActivity).inflate(R.layout.camera_layout, null);
        Button camera_btn = popupWindow_view.findViewById(R.id.camera_btn);
        Button photo_btn = popupWindow_view.findViewById(R.id.photo_btn);
        Button cancel_btn = popupWindow_view.findViewById(R.id.cancel_btn);
        camera_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (requestPermissions()) {
                    Intent intent = new Intent();
                    intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    String f = System.currentTimeMillis() + ".jpg";
                    String fileDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/xgy";
                    FilePath = Environment.getExternalStorageDirectory().getAbsoluteFile() + "/xgy/" + f;
                    File dirfile = new File(fileDir);
                    if (!dirfile.exists()) {
                        dirfile.mkdirs();
                    }
                    File file = new File(FilePath);
                    Uri fileUri;
                    if (Build.VERSION.SDK_INT >= 24) {
                        fileUri = FileProvider.getUriForFile(mActivity, "com.zhenhaikj.factoryside.fileProvider", file);
                    } else {
                        fileUri = Uri.fromFile(file);
                    }

                    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                    startActivityForResult(intent, code1);
                } else {
                    requestPermissions(permissions.toArray(new String[permissions.size()]), 10001);
                }
                mPopupWindow.dismiss();
            }
        });

        photo_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (requestPermissions()) {
                    Intent intent1 = new Intent(Intent.ACTION_GET_CONTENT);
                    intent1.addCategory(Intent.CATEGORY_OPENABLE);
                    intent1.setType("image/*");
                    startActivityForResult(Intent.createChooser(intent1, "test"), code2);
                    mPopupWindow.dismiss();
                } else {
                    requestPermissions(permissions.toArray(new String[permissions.size()]), 10002);
                }
            }
        });
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });
        mPopupWindow = new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                MyUtils.setWindowAlpa(mActivity, false);
            }
        });
//        if (mPopupWindow != null && !mPopupWindow.isShowing()) {
            mPopupWindow.showAtLocation(popupWindow_view, Gravity.BOTTOM, 0, 0);
//        }
        MyUtils.setWindowAlpa(mActivity, true);
    }

    //申请相关权限:返回监听
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        size = 0;
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                size++;
            }
        }
        switch (requestCode) {
            case 10001:
                if (size == grantResults.length) {//允许
                    showPopupWindow(101, 102);
                } else {//拒绝
                    MyUtils.showToast(mActivity, "相关权限未开启");
                }
                break;
            case 20002:
                if (size == grantResults.length) {//允许
                    Location();
                } else {//拒绝
                    MyUtils.showToast(mActivity, "相关权限未开启");
                }

                break;
            default:
                break;

        }
    }

    //返回图片处理
    @SuppressLint("NewApi")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        File file = null;
        switch (requestCode) {
            //拍照
            case 101:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(mIvPositive);
                    file = new File(FilePath);
                }
                if (file != null) {
                    uploadImg(file, 0);
                }
                break;
            //相册
            case 102:
                if (data != null) {
                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(mIvPositive);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));

                }
                if (file != null) {
                    uploadImg(file, 0);
                }
                break;
            case 100:
                if (data != null) {
                    mAddress = data.getStringExtra("address");
                    mProvince = data.getStringExtra("Province");
                    mCity = data.getStringExtra("City");
                    mDistrict = data.getStringExtra("Area");
                    mStreet = data.getStringExtra("District");
                    mLongitude = data.getDoubleExtra("Longitude", -1);
                    mLatitude = data.getDoubleExtra("Dimension", -1);
                    if (mAddress != null) {
                        mTvShopAddress.setText(mAddress);
                    }
                } else {
                    mTvShopAddress.setText(mAddress);
                }
                break;

        }

    }

    public void uploadImg(File f, int code) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("img", f.getName(), RequestBody.create(MediaType.parse("img/png"), f));
        builder.addFormDataPart("UserID", userID);
        builder.addFormDataPart("Sort", (code + 1) + "");
        MultipartBody requestBody = builder.build();
        mPresenter.IDCardUpload(requestBody, code);
    }

    public void showLoading() {
        dialog.setLoadingBuilder(Z_TYPE.SINGLE_CIRCLE)//设置类型
                .setLoadingColor(Color.BLACK)//颜色
                .setHintText("提交中请稍后...")
                .setHintTextSize(14) // 设置字体大小 dp
                .setHintTextColor(Color.BLACK)  // 设置字体颜色
                .setDurationTime(1) // 设置动画时间百分比 - 0.5倍
                .setCanceledOnTouchOutside(false)//点击外部无法取消
                .show();
    }

    public void cancleLoading() {
        dialog.dismiss();
    }
}
