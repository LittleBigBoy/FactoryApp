package com.zhenhaikj.factoryside.mvp.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.Config;
import com.zhenhaikj.factoryside.mvp.activity.AboutUsActivity;
import com.zhenhaikj.factoryside.mvp.activity.AllWorkOrdersActivity;
import com.zhenhaikj.factoryside.mvp.activity.BrandActivity;
import com.zhenhaikj.factoryside.mvp.activity.DetailRecordActivity;
import com.zhenhaikj.factoryside.mvp.activity.ModelActivity;
import com.zhenhaikj.factoryside.mvp.activity.OpinionActivity;
import com.zhenhaikj.factoryside.mvp.activity.PersonalInformationActivity;
import com.zhenhaikj.factoryside.mvp.activity.RechargeActivity;
import com.zhenhaikj.factoryside.mvp.activity.SettingActivity;
import com.zhenhaikj.factoryside.mvp.activity.SubAccountManagementActivity;
import com.zhenhaikj.factoryside.mvp.activity.VerifiedActivity;
import com.zhenhaikj.factoryside.mvp.activity.WalletActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseLazyFragment;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.CompanyInfo;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.Search;
import com.zhenhaikj.factoryside.mvp.bean.UserInfo;
import com.zhenhaikj.factoryside.mvp.contract.MineContract;
import com.zhenhaikj.factoryside.mvp.model.MineModel;
import com.zhenhaikj.factoryside.mvp.presenter.MinePresenter;
import com.zhenhaikj.factoryside.mvp.utils.MyUtils;
import com.zhenhaikj.factoryside.mvp.utils.ZXingUtils;
import com.zhenhaikj.factoryside.mvp.widget.CommonDialog_Home;
import com.zhenhaikj.factoryside.mvp.widget.VerifiedDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MineFragment extends BaseLazyFragment<MinePresenter, MineModel> implements View.OnClickListener, MineContract.View {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.iv_service)
    ImageView mIvService;
    @BindView(R.id.iv_setting)
    ImageView mIvSetting;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.iv_profile_image)
    ImageView mIvProfileImage;
    @BindView(R.id.tv_nickname)
    TextView mTvNickname;
    @BindView(R.id.ll_gift)
    LinearLayout mLlGift;
    @BindView(R.id.my_top)
    LinearLayout mMyTop;
    @BindView(R.id.tv_money)
    TextView mTvMoney;
    @BindView(R.id.tv_recharge)
    TextView mTvRecharge;
    @BindView(R.id.ll_all_order)
    LinearLayout mLlAllOrder;
    @BindView(R.id.ll_to_be_returned)
    LinearLayout mLlToBeReturned;
    @BindView(R.id.ll_to_be_confirmed)
    LinearLayout mLlToBeConfirmed;
    @BindView(R.id.ll_complete)
    LinearLayout mLlComplete;
    @BindView(R.id.ll_my_wallet)
    LinearLayout mLlMyWallet;
    @BindView(R.id.ll_service_phone)
    LinearLayout mLlServicePhone;
    @BindView(R.id.ll_personal_info)
    LinearLayout mLlPersonalInfo;
    @BindView(R.id.ll_sub_account_management)
    LinearLayout mLlSubAccountManagement;
    @BindView(R.id.ll_qr_code)
    LinearLayout mLlQrCode;
    @BindView(R.id.ll_consume)
    LinearLayout mLlConsume;
    @BindView(R.id.ll_add_brand)
    LinearLayout mLlAddBrand;
    @BindView(R.id.ll_feedback)
    LinearLayout mLlFeedback;
    @BindView(R.id.ll_about)
    LinearLayout mLlAbout;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.ll_model_addition)
    LinearLayout mLlModelAddition;
    @BindView(R.id.ll_setting)
    LinearLayout mLlSetting;
    @BindView(R.id.iv_code)
    ImageView mIvCode;
    @BindView(R.id.ll_share)
    LinearLayout mLlShare;

    private String mParam1;
    private String mParam2;
    private Bundle bundle;
    private Intent intent;

    int position = 0;
    private View shareView;
    private AlertDialog shareDialog;
    private String userId;
    private UserInfo.UserInfoDean userInfoDean;
    private int size;
    private ArrayList<String> permissions;
    private PopupWindow mPopupWindow;
    private View popupWindow_view;
    private String filePath;
    private View btn_share_one;
    private View btn_share_two;
    private ShareAction mShareAction;
    private ShareAction mShareAction2;
    private CustomShareListener mShareListener;
    private CustomShareListener mShareListener2;
    private SPUtils spUtils;
    private String userID;
    private ImageView iv_code_one;
    private Button btn_go_to_the_mall;
    private View under_review;
    private Button btnConfirm;
    private AlertDialog underReviewDialog;
    private VerifiedDialog customDialog;
    private CompanyInfo companyDean;
    private View dialog_share;
    private AlertDialog dialogShare;


    public MineFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MineFragment newInstance(String param1, String param2) {
        MineFragment fragment = new MineFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarColor(R.color.red);
        mImmersionBar.fitsSystemWindows(true);
        mImmersionBar.keyboardEnable(true).navigationBarWithKitkatEnable(false).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initData() {
        spUtils = SPUtils.getInstance("token");
        userID = spUtils.getString("userName");
//        Glide.with(mActivity).load(R.drawable.avatar).apply(RequestOptions.circleCropTransform().placeholder(R.drawable.default_avatar).error(R.drawable.default_avatar)).into(mIvProfileImage);
        mRefreshLayout.setEnableLoadMore(false);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.GetUserInfoList(userId, "1");
                mPresenter.GetOrderInfoList("","",userID,"1","999");
                mRefreshLayout.finishRefresh(1000);
            }
        });
        mPresenter.GetUserInfoList(userId, "1");
        mPresenter.GetOrderInfoList("","",userID,"1","999");
