package com.zhenhaikj.factoryside.mvp.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alipay.sdk.app.PayTask;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.Config;
import com.zhenhaikj.factoryside.mvp.activity.AllWorkOrdersActivity;
import com.zhenhaikj.factoryside.mvp.activity.CustomerServiceActivity;
import com.zhenhaikj.factoryside.mvp.activity.ExcelOrderActivity;
import com.zhenhaikj.factoryside.mvp.activity.HomeMaintenanceActivity2;
import com.zhenhaikj.factoryside.mvp.activity.PersonalInformationActivity;
import com.zhenhaikj.factoryside.mvp.activity.RechargeActivity;
import com.zhenhaikj.factoryside.mvp.activity.VerifiedActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseLazyFragment;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.CompanyInfo;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.HomeData;
import com.zhenhaikj.factoryside.mvp.bean.PayResult;
import com.zhenhaikj.factoryside.mvp.bean.Search;
import com.zhenhaikj.factoryside.mvp.bean.UserInfo;
import com.zhenhaikj.factoryside.mvp.bean.WXpayInfo;
import com.zhenhaikj.factoryside.mvp.contract.HomeContract;
import com.zhenhaikj.factoryside.mvp.model.HomeModel;
import com.zhenhaikj.factoryside.mvp.presenter.HomePresenter;
import com.zhenhaikj.factoryside.mvp.utils.GlideImageLoader;
import com.zhenhaikj.factoryside.mvp.utils.ZXingUtils;
import com.zhenhaikj.factoryside.mvp.widget.CommonDialog_Home;
import com.zhenhaikj.factoryside.mvp.widget.GlideCircleWithBorder;
import com.zhenhaikj.factoryside.mvp.widget.VerifiedDialog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class HomeFragment extends BaseLazyFragment<HomePresenter, HomeModel> implements HomeContract.View, View.OnClickListener {
    private static final String TAG = "V3HomeFragment";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.iv_service)
    ImageView mIvService;
    @BindView(R.id.iv_gray_message)
    ImageView mIvGrayMessage;
    @BindView(R.id.iv_code)
    ImageView mIvCode;
    @BindView(R.id.ll_code)
    LinearLayout mLlCode;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.banner_home)
    Banner mBannerHome;
    @BindView(R.id.iv_avatar)
    ImageView mIvAvatar;
    @BindView(R.id.tv_factory_name)
    TextView mTvFactoryName;
    @BindView(R.id.tv_verified)
    TextView mTvVerified;
    @BindView(R.id.iv_verified)
    ImageView mIvVerified;
    @BindView(R.id.ll_verified)
    LinearLayout mLlVerified;
    @BindView(R.id.tv_finish)
    TextView mTvFinish;
    @BindView(R.id.ll_finsh)
    LinearLayout mLlFinsh;
    @BindView(R.id.tv_complaint)
    TextView mTvComplaint;
    @BindView(R.id.ll_complaint)
    LinearLayout mLlComplaint;
    @BindView(R.id.tv_announcement)
    TextView mTvAnnouncement;
    @BindView(R.id.rv_main_menu)
    RecyclerView mRvMainMenu;
    @BindView(R.id.iv_more)
    ImageView mIvMore;
    @BindView(R.id.rv_common_menu)
    RecyclerView mRvCommonMenu;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;


    private String mParam1;
    private String mParam2;
    private ArrayList<MenuItem2> mMainMenus;
    private ArrayList<MenuItem> mCommonMenus;
    private UserInfo.UserInfoDean userInfoDean;

    private Integer[] icons = new Integer[]{
            R.mipmap.one_bg, R.mipmap.two_bg, R.mipmap.three_bg, R.mipmap.four_bg, R.drawable.yuanchengfei, R.drawable.suoyou1,
            R.drawable.daijiedan, R.drawable.yijiedan, R.drawable.daishenhe, R.drawable.daijijian, R.drawable.daizhifu, R.drawable.yiwanjie, R.drawable.zhibao
    };

    private Integer[] icons_content = new Integer[]{
            R.mipmap.one, R.mipmap.two, R.mipmap.three, R.mipmap.four,
            R.drawable.cost_change, R.drawable.waiting_order, R.drawable.waiting_order, R.drawable.return_order_processing, R.drawable.finished, R.drawable.accessory_list, R.drawable.to_be_paid,
            R.drawable.yuanchengfei, R.drawable.warranty, R.drawable.undone, R.drawable.leave_a_message
    };
    private String[] names = new String[]{
            "发布安装", "发布维修", "发布送修", "批量发单", "远程费审核", "所有工单",
            "待接单", "已接单", "待审核", "待寄件", "待支付", "已完结", "质保单", "退单处理"
    };
    private MenuAdapter2 mMainAdapter;
    private MenuAdapter mCommonAdapter;
    private Intent intent;
    private SPUtils spUtils;
    private String userId;
    private View under_review;
    private TextView tv_cancel;
    private TextView tv_confirm;
    private TextView tv_margin;
    private LinearLayout ll_alipay;
    private LinearLayout ll_wxpay;
    private ImageView iv_aplipay;
    private ImageView iv_wechat;
    private LinearLayout ll_choose1;
    private LinearLayout ll_choose2;
    private LinearLayout ll_choose3;
    private LinearLayout ll_choose4;
    private ImageView cb1;
    private ImageView cb2;
    private ImageView cb3;
    private ImageView cb4;
    private int payway = 1;
    private AlertDialog underReviewDialog;
    private Window window;
    private WXpayInfo wXpayInfo;
    private String orderinfo;
    private IWXAPI api;
    private LinearLayout ll_choose5;
    private LinearLayout ll_choose6;
    private ImageView cb5;
    private ImageView cb6;
    private View shareView;
    private Button btn_share_one;
    private ImageView iv_code_one;
    private Button btn_go_to_the_mall;
    private AlertDialog shareDialog;
    private ShareAction mShareAction;
    private CustomShareListener mShareListener;
    private VerifiedDialog customDialog;
    private Button btnConfirm;
    private Button btn_verified_update;
    private CompanyInfo companyDean;
    private boolean flag;
    private Bundle bundle;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment V3HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        flag = spUtils.getBoolean("flag", false);
