package com.zhenhaikj.factoryside.mvp.v3.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.Config;
import com.zhenhaikj.factoryside.mvp.activity.AllWorkOrdersActivity;
import com.zhenhaikj.factoryside.mvp.activity.CustomerServiceActivity;
import com.zhenhaikj.factoryside.mvp.activity.ExcelOrderActivity;
import com.zhenhaikj.factoryside.mvp.activity.HomeMaintenanceActivity2;
import com.zhenhaikj.factoryside.mvp.activity.VerifiedActivity;
import com.zhenhaikj.factoryside.mvp.activity.WebActivity2;
import com.zhenhaikj.factoryside.mvp.base.BaseLazyFragment;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Article;
import com.zhenhaikj.factoryside.mvp.bean.CompanyInfo;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.FactoryNavigationBarNumber;
import com.zhenhaikj.factoryside.mvp.bean.UserInfo;
import com.zhenhaikj.factoryside.mvp.v3.activity.MessageActivity;
import com.zhenhaikj.factoryside.mvp.v3.mvp.contract.HomeContract;
import com.zhenhaikj.factoryside.mvp.v3.mvp.model.HomeModel;
import com.zhenhaikj.factoryside.mvp.v3.mvp.presenter.HomePresenter;
import com.zhenhaikj.factoryside.mvp.widget.GlideCircleWithBorder;
import com.zhenhaikj.factoryside.mvp.widget.SwitchView;
import com.zhenhaikj.factoryside.mvp.widget.VerifiedDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class V3HomeFragment extends BaseLazyFragment<HomePresenter, HomeModel> implements HomeContract.View, View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.iv_red)
    ImageView mIvRed;
    @BindView(R.id.ll_message)
    LinearLayout mLlMessage;
    @BindView(R.id.scrolltv)
    SwitchView mScrolltv;
    @BindView(R.id.ll_switch)
    LinearLayout mLlSwitch;
    @BindView(R.id.rv_main_menu)
    RecyclerView mRvMainMenu;
    @BindView(R.id.iv_more)
    ImageView mIvMore;
    @BindView(R.id.rv_common_menu)
    RecyclerView mRvCommonMenu;
    @BindView(R.id.iv_avatar)
    ImageView mIvAvatar;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.tv_pending_review)
    TextView mTvPendingReview;
    @BindView(R.id.ll_pending_review)
    LinearLayout mLlPendingReview;
    @BindView(R.id.tv_pending)
    TextView mTvPending;
    @BindView(R.id.ll_pending)
    LinearLayout mLlPending;
    @BindView(R.id.tv_paid)
    TextView mTvPaid;
    @BindView(R.id.ll_paid)
    LinearLayout mLlPaid;
    @BindView(R.id.tv_completed)
    TextView mTvCompleted;
    @BindView(R.id.ll_completed)
    LinearLayout mLlCompleted;
    @BindView(R.id.tv_pending_orders)
    TextView mTvPendingOrders;
    @BindView(R.id.ll_pending_orders)
    LinearLayout mLlPendingOrders;
    @BindView(R.id.tv_received_work_order)
    TextView mTvReceivedWorkOrder;
    @BindView(R.id.ll_received_work_order)
    LinearLayout mLlReceivedWorkOrder;
    @BindView(R.id.tv_star_ticket)
    TextView mTvStarTicket;
    @BindView(R.id.ll_star_ticket)
    LinearLayout mLlStarTicket;
    @BindView(R.id.tv_returned)
    TextView mTvReturned;
    @BindView(R.id.ll_returned)
    LinearLayout mLlReturned;
    @BindView(R.id.tv_warranty_work_order)
    TextView mTvWarrantyWorkOrder;
    @BindView(R.id.ll_warranty_work_order)
    LinearLayout mLlWarrantyWorkOrder;
    @BindView(R.id.tv_returned_work_order)
    TextView mTvReturnedWorkOrder;
    @BindView(R.id.ll_returned_work_order)
    LinearLayout mLlReturnedWorkOrder;
    @BindView(R.id.tv_close)
    TextView mTvClose;
    @BindView(R.id.ll_close)
    LinearLayout mLlClose;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private String mParam1;
    private String mParam2;
    private List<MenuItem2> mMainMenus = new ArrayList<>();
    private ArrayList<MenuItem> mCommonMenus = new ArrayList<>();
    private Integer[] icons = new Integer[]{
            R.mipmap.one_bg, R.mipmap.two_bg, R.drawable.yuanchengfei, R.drawable.suoyou1,
            R.drawable.daijiedan, R.drawable.yijiedan, R.drawable.daishenhe, R.drawable.daijijian, R.drawable.daizhifu, R.drawable.yiwanjie, R.drawable.zhibao
    };

    private Integer[] icons_content = new Integer[]{
            R.mipmap.one, R.mipmap.two,
            R.drawable.waiting_order, R.drawable.finished, R.drawable.accessory_list, R.drawable.to_be_paid,
            R.drawable.yuanchengfei, R.drawable.undone, R.drawable.leave_a_message
    };
    private String[] names = new String[]{
            "发布安装", "发布维修", "待接单", "待审核", "待寄件", "待支付", "质保单", "退单处理"
    };
    private MenuAdapter2 mMainAdapter;
    private Intent intent;
    private SPUtils spUtils;
    private String userId;
    private UserInfo.UserInfoDean userInfoDean;
    private View under_review;
    private AlertDialog underReviewDialog;
    private VerifiedDialog customDialog;
    private List<Article.DataBean> datalist;
    private int i = 0;
    private MenuAdapter mCommonAdapter;
    private Bundle bundle;
    private CompanyInfo companyDean;
    private FactoryNavigationBarNumber barNumber;

    public static V3HomeFragment newInstance(String param1, String param2) {
        V3HomeFragment fragment = new V3HomeFragment();
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
        mImmersionBar.statusBarColor(R.color.white);
        mImmersionBar.fitsSystemWindows(true);
        mImmersionBar.keyboardEnable(true).navigationBarWithKitkatEnable(false).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.v3_fragment_home;
    }

    @Override
    protected void initData() {
        spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        mPresenter.GetUserInfoList(userId, "1");
        mPresenter.GetListCategoryContentByCategoryID("3", "1", "999");
        mPresenter.FactoryNavigationBarNumber(userId, "5", "1", "999");
        mPresenter.messgIsOrNo(userId, "1", "1");
        for (int i = 0; i < 2; i++) {
            mMainMenus.add(new MenuItem2(icons_content[i], icons[i], names[i]));
        }
        mMainAdapter = new MenuAdapter2(R.layout.menu_item2, mMainMenus);
        mRvMainMenu.setLayoutManager(new GridLayoutManager(mActivity, 2));
        mRvMainMenu.setAdapter(mMainAdapter);
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

        for (int i = 2; i < 6; i++) {
            mCommonMenus.add(new MenuItem(icons[i], names[i]));
        }
        mCommonAdapter = new MenuAdapter(R.layout.menu_item, mCommonMenus);
        mRvCommonMenu.setLayoutManager(new GridLayoutManager(mActivity, 4));
        mRvCommonMenu.setAdapter(mCommonAdapter);
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
    }

    @Override
    protected void initView() {
        mRefreshLayout.setEnableLoadMore(false);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.GetUserInfoList(userId, "1");
                mPresenter.GetListCategoryContentByCategoryID("3", "1", "999");
                mPresenter.FactoryNavigationBarNumber(userId, "5", "1", "999");
                mPresenter.messgIsOrNo(userId, "1", "1");
                mRefreshLayout.finishRefresh(1000);
            }
        });

    }

    @Override
    protected void setListener() {
        mLlPending.setOnClickListener(this);
        mLlMessage.setOnClickListener(this);
        mLlPendingReview.setOnClickListener(this);
        mLlClose.setOnClickListener(this);
        mLlCompleted.setOnClickListener(this);
        mLlPaid.setOnClickListener(this);
        mLlPendingOrders.setOnClickListener(this);
        mLlReceivedWorkOrder.setOnClickListener(this);
        mLlReturned.setOnClickListener(this);
        mLlReturnedWorkOrder.setOnClickListener(this);
        mLlStarTicket.setOnClickListener(this);
        mLlWarrantyWorkOrder.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_pending_review:
                EventBus.getDefault().post(11);
                EventBus.getDefault().post(0);
                break;
            case R.id.ll_pending:
                EventBus.getDefault().post(11);
                EventBus.getDefault().post(0);
                EventBus.getDefault().post("待寄件");
                break;
            case R.id.ll_paid:
                EventBus.getDefault().post(11);
                EventBus.getDefault().post(3);
                EventBus.getDefault().post("待支付");
                break;
            case R.id.ll_completed:
                EventBus.getDefault().post(11);
                EventBus.getDefault().post(3);
                EventBus.getDefault().post("已完成");
                break;
            case R.id.ll_message:
                startActivity(new Intent(mActivity, MessageActivity.class));
                break;
            case R.id.ll_pending_orders:
                EventBus.getDefault().post(11);
                EventBus.getDefault().post(6);
                break;
            case R.id.ll_received_work_order:
                EventBus.getDefault().post(11);
                EventBus.getDefault().post(0);
                break;
            case R.id.ll_star_ticket:
                EventBus.getDefault().post(11);
                EventBus.getDefault().post(1);
                break;
            case R.id.ll_returned:
                EventBus.getDefault().post(11);
                EventBus.getDefault().post(2);
                break;
            case R.id.ll_warranty_work_order:
                EventBus.getDefault().post(11);
                EventBus.getDefault().post(3);
                break;
            case R.id.ll_returned_work_order:
                EventBus.getDefault().post(11);
                EventBus.getDefault().post(4);
                break;
            case R.id.ll_close:
                EventBus.getDefault().post(11);
                EventBus.getDefault().post(5);
                break;
        }
    }

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                userInfoDean = baseResult.getData().getData().get(0);
                RequestOptions myOptions = new RequestOptions().transform(new GlideCircleWithBorder(mActivity, 2, Color.parseColor("#DCDCDC")));
                Glide.with(mActivity)
                        .load(Config.HEAD_URL + userInfoDean.getAvator())
                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                        .apply(myOptions)
                        .into(mIvAvatar);