//        mPresenter.GetmessageBytype(userId);

        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        UMShareAPI.get(mActivity).setShareConfig(config);
        mShareListener = new CustomShareListener(mActivity);
        mShareListener2 = new CustomShareListener(mActivity);
        /*增加自定义按钮的分享面板*/
        mShareAction = new ShareAction(mActivity).setDisplayList(
                SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN_FAVORITE,
                SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.MORE)
                .addButton("复制文本", "复制文本", "umeng_socialize_copy", "umeng_socialize_copy")
                .addButton("复制链接", "复制链接", "umeng_socialize_copyurl", "umeng_socialize_copyurl")
                .setShareboardclickCallback(new ShareBoardlistener() {
                    @Override
                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                        if (snsPlatform.mShowWord.equals("复制文本")) {
                            Toast.makeText(mActivity, "已复制", Toast.LENGTH_LONG).show();
                        } else if (snsPlatform.mShowWord.equals("复制链接")) {
                            Toast.makeText(mActivity, "已复制", Toast.LENGTH_LONG).show();

                        } else {
                            UMWeb web = new UMWeb("http://admin.xigyu.com/factorysign?phone=" + userID + "&type=6");
                            web.setTitle("西瓜鱼");
                            web.setDescription("注册送西瓜币了！！！！");
                            web.setThumb(new UMImage(mActivity, R.drawable.icon));
                            new ShareAction(mActivity).withMedia(web)
                                    .setPlatform(share_media)
                                    .setCallback(mShareListener)
                                    .share();
                        }
                    }
                });

        mShareAction2 = new ShareAction(mActivity).setDisplayList(
                SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN_FAVORITE,
                SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.MORE)
                .addButton("复制文本", "复制文本", "umeng_socialize_copy", "umeng_socialize_copy")
                .addButton("复制链接", "复制链接", "umeng_socialize_copyurl", "umeng_socialize_copyurl")
                .setShareboardclickCallback(new ShareBoardlistener() {
                    @Override
                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                        if (snsPlatform.mShowWord.equals("复制文本")) {
                            Toast.makeText(mActivity, "已复制", Toast.LENGTH_LONG).show();
                        } else if (snsPlatform.mShowWord.equals("复制链接")) {
                            Toast.makeText(mActivity, "已复制", Toast.LENGTH_LONG).show();

                        } else {
                            UMWeb web = new UMWeb("http://admin.xigyu.com/NewSign?phone=" + userID + "&type=8");
                            web.setTitle("西瓜鱼");
                            web.setDescription("注册送西瓜币了！！！！");
                            web.setThumb(new UMImage(mActivity, R.drawable.icon));
                            new ShareAction(mActivity).withMedia(web)
                                    .setPlatform(share_media)
                                    .setCallback(mShareListener)
                                    .share();
                        }
                    }
                });


    }

    @Override
    protected void initView() {
        SPUtils spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        userInfoDean = new UserInfo.UserInfoDean();

    }

    @Override
    protected void setListener() {
        mIvSetting.setOnClickListener(this);
        mIvService.setOnClickListener(this);
        mLlGift.setOnClickListener(this);
        mTvRecharge.setOnClickListener(this);

        mLlAllOrder.setOnClickListener(this);
        mLlToBeReturned.setOnClickListener(this);
        mLlToBeConfirmed.setOnClickListener(this);
        mLlComplete.setOnClickListener(this);

        mLlMyWallet.setOnClickListener(this);
        mLlServicePhone.setOnClickListener(this);
        mLlPersonalInfo.setOnClickListener(this);
        mLlSubAccountManagement.setOnClickListener(this);
        mLlQrCode.setOnClickListener(this);
        mLlConsume.setOnClickListener(this);
        mLlAddBrand.setOnClickListener(this);
        mLlFeedback.setOnClickListener(this);
        mLlAbout.setOnClickListener(this);
        mIvProfileImage.setOnClickListener(this);
        mLlModelAddition.setOnClickListener(this);
        mLlSetting.setOnClickListener(this);
        mIvCode.setOnClickListener(this);
        mLlShare.setOnClickListener(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {
        if (!"GetUserInfoList".equals(name)) {
            return;
        }
        mPresenter.GetUserInfoList(userId, "1");
        mPresenter.GetOrderInfoList("","",userID,"1","999");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_setting:
            case R.id.iv_setting:
                startActivity(new Intent(mActivity, SettingActivity.class));
                break;
            case R.id.iv_service:
                final CommonDialog_Home dialog = new CommonDialog_Home(getActivity());
                dialog.setMessage("是否拨打电话给客服")
                        //.setImageResId(R.mipmap.ic_launcher)
                        .setTitle("提示")
                        .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                    @Override
                    public void onPositiveClick() {//拨打电话
                        dialog.dismiss();
                        call("tel:" + "4006262365");
                    }

                    @Override
                    public void onNegtiveClick() {//取消
                        dialog.dismiss();
                        // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                    }
                }).show();
                break;
            case R.id.iv_code:
            case R.id.ll_gift:
//                startActivity(new Intent(mActivity,WalletActivity.class));
                shareView = LayoutInflater.from(mActivity).inflate(R.layout.dialog_share, null);
                btn_share_one = shareView.findViewById(R.id.btn_share_one);
                iv_code_one = shareView.findViewById(R.id.iv_code_one);
                btn_go_to_the_mall = shareView.findViewById(R.id.btn_go_to_the_mall);
                Bitmap bitmap = ZXingUtils.createQRImage("http://admin.xigyu.com/NewSign?phone=" + userID + "&type=8", 600, 600, BitmapFactory.decodeResource(getResources(), R.drawable.icon));
                iv_code_one.setImageBitmap(bitmap);
                btn_share_one.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shareDialog.dismiss();
                        mShareAction2.open();
                    }
                });

                btn_go_to_the_mall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openShopApp("com.zhenghaikj.shop");
                        shareDialog.dismiss();
                    }
                });
                shareDialog = new AlertDialog.Builder(mActivity).setView(shareView).create();
                shareDialog.show();
                Window window = shareDialog.getWindow();
                WindowManager.LayoutParams lp = window.getAttributes();
