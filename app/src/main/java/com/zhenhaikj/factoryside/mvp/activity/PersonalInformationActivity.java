package com.zhenhaikj.factoryside.mvp.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.gyf.barlibrary.ImmersionBar;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.Config;
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
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileNotFoundException;
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


    private static final String TAG = "PersonalInformationActivity";
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
    @BindView(R.id.iv_male)
    ImageView mIvMale;
    @BindView(R.id.iv_female)
    ImageView mIvFemale;
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
    private UserInfo.UserInfoDean userInfoDean;

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
        userId = spUtils.getString("userName");
        mPresenter.GetUserInfoList(userId, "1");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String message) {
        if (!"GetUserInfoList".equals(message)){
            return;
        }
        mPresenter.GetUserInfoList(userId, "1");
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
        mLlFemale.setOnClickListener(this);
        mLlMale.setOnClickListener(this);
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

                break;
            case R.id.ll_nickname:
                startActivity(new Intent(mActivity, ChageUserNameActivity.class));
                break;
            case R.id.ll_password:
                startActivity(new Intent(mActivity, AccountAndSecurityActivity.class));
                break;
            case R.id.ll_avatar:
                if (requestPermissions()) {
                    showPopupWindow(101, 102);
                } else {
                    requestPermissions(permissions.toArray(new String[permissions.size()]), 10001);
                }
                break;
            case R.id.ll_female:
                mIvFemale.setSelected(true);
                mIvMale.setSelected(false);
                mPresenter.UpdateSex(userId,"女");
                break;
            case R.id.ll_male:
                mIvMale.setSelected(true);
                mIvFemale.setSelected(false);
                mPresenter.UpdateSex(userId,"男");
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


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
                    filePath = Environment.getExternalStorageDirectory().getAbsoluteFile() + "/xgy/" + f;
                    File dirfile = new File(fileDir);
                    if (!dirfile.exists()) {
                        dirfile.mkdirs();
                    }
                    File file = new File(filePath);
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
            public void onClick(View v) {
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
        if (mPopupWindow != null && !mPopupWindow.isShowing()) {
            mPopupWindow.showAtLocation(popupWindow_view, Gravity.BOTTOM, 0, 0);
        }
        MyUtils.setWindowAlpa(mActivity, true);
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
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                size++;
            }
        }
        switch (requestCode) {
            case 1001:
                if (size == grantResults.length) {
                    showPopupWindow(101, 102);
                } else {
                    MyUtils.showToast(mActivity, "相关权限未开启");
                }
                break;
            case 1002:
                if (size == grantResults.length) {
                    showPopupWindow(201, 202);
                } else {
                    MyUtils.showToast(mActivity, "相关权限未开启");
                }
        }
    }

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                userInfoDean = baseResult.getData().getData().get(0);
                if (userInfoDean.getAvator() == null) {
                    return;
                } else {
                    Glide.with(mActivity)
                            .load(Config.HEAD_URL + userInfoDean.getAvator())
                            .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                            .into(mIvAvatar);
                }
                mTvNickname.setText(userInfoDean.getNickName());
                mTvName.setText(userInfoDean.getTrueName());
                mTvPhone.setText(userInfoDean.getPhone());