//                mTvName.setText(userInfoDean.getNickName());
                String maskNumber = userInfoDean.getUserID().substring(0, 3) + "****" + userInfoDean.getUserID().substring(7, userInfoDean.getUserID().length());
                mTvPhone.setText(maskNumber);
                if ("1".equals(userInfoDean.getIfAuth())) {
                    mPresenter.GetmessageBytype(userId);
                } else {
                    mTvName.setText("未实名");
                }
                break;
        }
    }

    @Override
    public void GetListCategoryContentByCategoryID(BaseResult<Article> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                datalist = baseResult.getData().getData();
                mScrolltv.removeAllViews();
                mScrolltv.initView(R.layout.item_switchview, new SwitchView.ViewBuilder() {
                    @Override
                    public void initView(View view) {
                        final TextView tv_name = (TextView) view.findViewById(R.id.tv_content);
                        final TextView tv_url = (TextView) view.findViewById(R.id.tv_url);
                        tv_name.setText(datalist.get(i % datalist.size()).getTitle());
                        tv_url.setText(datalist.get(i % datalist.size()).getContent());
                        tv_name.setTag(i);

                        i++;
                        if (i == datalist.size()) {
                            i = 0;
                        }

                        tv_name.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String title = tv_name.getText().toString();
                                String content = tv_url.getText().toString();
                                Intent intent = new Intent(mActivity, WebActivity2.class);
                                intent.putExtra("Url", content);
                                intent.putExtra("title", title);
                                startActivity(intent);
                            }
                        });
                    }
                });
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
                        mTvName.setText(companyDean.getCompanyName());
                    } else {
                        mTvName.setText("未实名");
                    }
                } else {
                    mTvName.setText("未实名");
                }

                break;
        }
    }

    @Override
    public void FactoryNavigationBarNumber(BaseResult<Data<FactoryNavigationBarNumber>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    barNumber = baseResult.getData().getItem2();
                    mTvPendingOrders.setText("(" + barNumber.getCount1() + ")");
                    mTvReceivedWorkOrder.setText("(" + barNumber.getCount2() + ")");
                    mTvStarTicket.setText("(" + barNumber.getCount3() + ")");
                    mTvWarrantyWorkOrder.setText("(" + barNumber.getCount4() + ")");
                    mTvReturnedWorkOrder.setText("(" + barNumber.getCount5() + ")");
                    mTvClose.setText("(" + barNumber.getCount6() + ")");
                    mTvReturned.setText("(" + barNumber.getCount7() + ")");
                    mTvPendingReview.setText("" + barNumber.getCount8() + "");
                    mTvPending.setText("" + barNumber.getCount9() + "");
                    mTvPaid.setText("" + barNumber.getCount10() + "");
                    mTvCompleted.setText("" + barNumber.getCount11() + "");
                }
                break;
        }
    }

    @Override
    public void messgIsOrNo(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                if (baseResult.getData().isItem1()){
                    mIvRed.setVisibility(View.VISIBLE);
                }else {
                    mIvRed.setVisibility(View.GONE);
                }
                break;
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

    public void showRejectDialog() {
        under_review = LayoutInflater.from(mActivity).inflate(R.layout.dialog_audit_failure, null);
        TextView content = under_review.findViewById(R.id.tv_content);
        content.setText(userInfoDean.getAuthMessage() + ",有疑问请咨询客服电话。");
        Button btnConfirm = under_review.findViewById(R.id.btn_confirm);
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
        Button btnConfirm = under_review.findViewById(R.id.btn_confirm);
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {
        if ("transaction_num".equals(name)) {
            mPresenter.messgIsOrNo(userId, "1", "1");
        }
    }
}