//                Display display = mActivity.getWindowManager().getDefaultDisplay();
//                lp.width = (int) (display.getWidth() * 0.6);
                window.setAttributes(lp);
                window.setBackgroundDrawable(new ColorDrawable());
                break;
            case R.id.ll_share:
                dialog_share = LayoutInflater.from(mActivity).inflate(R.layout.dialog_share, null);
                btn_share_one = dialog_share.findViewById(R.id.btn_share_one);
//                btn_share_two = dialog_share.findViewById(R.id.btn_share_two);
                TextView tv_title = dialog_share.findViewById(R.id.tv_title);
                tv_title.setText("扫描加入西瓜鱼工厂");

                iv_code_one = dialog_share.findViewById(R.id.iv_code_one);
                btn_go_to_the_mall = dialog_share.findViewById(R.id.btn_go_to_the_mall);
                Bitmap bitmap1 = ZXingUtils.createQRImage("http://admin.xigyu.com/factorysign?phone=" + userID + "&type=6", 600, 600, BitmapFactory.decodeResource(getResources(), R.drawable.icon));
                iv_code_one.setImageBitmap(bitmap1);

                btn_share_one.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogShare.dismiss();
                        mShareAction.open();
                    }
                });
                btn_go_to_the_mall.setVisibility(View.GONE);
