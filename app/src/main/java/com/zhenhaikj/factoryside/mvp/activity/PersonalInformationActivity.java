package com.zhenhaikj.factoryside.mvp.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.adapter.BillAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.RechargeRecordAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Address;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.UserInfo;
import com.zhenhaikj.factoryside.mvp.contract.InfoManageContract;
import com.zhenhaikj.factoryside.mvp.model.InfoManageModel;
import com.zhenhaikj.factoryside.mvp.presenter.InfoManagePresenter;
import com.zhenhaikj.factoryside.mvp.utils.MyUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class PersonalInformationActivity extends BaseActivity<InfoManagePresenter, InfoManageModel> implements View.OnClickListener, InfoManageContract.View {


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
    @BindView(R.id.iv_avatar)
    ImageView mIvAvatar;
    @BindView(R.id.ll_avatar)
    LinearLayout mLlAvatar;
    @BindView(R.id.tv_nickname)
    TextView mTvNickname;
    @BindView(R.id.ll_nickname)
    LinearLayout mLlNickname;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_certification)
    TextView mTvCertification;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.ll_password)
    LinearLayout mLlPassword;
    @BindView(R.id.tv_id_card)
    TextView mTvIdCard;
    @BindView(R.id.iv_id_card_one)
    ImageView mIvIdCardOne;
    @BindView(R.id.iv_id_card_two)
    ImageView mIvIdCardTwo;
    @BindView(R.id.tv_shop_address)
    TextView mTvShopAddress;
    @BindView(R.id.ll_shop_address)
    LinearLayout mLlShopAddress;
    @BindView(R.id.ll_male)
    LinearLayout mLlMale;
    @BindView(R.id.ll_female)
    LinearLayout mLlFemale;
    @BindView(R.id.tv_my_skills)
    TextView mTvMySkills;
    @BindView(R.id.ll_my_skills)
    LinearLayout mLlMySkills;
    @BindView(R.id.tv_recipient_address)
    TextView mTvRecipientAddress;
    @BindView(R.id.ll_recipient_address)
    LinearLayout mLlRecipientAddress;
    @BindView(R.id.btn_sign_out_of_your_account)
    Button mBtnSignOutOfYourAccount;
    private List<Address> billList = new ArrayList<>();
    private List<Address> rechargeRecordList = new ArrayList<>();
    private BillAdapter billAdapter;
    private RechargeRecordAdapter rechargeRecordAdapter;
    private View popupWindow_view;
    private PopupWindow mPopupWindow;
    private ArrayList<String> permissions;
    private String filePath;
    private int size;
    private String userId;
    private SPUtils spUtils;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_personal_information;
    }

    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