//        mIvEye.setSelected(flag);
        api = WXAPIFactory.createWXAPI(mActivity, "wxd6509c9c912f0015");
        // 将该app注册到微信
        api.registerApp("wxd6509c9c912f0015");
        mPresenter.GetUserInfoList(userId, "1");
        mPresenter.GetRemainMoney(userId);
        mPresenter.GetUserOrderNum(userId);
//        mPresenter.GetmessageBytype(userId);
        List<Integer> images = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            images.add(R.drawable.home_banner);
        }
        mBannerHome.setImageLoader(new GlideImageLoader());
        mBannerHome.setImages(images);
        mBannerHome.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        mBannerHome.setIndicatorGravity(BannerConfig.CENTER);
        mBannerHome.start();
        mMainMenus = new ArrayList<>();
        mCommonMenus = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            mMainMenus.add(new MenuItem2(icons_content[i], icons[i], names[i]));
        }
        for (int i = 4; i < 12; i++) {
            mCommonMenus.add(new MenuItem(icons[i], names[i]));
        }
        mMainAdapter = new MenuAdapter2(R.layout.menu_item2, mMainMenus);
        mCommonAdapter = new MenuAdapter(R.layout.menu_item, mCommonMenus);
        mRvCommonMenu.setLayoutManager(new GridLayoutManager(mActivity, 4));
//        mRvCommonMenu.addItemDecoration(new RecyclerViewDivider(mActivity, LinearLayoutManager.HORIZONTAL, 2, Color.parseColor("#F2F2F2")));
        mRvMainMenu.setLayoutManager(new GridLayoutManager(mActivity, 2));
//        mRvMainMenu.addItemDecoration(new RecyclerViewDivider(mActivity, LinearLayoutManager.HORIZONTAL, 2, Color.parseColor("#F2F2F2")));
        mRvMainMenu.setAdapter(mMainAdapter);
        mRvCommonMenu.setAdapter(mCommonAdapter);
        mMainAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
