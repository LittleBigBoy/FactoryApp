package com.zhenhaikj.factoryside.mvp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.activity.AllWorkOrdersActivity;
import com.zhenhaikj.factoryside.mvp.activity.BatchOrderActivity;
import com.zhenhaikj.factoryside.mvp.activity.CustomerServiceActivity;
import com.zhenhaikj.factoryside.mvp.activity.HomeInstallationActivity;
import com.zhenhaikj.factoryside.mvp.activity.HomeMaintenanceActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseLazyFragment;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.HomeData;
import com.zhenhaikj.factoryside.mvp.contract.HomeContract;
import com.zhenhaikj.factoryside.mvp.model.HomeModel;
import com.zhenhaikj.factoryside.mvp.presenter.HomePresenter;
import com.zhenhaikj.factoryside.mvp.utils.GlideImageLoader;

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


    private String mParam1;
    private String mParam2;
    private ArrayList<MenuItem> mMainMenus;
    private ArrayList<MenuItem> mCommonMenus;


    private Integer[] icons = new Integer[]{
            R.drawable.installation, R.drawable.home_maintenance, R.drawable.customer_service_order, R.drawable.batch_order,
            R.drawable.waiting_order, R.drawable.return_order_processing, R.drawable.finished, R.drawable.accessory_list, R.drawable.to_be_paid,
            R.drawable.remote_bill, R.drawable.warranty, R.drawable.undone, R.drawable.cost_change, R.drawable.leave_a_message
    };
    private String[] names = new String[]{
            "上门安装", "上门维修", "客户送修单", "批量发单",
            "待接单", "退单处理", "已完结", "配件单", "待支付",
            "远程费单", "质保单", "未完成单", "费用变更", "留言工单"
    };
    private MenuAdapter mMainAdapter;
    private MenuAdapter mCommonAdapter;

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
        super.initImmersionBar();
        mImmersionBar.statusBarView(mToolbar);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        List<Integer> images = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            images.add(R.drawable.home_banner);
        }
        mBannerHome.setImageLoader(new GlideImageLoader());
        mBannerHome.setImages(images);
        mBannerHome.start();
        mMainMenus = new ArrayList<>();
        mCommonMenus = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            mMainMenus.add(new MenuItem(icons[i], names[i]));
        }
        for (int i = 4; i < 14; i++) {
            mCommonMenus.add(new MenuItem(icons[i], names[i]));
        }
        mMainAdapter = new MenuAdapter(R.layout.menu_item, mMainMenus);
        mCommonAdapter = new MenuAdapter(R.layout.menu_item, mCommonMenus);
        mRvCommonMenu.setLayoutManager(new GridLayoutManager(mActivity, 5));
//        mRvCommonMenu.addItemDecoration(new RecyclerViewDivider(mActivity, LinearLayoutManager.HORIZONTAL, 2, Color.parseColor("#F2F2F2")));
        mRvMainMenu.setLayoutManager(new GridLayoutManager(mActivity, 4));
//        mRvMainMenu.addItemDecoration(new RecyclerViewDivider(mActivity, LinearLayoutManager.HORIZONTAL, 2, Color.parseColor("#F2F2F2")));
        mRvMainMenu.setAdapter(mMainAdapter);
        mRvCommonMenu.setAdapter(mCommonAdapter);
        mMainAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(mActivity, HomeInstallationActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(mActivity, HomeMaintenanceActivity.class));
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

    }

    @Override
    public void success(BaseResult<HomeData> baseResult) {

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
}