//        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarView(mView);
        mImmersionBar.keyboardEnable(true);
        mImmersionBar.init();
    }

    @Override
    protected void initData() {
        spUtils = SPUtils.getInstance("token");
        userId= spUtils.getString("userName");
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void initView() {
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText("个人信息管理");
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mBtnSignOutOfYourAccount.setOnClickListener(this);
        mLlNickname.setOnClickListener(this);
        mLlPassword.setOnClickListener(this);
        mLlAvatar.setOnClickListener(this);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.btn_sign_out_of_your_account:
                spUtils.put("isLogin",false);
                startActivity(new Intent(mActivity, LoginActivity.class));
                ActivityUtils.finishAllActivities();
                break;
            case R.id.ll_nickname:
                startActivity(new Intent(mActivity,ChageUserNameActivity.class));
                break;
            case R.id.ll_password:
                startActivity(new Intent(mActivity,ChagePasswordActivity.class));
                break;
            case R.id.ll_avatar:
                if (requestPermissions()){
                    showPopupWindow(101,102);
                }else {
                    requestPermissions(permissions.toArray(new String[permissions.size()]),10001);
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    public void showPopupWindow(final int code1,final int code2 ){
        popupWindow_view = LayoutInflater.from(mActivity).inflate(R.layout.camera_layout,null);
        Button camera_btn=popupWindow_view.findViewById(R.id.camera_btn);
        Button photo_btn=popupWindow_view.findViewById(R.id.photo_btn);
        Button cancel_btn=popupWindow_view.findViewById(R.id.cancel_btn);
        camera_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (requestPermissions()){
                    Intent intent=new Intent();
                    intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    String f=System.currentTimeMillis()+".jpg";
                    String fileDir= Environment.getExternalStorageDirectory().getAbsolutePath()+"/xgy";
                    filePath = Environment.getExternalStorageDirectory().getAbsoluteFile()+"/xgy/"+f;
                    File dirfile=new File(fileDir);
                    if (!dirfile.exists()){
                        dirfile.mkdirs();
                    }
                    File file=new File(filePath);
                    Uri fileUri;
                    if (Build.VERSION.SDK_INT>=24){
                        fileUri= FileProvider.getUriForFile(mActivity,"com.zhenhaikj.factoryside.mvp.fileProvider",file);
                    }else {
                        fileUri=Uri.fromFile(file);
                    }

                    intent.putExtra(MediaStore.EXTRA_OUTPUT,fileUri);
                    startActivityForResult(intent,code1);
                }else {
                    requestPermissions(permissions.toArray(new String[permissions.size()]),10001);
                }
                mPopupWindow.dismiss();
            }
        });

        photo_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (requestPermissions()){
                    Intent intent1=new Intent(Intent.ACTION_GET_CONTENT);
                    intent1.addCategory(Intent.CATEGORY_OPENABLE);
                    intent1.setType("image/*");
                    startActivityForResult(Intent.createChooser(intent1,"test"),code2);
                    mPopupWindow.dismiss();
                }else {
                    requestPermissions(permissions.toArray(new String[permissions.size()]), 10002);
                }
            }
        });
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });

        mPopupWindow=new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                MyUtils.setWindowAlpa(mActivity,false);
            }
        });
        if (mPopupWindow!=null&&!mPopupWindow.isShowing()){
            mPopupWindow.showAtLocation(popupWindow_view, Gravity.BOTTOM,0,0);
        }
        MyUtils.setWindowAlpa(mActivity,true);
    }

    //请求权限
    private boolean requestPermissions() {
        if (Build.VERSION.SDK_INT >= 23) {
            permissions = new ArrayList<>();
            if (mActivity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
            if (mActivity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (mActivity.checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        size = 0;
        for (int i=0;i<grantResults.length;i++){
            if (grantResults[i]==PackageManager.PERMISSION_GRANTED){
                size++;
            }
        }
        switch (requestCode){
            case 1001:
                if (size==grantResults.length){
                    showPopupWindow(101,102);
                }else {
                    MyUtils.showToast(mActivity,"相关权限未开启");
                }
                break;
            case 1002:
                if (size==grantResults.length){
                    showPopupWindow(201,202);
                }else {
                    MyUtils.showToast(mActivity,"相关权限未开启");
                }
        }
    }

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {

    }

    @Override
    public void UpdateAccountNickName(BaseResult<Data> baseResult) {

    }

    @Override
    public void UpdatePassword(BaseResult<Data> baseResult) {

    }

    @Override
    public void UploadAvator(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                if (!baseResult.getData().isItem1()){

                    Toast.makeText(this,"图片上传失败",Toast.LENGTH_SHORT).show();
                }else {

                    Toast.makeText(this,"图片上传成功",Toast.LENGTH_SHORT).show();
                    EventBus.getDefault().post("");
                }

                break;

            default:
                Toast.makeText(mActivity,"修改失败",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void uploadImg(File f){
        MultipartBody.Builder builder=new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("img",f.getName(), RequestBody.create(MediaType.parse("img/png"),f));
        builder.addFormDataPart("UserId",userId);
        MultipartBody requestBody=builder.build();
        mPresenter.UploadAvator(requestBody);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        File file = null;
        switch (requestCode) {
            //拍照获取图片
            case 101:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(filePath).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(mIvAvatar);
                    file = new File(filePath);
                }
                if (file != null) {
                    uploadImg(file);
                }
                break;
            //从相册中获取
            case 102:
                if (data != null) {
                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(mIvAvatar);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                if (file != null) {
                    uploadImg(file);
                }
                break;

            default:
                break;
        }

    }
}