//                        startActivity(new Intent(mActivity, HomeInstallationActivity.class));
                        if (userInfoDean.getIfAuth() != null) {
                            if (userInfoDean.getIfAuth().equals("1")) {
                                intent = new Intent(mActivity, HomeMaintenanceActivity2.class);
                                intent.putExtra("type", 0);
                                startActivity(intent);
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
                    case 1:
                        if (userInfoDean.getIfAuth() != null) {
                            if (userInfoDean.getIfAuth().equals("1")) {
                                intent = new Intent(mActivity, HomeMaintenanceActivity2.class);
                                intent.putExtra("type", 1);
                                startActivity(intent);
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
                    case 2:
                        if (userInfoDean.getIfAuth() != null) {
                            if (userInfoDean.getIfAuth().equals("1")) {
                                startActivity(new Intent(mActivity, CustomerServiceActivity.class));
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
                    case 3:
                        if (userInfoDean.getIfAuth() != null) {
                            if (userInfoDean.getIfAuth().equals("1")) {
                                // startActivity(new Intent(mActivity, BatchOrderActivity.class));
                                startActivity(new Intent(mActivity, ExcelOrderActivity.class));

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
                }
            }
        });
        mCommonAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (userInfoDean.getIfAuth() != null) {
                    if (userInfoDean.getIfAuth().equals("1")) {
                        if (position <= 3) {
                            bundle = new Bundle();
                            bundle.putString("title", mCommonMenus.get(position).getName());
                            bundle.putInt("position", position);
                        } else if (position >= 4) {
                            bundle = new Bundle();
                            bundle.putString("title", mCommonMenus.get(position).getName());
                            bundle.putInt("position", position + 1);
                        }
                        Intent intent = new Intent(mActivity, AllWorkOrdersActivity.class);
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

            }
        });
        mRefreshLayout.setEnableLoadMore(false);
//        getHomeData();
//        mPresenter.getData("1");
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
//                getHomeData();
//                mPresenter.getData("1");
                mPresenter.GetUserInfoList(userId, "1");
                mPresenter.GetRemainMoney(userId);
                mPresenter.GetUserOrderNum(userId);
                mRefreshLayout.finishRefresh(1000);
            }
        });


        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        UMShareAPI.get(mActivity).setShareConfig(config);
        mShareListener = new CustomShareListener(mActivity);
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
                            UMWeb web = new UMWeb("http://admin.xigyu.com/NewSign?phone=" + userId + "&type=8");
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

    }

    @Override
    protected void setListener() {
//        mTvPayTheDeposit.setOnClickListener(this);
        mIvAvatar.setOnClickListener(this);
        mIvService.setOnClickListener(this);
        mIvCode.setOnClickListener(this);
//        mTvRecharge.setOnClickListener(this);
        mLlVerified.setOnClickListener(this);
//        mIvEye.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_recharge:
                startActivity(new Intent(mActivity, RechargeActivity.class));
                break;
            case R.id.tv_pay_the_deposit:
                deposit();
                break;
            case R.id.iv_avatar:
                startActivity(new Intent(mActivity, PersonalInformationActivity.class));
                break;
            case R.id.iv_code:
//                startActivity(new Intent(mActivity,WalletActivity.class));
                shareView = LayoutInflater.from(mActivity).inflate(R.layout.dialog_share, null);
                btn_share_one = shareView.findViewById(R.id.btn_share_one);
                iv_code_one = shareView.findViewById(R.id.iv_code_one);
                btn_go_to_the_mall = shareView.findViewById(R.id.btn_go_to_the_mall);
                Bitmap bitmap = ZXingUtils.createQRImage("http://admin.xigyu.com/NewSign?phone=" + userId + "&type=8", 600, 600, BitmapFactory.decodeResource(getResources(), R.drawable.icon));
                iv_code_one.setImageBitmap(bitmap);
                btn_share_one.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shareDialog.dismiss();
                        mShareAction.open();
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
            case R.id.ll_verified:
                if (userInfoDean.getIfAuth() != null) {
                    if (userInfoDean.getIfAuth().equals("1")) {
                        under_review = LayoutInflater.from(mActivity).inflate(R.layout.dialog_successful_review, null);
                        btnConfirm = under_review.findViewById(R.id.btn_confirm);
                        btn_verified_update = under_review.findViewById(R.id.btn_verified_update);
                        btnConfirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                underReviewDialog.dismiss();
                            }
                        });
                        btn_verified_update.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                underReviewDialog.dismiss();
//                                startActivity(new Intent(mActivity, VerifiedUpdateActivity.class));
                            }
                        });
                        underReviewDialog = new AlertDialog.Builder(mActivity).setView(under_review).create();
                        underReviewDialog.show();
                    } else if (userInfoDean.getIfAuth().equals("0")) {
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
                    } else if (userInfoDean.getIfAuth().equals("-1")) {
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
                    } else {
                        showVerifiedDialog();
                    }
                } else {
                    showVerifiedDialog();
                }
                break;