//                btn_share_two.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialogShare.dismiss();
//                        mShareAction.open();
//                    }
//                });
                dialogShare = new AlertDialog.Builder(mActivity).setView(dialog_share)
                        .create();
                dialogShare.show();
                window = dialogShare.getWindow();
//                window.setContentView(dialog_share);
                WindowManager.LayoutParams lp2 = window.getAttributes();
//                lp.alpha = 0.5f;
                // 也可按屏幕宽高比例进行设置宽高
//                Display display = mActivity.getWindowManager().getDefaultDisplay();
//                lp.width = (int) (display.getWidth() * 0.6);
//                lp.height = dialog_share.getHeight();
//                lp.width = 300;
//                lp.height = 400;

                window.setAttributes(lp2);
//                window.setDimAmount(0.1f);
                window.setBackgroundDrawable(new ColorDrawable());
                break;
            case R.id.tv_recharge:
                startActivity(new Intent(mActivity, RechargeActivity.class));
                break;
            case R.id.ll_all_order:
                if (userInfoDean.getIfAuth() != null) {
                    if (userInfoDean.getIfAuth().equals("1")) {
                        bundle = new Bundle();
                        bundle.putString("title", "所有订单");
                        bundle.putInt("position", 2);
                        intent = new Intent(mActivity, AllWorkOrdersActivity.class);
                        intent.putExtras(bundle);
                        ActivityUtils.startActivity(intent);
                    } else if (userInfoDean.getIfAuth().equals("0")) {
                        showUnderDialog();
                    } else if (userInfoDean.getIfAuth().equals("-1")) {
                        showRejectDialog();
                    } else {
                        showVerifiedDialog();
                    }
                } else {
                    showVerifiedDialog();
                }
                break;
            case R.id.ll_to_be_returned:
                if (userInfoDean.getIfAuth() != null) {
                    if (userInfoDean.getIfAuth().equals("1")) {
                        bundle = new Bundle();
                        bundle.putString("title", "待审核");
                        bundle.putInt("position", 5);
                        intent = new Intent(mActivity, AllWorkOrdersActivity.class);
                        intent.putExtras(bundle);
                        ActivityUtils.startActivity(intent);
                    } else if (userInfoDean.getIfAuth().equals("0")) {
                        showUnderDialog();
                    } else if (userInfoDean.getIfAuth().equals("-1")) {
                        showRejectDialog();
                    } else {
                        showVerifiedDialog();
                    }
                } else {
                    showVerifiedDialog();
                }

                break;
            case R.id.ll_to_be_confirmed:
                if (userInfoDean.getIfAuth() != null) {
                    if (userInfoDean.getIfAuth().equals("1")) {
                        bundle = new Bundle();
                        bundle.putString("title", "待支付");
                        bundle.putInt("position", 7);
                        intent = new Intent(mActivity, AllWorkOrdersActivity.class);
                        intent.putExtras(bundle);
                        ActivityUtils.startActivity(intent);
                    } else if (userInfoDean.getIfAuth().equals("0")) {
                        showUnderDialog();
                    } else if (userInfoDean.getIfAuth().equals("-1")) {
                        showRejectDialog();
                    } else {
                        showVerifiedDialog();
                    }
                } else {
                    showVerifiedDialog();
                }

                break;
            case R.id.ll_complete:
                if (userInfoDean.getIfAuth() != null) {
                    if (userInfoDean.getIfAuth().equals("1")) {
                        bundle = new Bundle();
                        bundle.putString("title", "已完成");
                        bundle.putInt("position", 8);
                        intent = new Intent(mActivity, AllWorkOrdersActivity.class);
                        intent.putExtras(bundle);
                        ActivityUtils.startActivity(intent);
                    } else if (userInfoDean.getIfAuth().equals("0")) {
                        showUnderDialog();
                    } else if (userInfoDean.getIfAuth().equals("-1")) {
                        showRejectDialog();
                    } else {
                        showVerifiedDialog();
                    }
                } else {
                    showVerifiedDialog();
                }

                break;
            case R.id.ll_my_wallet:
                startActivity(new Intent(mActivity, WalletActivity.class));
                break;
            case R.id.ll_service_phone:
                final CommonDialog_Home dialog1 = new CommonDialog_Home(getActivity());
                dialog1.setMessage("是否拨打电话给客服")
                        //.setImageResId(R.mipmap.ic_launcher)
                        .setTitle("提示")
                        .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                    @Override
                    public void onPositiveClick() {//拨打电话
                        dialog1.dismiss();
                        call("tel:" + "4006262365");
                    }

                    @Override
                    public void onNegtiveClick() {//取消
                        dialog1.dismiss();
                        // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                    }
                }).show();
                break;
            case R.id.ll_personal_info:
                startActivity(new Intent(mActivity, PersonalInformationActivity.class));
                break;
            case R.id.ll_sub_account_management:
                startActivity(new Intent(mActivity, SubAccountManagementActivity.class));
                break;
            case R.id.ll_qr_code:
                startActivity(new Intent(mActivity, WalletActivity.class));
                break;
            case R.id.ll_consume:
                Intent intent = new Intent(mActivity, DetailRecordActivity.class);
                intent.putExtra("openwhich", "1");
                startActivity(intent);
                break;
            case R.id.ll_add_brand:
                startActivity(new Intent(mActivity, BrandActivity.class));
                break;
            case R.id.ll_feedback:
                startActivity(new Intent(mActivity, OpinionActivity.class));
                break;
            case R.id.ll_about:
                startActivity(new Intent(mActivity, AboutUsActivity.class));
                break;
            case R.id.iv_profile_image:
                if (requestPermissions()) {
                    showPopupWindow(101, 102);
                } else {
                    requestPermissions(permissions.toArray(new String[permissions.size()]), 10001);
                }
                break;
            case R.id.ll_model_addition:
                startActivity(new Intent(mActivity, ModelActivity.class));
                break;
        }
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

    public void uploadImg(File f) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("img", f.getName(), RequestBody.create(MediaType.parse("img/png"), f));
        builder.addFormDataPart("UserId", userId);
        MultipartBody requestBody = builder.build();
        mPresenter.UploadAvator(requestBody);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        File file = null;
        switch (requestCode) {
            //拍照获取图片
            case 101:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(filePath).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(mIvProfileImage);
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
                    Glide.with(mActivity).load(uri).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(mIvProfileImage);
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


    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().getData().isEmpty()) {
                    return;
                } else {
                    userInfoDean = baseResult.getData().getData().get(0);
                    if (userInfoDean.getAvator() == null) {
                        return;
                    } else {
                        Glide.with(mActivity)
                                .load(Config.HEAD_URL + userInfoDean.getAvator())
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvProfileImage);
                    }
                    if ("1".equals(userInfoDean.getIfAuth())) {
                        mPresenter.GetmessageBytype(userId);
                    }