//                Log.d(TAG,"......"+userInfoDean.getSex());
//                if (userInfoDean.getSex().equals("男")){
//                    mIvMale.setSelected(true);
//                    mIvFemale.setSelected(false);
//                }else if(userInfoDean.getSex().equals("女")){
//                    mIvFemale.setSelected(true);
//                    mIvMale.setSelected(false);
//                }else {
//                    mIvMale.setSelected(false);
//                    mIvFemale.setSelected(false);
//                }
                break;
            case 401:
                break;
        }
    }

    @Override
    public void UpdateAccountNickName(BaseResult<Data> baseResult) {

    }

    @Override
    public void UpdatePassword(BaseResult<Data> baseResult) {

    }

    @Override
    public void UploadAvator(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (!baseResult.getData().isItem1()) {

                    Toast.makeText(this, "图片上传失败", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(this, "图片上传成功", Toast.LENGTH_SHORT).show();
                    EventBus.getDefault().post("GetUserInfoList");
                }

                break;

            default:
                Toast.makeText(mActivity, "修改失败", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void UpdateSex(BaseResult<Data> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                ToastUtils.showShort("修改成功");
                break;
            case 401:
                break;
        }
    }

    public void uploadImg(File f) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("img", f.getName(), RequestBody.create(MediaType.parse("img/png"), f));
        builder.addFormDataPart("UserId", userId);
        MultipartBody requestBody = builder.build();
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

                    startCrop(Uri.fromFile(file));
                }
             /*   if (file != null) {
                    uploadImg(file);
                }*/
                break;
            //从相册中获取
            case 102:
                if (data != null) {
                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(mIvAvatar);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));

                    startCrop(Uri.fromFile(file));
                }
               /* if (file != null) {
                    uploadImg(file);
                }*/
                break;

            //裁剪后的效果
            case UCrop.REQUEST_CROP:
                if (resultCode==RESULT_OK){
                    Uri resultUri=UCrop.getOutput(data);

                    try {
                        Bitmap bitmap= BitmapFactory.decodeStream(getContentResolver().openInputStream(resultUri));
                        // headimageView.setImageBitmap(bitmap);
                        Glide.with(mActivity).load(bitmap).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(mIvAvatar);

                       File file1=uritoFile(resultUri);

                        if (file1!=null){
                           /* File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file1);*/
                            uploadImg(file1);
                        }


                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }



                }

                //错误裁剪的结果
            case UCrop.RESULT_ERROR:
                if(resultCode==RESULT_OK){
                    final Throwable cropError = UCrop.getError(data);
                    handleCropError(cropError);
                }
                break;


            default:
                break;
        }

    }

    //处理剪切失败的返回值
    private void handleCropError(Throwable cropError) {
        deleteTempPhotoFile();
        if (cropError != null) {
            Toast.makeText(this, cropError.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 删除拍照临时文件
     */
    private void deleteTempPhotoFile() {
        File tempFile = new File(Environment.getExternalStorageDirectory() + File.separator + "output_iamge.jpg");
        if (tempFile.exists() && tempFile.isFile()) {
            tempFile.delete();
        }
    }


    //图片裁剪的方法
    private void startCrop(Uri uri){
        UCrop.Options options = new UCrop.Options();
        //裁剪后图片保存在文件夹中
        Uri destinationUri = Uri.fromFile(new File(getExternalCacheDir(), "uCrop.jpg"));
        UCrop uCrop = UCrop.of(uri, destinationUri);//第一个参数是裁剪前的uri,第二个参数是裁剪后的uri
        uCrop.withAspectRatio(1,1);//设置裁剪框的宽高比例
        //下面参数分别是缩放,旋转,裁剪框的比例
        options.setAllowedGestures(UCropActivity.ALL,UCropActivity.NONE,UCropActivity.ALL);
        options.setToolbarTitle("头像裁剪");//设置标题栏文字
        options.setCropGridStrokeWidth(2);//设置裁剪网格线的宽度(我这网格设置不显示，所以没效果)
        options.setCropFrameStrokeWidth(10);//设置裁剪框的宽度
        options.setMaxScaleMultiplier(3);//设置最大缩放比例
        options.setHideBottomControls(true);//隐藏下边控制栏
        options.setShowCropGrid(false);  //设置是否显示裁剪网格
        options.setOvalDimmedLayer(true);//设置是否为圆形裁剪框
        options.setShowCropFrame(false); //设置是否显示裁剪边框(true为方形边框)
        options.setToolbarWidgetColor(Color.parseColor("#ffffff"));//标题字的颜色以及按钮颜色
        options.setDimmedLayerColor(Color.parseColor("#AA000000"));//设置裁剪外颜色
        options.setToolbarColor(Color.parseColor("#000000")); // 设置标题栏颜色
        options.setStatusBarColor(Color.parseColor("#000000"));//设置状态栏颜色
        options.setCropGridColor(Color.parseColor("#ffffff"));//设置裁剪网格的颜色
        options.setCropFrameColor(Color.parseColor("#ffffff"));//设置裁剪框的颜色
        uCrop.withOptions(options);
        uCrop.start(this);
    }

    private File uritoFile(Uri uri) {
        String img_path;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor actualimagecursor = managedQuery(uri, proj, null,
                null, null);
        if (actualimagecursor == null) {
            img_path = uri.getPath();
        } else {
            int actual_image_column_index = actualimagecursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            actualimagecursor.moveToFirst();
            img_path = actualimagecursor
                    .getString(actual_image_column_index);
        }
        File file = new File(img_path);
        return file;
    }


}