//            case R.id.iv_eye:
//                if (!flag) {
//                    flag = true;
//                    mTvMoney.setText("****");//钱包余额
////                    mTvWatermelonBalance.setText("****");//西瓜币
//                } else {
//                    flag = false;
//                    String format = String.format("%.2f", userInfoDean.getTotalMoney() - userInfoDean.getFrozenMoney());
//                    mTvMoney.setText(format + "");//钱包余额
////                    mTvWatermelonBalance.setText("¥" + userInfo.getCon() + "");//西瓜币
//                }
//                mIvEye.setSelected(flag);
//                spUtils.put("flag", flag);
//                break;
        }
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

    public void deposit() {
        under_review = LayoutInflater.from(mActivity).inflate(R.layout.dialog_deposit, null);
        tv_cancel = under_review.findViewById(R.id.tv_cancel);
        tv_confirm = under_review.findViewById(R.id.tv_confirm);
        tv_margin = under_review.findViewById(R.id.tv_margin);
        ll_alipay = under_review.findViewById(R.id.ll_alipay);
        ll_wxpay = under_review.findViewById(R.id.ll_wxpay);
        iv_aplipay = under_review.findViewById(R.id.iv_aplipay);
        iv_wechat = under_review.findViewById(R.id.iv_wechat);
        iv_aplipay.setSelected(true);
        ll_choose1 = under_review.findViewById(R.id.ll_choose1);
        ll_choose2 = under_review.findViewById(R.id.ll_choose2);
        ll_choose3 = under_review.findViewById(R.id.ll_choose3);
        ll_choose4 = under_review.findViewById(R.id.ll_choose4);
        ll_choose5 = under_review.findViewById(R.id.ll_choose5);
        ll_choose6 = under_review.findViewById(R.id.ll_choose6);
        cb1 = under_review.findViewById(R.id.cb1);
        cb2 = under_review.findViewById(R.id.cb2);
        cb3 = under_review.findViewById(R.id.cb3);
        cb4 = under_review.findViewById(R.id.cb4);
        cb5 = under_review.findViewById(R.id.cb5);
        cb6 = under_review.findViewById(R.id.cb6);
        cb2.setSelected(true);
        tv_margin.setText("1000");
        ll_alipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payway = 1;
                iv_aplipay.setSelected(true);
                iv_wechat.setSelected(false);
            }
        });

        ll_wxpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payway = 2;
                iv_aplipay.setSelected(false);
                iv_wechat.setSelected(true);
            }
        });

        ll_choose1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cb1.setSelected(true);
                cb2.setSelected(false);
                cb3.setSelected(false);
                cb4.setSelected(false);
                cb5.setSelected(false);
                cb6.setSelected(false);
                tv_margin.setText("500");
            }
        });
        ll_choose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cb1.setSelected(false);
                cb2.setSelected(true);
                cb3.setSelected(false);
                cb4.setSelected(false);
                cb5.setSelected(false);
                cb6.setSelected(false);
                tv_margin.setText("1000");
            }
        });
        ll_choose3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cb1.setSelected(false);
                cb2.setSelected(false);
                cb3.setSelected(true);
                cb4.setSelected(false);
                cb5.setSelected(false);
                cb6.setSelected(false);
                tv_margin.setText("1500");
            }
        });
        ll_choose4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cb1.setSelected(false);
                cb2.setSelected(false);
                cb3.setSelected(false);
                cb4.setSelected(true);
                cb5.setSelected(false);
                cb6.setSelected(false);
                tv_margin.setText("3000");
            }
        });
        ll_choose5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cb1.setSelected(false);
                cb2.setSelected(false);
                cb3.setSelected(false);
                cb4.setSelected(false);
                cb5.setSelected(true);
                cb6.setSelected(false);
                tv_margin.setText("5000");
            }
        });
        ll_choose6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cb1.setSelected(false);
                cb2.setSelected(false);
                cb3.setSelected(false);
                cb4.setSelected(false);
                cb5.setSelected(false);
                cb6.setSelected(true);
                tv_margin.setText("10000");
            }
        });


        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                underReviewDialog.dismiss();
            }
        });