//                    mTvNickname.setText(userInfoDean.getTrueName());
                    String format = String.format("%.2f", userInfoDean.getTotalMoney() - userInfoDean.getFrozenMoney());

                }

                break;
            case 401:
                break;
        }
    }

    @Override
    public void UploadAvator(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (!baseResult.getData().isItem1()) {

                    Toast.makeText(mActivity, "图片上传失败", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(mActivity, "图片上传成功", Toast.LENGTH_SHORT).show();
                    EventBus.getDefault().post("GetUserInfoList");
                }

                break;

            default:
                Toast.makeText(mActivity, "修改失败", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    @Override
    public void GetmessageBytype(BaseResult<Data<CompanyInfo>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    companyDean = baseResult.getData().getItem2();
                    if ("1".equals(companyDean.getIfAuth())) {
                        mTvNickname.setText(companyDean.getCompanyName());
                    } else {
                        mTvNickname.setText("未设置公司名称");
                    }
                }

                break;
        }
    }

    @Override
    public void GetOrderInfoList(BaseResult<Search> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                if (baseResult!=null){
                    mTvMoney.setText("已发工单数量："+baseResult.getData().getCount());
                }else {
                    return;
                }

                break;
        }
    }


    public static class CustomShareListener implements UMShareListener {
        private Context mContext;

        public CustomShareListener(Context context) {
            mContext = context;
        }

        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {

            if (platform.name().equals("WEIXIN_FAVORITE")) {
                Toast.makeText(mContext, platform + " 收藏成功啦", Toast.LENGTH_SHORT).show();
            } else {
                if (platform != SHARE_MEDIA.MORE && platform != SHARE_MEDIA.SMS
                        && platform != SHARE_MEDIA.EMAIL
                        && platform != SHARE_MEDIA.FLICKR
                        && platform != SHARE_MEDIA.FOURSQUARE
                        && platform != SHARE_MEDIA.TUMBLR
                        && platform != SHARE_MEDIA.POCKET
                        && platform != SHARE_MEDIA.PINTEREST

                        && platform != SHARE_MEDIA.INSTAGRAM
                        && platform != SHARE_MEDIA.GOOGLEPLUS
                        && platform != SHARE_MEDIA.YNOTE
                        && platform != SHARE_MEDIA.EVERNOTE) {
                    Toast.makeText(mContext, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
                }

            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            if (platform != SHARE_MEDIA.MORE && platform != SHARE_MEDIA.SMS
                    && platform != SHARE_MEDIA.EMAIL
                    && platform != SHARE_MEDIA.FLICKR
                    && platform != SHARE_MEDIA.FOURSQUARE
                    && platform != SHARE_MEDIA.TUMBLR
                    && platform != SHARE_MEDIA.POCKET
                    && platform != SHARE_MEDIA.PINTEREST

                    && platform != SHARE_MEDIA.INSTAGRAM
                    && platform != SHARE_MEDIA.GOOGLEPLUS
                    && platform != SHARE_MEDIA.YNOTE
                    && platform != SHARE_MEDIA.EVERNOTE) {
                Toast.makeText(mContext, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();

            }

        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {

            Toast.makeText(mContext, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isInstalled(String packageName) {
        PackageManager manager = mActivity.getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> installedPackages = manager.getInstalledPackages(0);
        if (installedPackages != null) {
            for (PackageInfo info : installedPackages) {
                if (info.packageName.equals(packageName))
                    return true;
            }
        }
        return false;
    }

    private void openShopApp(String packageName) {

        if (isInstalled(packageName)) {
            PackageInfo pi = null;
            try {
                pi = getActivity().getPackageManager().getPackageInfo(packageName, 0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
            resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            resolveIntent.setPackage(pi.packageName);

            List<ResolveInfo> apps = getActivity().getPackageManager().queryIntentActivities(resolveIntent, 0);

            ResolveInfo ri = apps.iterator().next();
            if (ri != null) {
                packageName = ri.activityInfo.packageName;
                String className = ri.activityInfo.name;

                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);

                ComponentName cn = new ComponentName(packageName, className);
                intent.setComponent(cn);
                startActivity(intent);
            }


        } else {

            Toast.makeText(mActivity, "未安装商城app请前往下载安装", Toast.LENGTH_SHORT).show();
        }
    }

    public void showRejectDialog() {
        under_review = LayoutInflater.from(mActivity).inflate(R.layout.dialog_audit_failure, null);
        TextView content = under_review.findViewById(R.id.tv_content);
        content.setText(userInfoDean.getAuthMessage() + ",有疑问请咨询客服电话。");
        btnConfirm = under_review.findViewById(R.id.btn_confirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                underReviewDialog.dismiss();
                startActivity(new Intent(mActivity, VerifiedActivity.class));
            }
        });
        underReviewDialog = new AlertDialog.Builder(mActivity).setView(under_review).create();
        underReviewDialog.show();
    }

    public void showUnderDialog() {
        under_review = LayoutInflater.from(mActivity).inflate(R.layout.dialog_under_review, null);
        btnConfirm = under_review.findViewById(R.id.btn_confirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                underReviewDialog.dismiss();
            }
        });
        underReviewDialog = new AlertDialog.Builder(mActivity).setView(under_review).create();
        underReviewDialog.show();
    }

    public void showVerifiedDialog() {
        customDialog = new VerifiedDialog(getContext());
        customDialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        customDialog.setTitle("实名认证");
        customDialog.show();
        customDialog.setYesOnclickListener("确定", new VerifiedDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                //Toast.makeText(getContext(), "点击了--去认证--按钮", Toast.LENGTH_LONG).show();
                customDialog.dismiss();
                startActivity(new Intent(mActivity, VerifiedActivity.class));
            }
        });

        customDialog.setNoOnclickListener("取消", new VerifiedDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                //Toast.makeText(getContext(), "点击了--再想想--按钮", Toast.LENGTH_LONG).show();
                customDialog.dismiss();
            }
        });

        customDialog.setNoOnclickListener("取消", new VerifiedDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                // Toast.makeText(getContext(), "点击了--关闭-按钮", Toast.LENGTH_LONG).show();
                customDialog.dismiss();
            }
        });
    }

}
