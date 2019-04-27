package com.zhenhaikj.factoryside.mvp.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.Config;
import com.zhenhaikj.factoryside.mvp.activity.AllWorkOrdersActivity;
import com.zhenhaikj.factoryside.mvp.activity.BatchOrderActivity;
import com.zhenhaikj.factoryside.mvp.activity.CustomerServiceActivity;
import com.zhenhaikj.factoryside.mvp.activity.HomeMaintenanceActivity2;
import com.zhenhaikj.factoryside.mvp.base.BaseLazyFragment;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.HomeData;
import com.zhenhaikj.factoryside.mvp.bean.UserInfo;
import com.zhenhaikj.factoryside.mvp.contract.HomeContract;
import com.zhenhaikj.factoryside.mvp.model.HomeModel;
import com.zhenhaikj.factoryside.mvp.presenter.HomePresenter;
import com.zhenhaikj.factoryside.mvp.utils.GlideImageLoader;
import com.zhenhaikj.factoryside.mvp.widget.GlideCircleWithBorder;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class HomeFragment extends BaseLazyFragment<HomePresenter, HomeModel> implements HomeContract.View {
    private static final String TAG = "HomeFragment";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.rv_common_menu)
    RecyclerView mRvCommonMenu;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.banner_home)
    Banner mBannerHome;
    @BindView(R.id.tv_announcement)
    TextView mTvAnnouncement;
    @BindView(R.id.rv_main_menu)
    RecyclerView mRvMainMenu;
    @BindView(R.id.iv_more)
    ImageView mIvMore;
    @BindView(R.id.iv_service)
    ImageView mIvService;
    @BindView(R.id.iv_gray_message)
    ImageView mIvGrayMessage;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.iv_avatar)
    ImageView mIvAvatar;
    @BindView(R.id.tv_factory_name)
    TextView mTvFactoryName;
    @BindView(R.id.tv_verified)
    TextView mTvVerified;
    @BindView(R.id.iv_verified)
    ImageView mIvVerified;
    @BindView(R.id.tv_money)
    TextView mTvMoney;
    @BindView(R.id.iv_eye)
    ImageView mIvEye;
    @BindView(R.id.tv_recharge)
    TextView mTvRecharge;
    @BindView(R.id.tv_pay_the_deposit)
    TextView mTvPayTheDeposit;


    private String mParam1;
    private String mParam2;
    private ArrayList<MenuItem2> mMainMenus;
    private ArrayList<MenuItem> mCommonMenus;
    private UserInfo.UserInfoDean userInfoDean;

    private Integer[] icons = new Integer[]{
            R.mipmap.one_bg, R.mipmap.two_bg, R.mipmap.three_bg, R.mipmap.four_bg,R.drawable.suoyou1,
            R.mipmap.daijiedan, R.mipmap.daishenhe, R.mipmap.daizhifu,  R.mipmap.yiwanjie,R.drawable.zhibao, R.mipmap.tuidan
    };

    private Integer[] icons_content = new Integer[]{
            R.mipmap.one, R.mipmap.two, R.mipmap.three, R.mipmap.four,
            R.drawable.cost_change, R.drawable.waiting_order, R.drawable.waiting_order, R.drawable.return_order_processing, R.drawable.finished, R.drawable.accessory_list, R.drawable.to_be_paid,
            R.drawable.remote_bill, R.drawable.warranty, R.drawable.undone, R.drawable.leave_a_message
    };
    private String[] names = new String[]{
            "发布安装", "发布维修", "发布送修", "批量发单","所有工单",
            "待接单", "待审核", "待支付",  "已完结","质保单", "退单处理"
    };
    private MenuAdapter2 mMainAdapter;
    private MenuAdapter mCommonAdapter;
    private Intent intent;
    private SPUtils spUtils;
    private String userId;

    public HomeFragment() {
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
        mPresenter.GetUserInfoList(userId, "1");
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
        for (int i = 4; i < 11; i++) {
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
                        intent = new Intent(mActivity, HomeMaintenanceActivity2.class);
                        intent.putExtra("type", 0);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(mActivity, HomeMaintenanceActivity2.class);
                        intent.putExtra("type", 1);
                        startActivity(intent);
                        break;
                    case 2:
                        startActivity(new Intent(mActivity, CustomerServiceActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(mActivity, BatchOrderActivity.class));
                        break;
                }
            }
        });
        mCommonAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("title", mCommonMenus.get(position).getName());
                bundle.putInt("position", position);
                Intent intent = new Intent(mActivity, AllWorkOrdersActivity.class);
                intent.putExtras(bundle);
                ActivityUtils.startActivity(intent);
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
                mRefreshLayout.finishRefresh(1000);
            }
        });

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {
        if (!"GetUserInfoList".equals(name)){
            return;
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
                if (baseResult.getData().getData().isEmpty()){
                    return;
                }else {
                    userInfoDean = baseResult.getData().getData().get(0);
                    if (userInfoDean.getAvator() == null) {
                        return;
                    } else {
                        RequestOptions myOptions = new RequestOptions().transform(new GlideCircleWithBorder(this,1, Color.parseColor("#DCDCDC")));
                        Glide.with(mActivity)
                                .load(Config.HEAD_URL + userInfoDean.getAvator())
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .apply(myOptions)
                                .into(mIvAvatar);
                    }
                    mTvFactoryName.setText(userInfoDean.getTrueName());
                    mTvMoney.setText("" + (userInfoDean.getTotalMoney()-userInfoDean.getFrozenMoney()));
                }
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
}