//        String value = tv_margin.getText().toString();
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (value ==null||"0".equals(value)){
//                    ToastUtils.showShort("请选择或输入充值金额");
//                    return;
//                }
                switch (payway) {
                    case 1:
                        mPresenter.GetOrderStr(userId, tv_margin.getText().toString());
//                        alipay();
                        break;
                    case 2:
                        mPresenter.GetWXOrderStr(userId, tv_margin.getText().toString());
//                        WXpay();
                        break;
                }
            }
        });
        underReviewDialog = new AlertDialog.Builder(mActivity).setView(under_review).create();
        underReviewDialog.show();
        window = underReviewDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        window.setAttributes(lp);
        window.setBackgroundDrawable(new ColorDrawable());

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {
        if (!"GetUserInfoList".equals(name)) {
            return;
        }
        if ("money".equals(name)) {
            mPresenter.GetUserInfoList(userId, "1");
        }
        mPresenter.GetUserInfoList(userId, "1");
    }

    @Override
    public void success(BaseResult<HomeData> baseResult) {

    }

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().getData().isEmpty()) {
                    return;
                } else {
                    userInfoDean = baseResult.getData().getData().get(0);
                    /*设置实名认证状态*/
                    if (userInfoDean.getIfAuth() == null) {
                        mTvVerified.setText("未实名认证");
//                        mImg_un_certification.setVisibility(View.VISIBLE);
//                        mImgCertification.setVisibility(View.INVISIBLE);
//                        showVerifiedDialog();

                    } else if (userInfoDean.getIfAuth().equals("0")) {
                        mTvVerified.setText("审核中");
                        mTvVerified.setTextColor(Color.RED);
//                        mImg_un_certification.setVisibility(View.VISIBLE);
//                        mImgCertification.setVisibility(View.INVISIBLE);

                    } else if (userInfoDean.getIfAuth().equals("-1")) {
                        mTvVerified.setText("审核不通过");
                        mTvVerified.setTextColor(Color.RED);
//                        mImg_un_certification.setVisibility(View.VISIBLE);
//                        mImgCertification.setVisibility(View.INVISIBLE);

                    } else if (userInfoDean.getIfAuth().equals("1")) {
                        mTvVerified.setText("已实名认证");
                        mTvVerified.setTextColor(Color.BLACK);
//                        mImg_un_certification.setVisibility(View.INVISIBLE);
//                        mImgCertification.setVisibility(View.VISIBLE);
                    } else {

                        return;
                    }
                    if (userInfoDean.getAvator() == null) {
                        return;
                    } else {
                        RequestOptions myOptions = new RequestOptions().transform(new GlideCircleWithBorder(mActivity, 2, Color.parseColor("#DCDCDC")));
                        Glide.with(mActivity)
                                .load(Config.HEAD_URL + userInfoDean.getAvator())
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .apply(myOptions)
                                .into(mIvAvatar);
                    }
//                    mTvFactoryName.setText(userInfoDean.getTrueName());
                    String format = String.format("%.2f", userInfoDean.getTotalMoney() - userInfoDean.getFrozenMoney());
//                    mTvMoney.setText(format);
                    mTvComplaint.setText(userInfoDean.getServiceComplaintNum());
                    if ("1".equals(userInfoDean.getIfAuth())) {
                        mPresenter.GetmessageBytype(userId);
                    }
                }
                break;
        }
    }

    @Override
    public void GetOrderStr(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    orderinfo = baseResult.getData().getItem2();
                    if (!"".equals(orderinfo)) {
                        alipay();
                    }
                } else {
                    ToastUtils.showShort("获取支付信息失败！");
                }
                break;
            default:
                ToastUtils.showShort("获取支付信息失败！");
                break;
        }
    }

    @Override
    public void GetWXOrderStr(BaseResult<Data<WXpayInfo>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    wXpayInfo = baseResult.getData().getItem2();
                    if (wXpayInfo != null) {
                        WXpay();
                    }
                } else {
                    ToastUtils.showShort("获取支付信息失败！");
                }
                break;
            default:
                ToastUtils.showShort("获取支付信息失败！");
                break;
        }
    }

    @Override
    public void WXNotifyManual(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void GetmessageBytype(BaseResult<Data<CompanyInfo>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    companyDean = baseResult.getData().getItem2();
                    if ("1".equals(companyDean.getIfAuth())) {
                        mTvFactoryName.setText(companyDean.getCompanyName());
                    } else {
                        mTvFactoryName.setVisibility(View.GONE);
                    }
                } else {
                    mTvFactoryName.setVisibility(View.GONE);
                }

                break;
        }
    }

    @Override
    public void GetRemainMoney(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
//                mTvMoney.setText(baseResult.getData().getItem2());
                break;
        }
    }

    @Override
    public void GetUserOrderNum(BaseResult<Search> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult != null) {
//                    mTvMoney.setText("已发工单数量：" + baseResult.getData().getCount());
//                    mTvIssuedAmount.setText(baseResult.getData().getCount());
                    mTvFinish.setText(baseResult.getData().getCount());
                } else {
                    return;
                }

                break;
        }
    }


    /**
     * 支付宝支付业务
     */
    public void alipay() {

        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo的获取必须来自服务端；
         */
//        orderinfo = "app_id=2015052600090779&biz_content=%7B%22timeout_express%22%3A%2230m%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22total_amount%22%3A%220.01%22%2C%22subject%22%3A%221%22%2C%22body%22%3A%22%E6%88%91%E6%98%AF%E6%B5%8B%E8%AF%95%E6%95%B0%E6%8D%AE%22%2C%22out_trade_no%22%3A%22IQJZSRC1YMQB5HU%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fdomain.merchant.com%2Fpayment_notify&sign_type=RSA2&timestamp=2016-08-25%2020%3A26%3A31&version=1.0&sign=cYmuUnKi5QdBsoZEAbMXVMmRWjsuUj%2By48A2DvWAVVBuYkiBj13CFDHu2vZQvmOfkjE0YqCUQE04kqm9Xg3tIX8tPeIGIFtsIyp%2FM45w1ZsDOiduBbduGfRo1XRsvAyVAv2hCrBLLrDI5Vi7uZZ77Lo5J0PpUUWwyQGt0M4cj8g%3D";

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(mActivity);
                Map<String, String> result = alipay.payV2(orderinfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = 0;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * 微信支付
     */
    public void WXpay() {
        PayReq req = new PayReq();
        req.appId = wXpayInfo.getAppid();
        req.partnerId = wXpayInfo.getPartnerid();
        req.prepayId = wXpayInfo.getPrepayid();
        req.nonceStr = wXpayInfo.getNoncestr();
        req.timeStamp = wXpayInfo.getTimestamp();
        req.packageValue = wXpayInfo.getPackageX();
        req.sign = wXpayInfo.getSign();
        //req.extData			= "app data"; // optional
        api.sendReq(req);
    }

    /**
     * 支付宝支付结果回调
     */
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        ToastUtils.showShort("支付成功");
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        ToastUtils.showShort("支付失败");
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

    /**
     * 微信支付结果
     *
     * @param resp
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(BaseResp resp) {
        switch (resp.errCode) {
            case 0:
                mPresenter.WXNotifyManual(wXpayInfo.getOut_trade_no());
                ToastUtils.showShort("支付成功");
                break;
            case -1:
                ToastUtils.showShort("支付出错");
                break;
            case -2:
                ToastUtils.showShort("支付取消");
                break;
        }
    }


    public class MenuItem {
        Integer icon;
        String name;

        public MenuItem(Integer icon, String name) {
            this.icon = icon;
            this.name = name;
        }

        public Integer getIcon() {
            return icon;
        }

        public void setIcon(Integer icon) {
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public class MenuItem2 {
        Integer icon;
        String name;
        Integer icon_content;

        public MenuItem2(Integer icon, Integer icon_content, String name) {
            this.icon = icon;
            this.name = name;
            this.icon_content = icon_content;
        }

        public Integer getIcon() {
            return icon;
        }

        public void setIcon(Integer icon) {
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getIcon_content() {
            return icon_content;
        }

        public void setIcon_content(Integer icon_content) {
            this.icon_content = icon_content;
        }
    }

    public class MenuAdapter extends BaseQuickAdapter<MenuItem, BaseViewHolder> {
        public MenuAdapter(int layoutResId, List<MenuItem> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, MenuItem item) {
            // 加载网络图片
            Glide.with(mContext).load(item.getIcon()).into((ImageView) helper.getView(R.id.icon));
            helper.setText(R.id.txt_content, item.getName());
        }
    }

    public class MenuAdapter2 extends BaseQuickAdapter<MenuItem2, BaseViewHolder> {
        public MenuAdapter2(int layoutResId, List<MenuItem2> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, MenuItem2 item) {
            // 加载网络图片
            Glide.with(mContext).load(item.getIcon_content()).into((ImageView) helper.getView(R.id.icon));
            Glide.with(mContext).load(item.getIcon()).into((ImageView) helper.getView(R.id.iv_content));
            helper.setText(R.id.txt_content, item.getName());
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

}
