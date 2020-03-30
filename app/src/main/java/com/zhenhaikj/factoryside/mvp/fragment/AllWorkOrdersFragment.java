package com.zhenhaikj.factoryside.mvp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.SPUtils;
import com.flyco.tablayout.SlidingTabLayout;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.Config;
import com.zhenhaikj.factoryside.mvp.activity.SearchActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseLazyFragment;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.RedPointData;
import com.zhenhaikj.factoryside.mvp.contract.RedPointContract;
import com.zhenhaikj.factoryside.mvp.model.RedPointModel;
import com.zhenhaikj.factoryside.mvp.presenter.RedPointPresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AllWorkOrdersFragment extends BaseLazyFragment<RedPointPresenter, RedPointModel> implements View.OnClickListener, RedPointContract.View {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    /*    @BindView(R.id.magic_indicator)
        MagicIndicator mMagicIndicator;*/
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.tab_receiving_layout)
    SlidingTabLayout mTabReceivingLayout;
    @BindView(R.id.et_search)
    TextView mEtSearch;
    @BindView(R.id.iv_microphone)
    ImageView mIvMicrophone;
    @BindView(R.id.ll_search)
    LinearLayout mLlSearch;

    private String mParam1;
    private String mParam2;
    //    private String[] mTitleDataList = new String[]{
//            "待审核","待接单","已接单","星标工单","待寄件", "待支付", "已完成", "质保单","退单处理","关闭工单"
//    };
    private String[] mTitleDataList = new String[]{
            "急需处理", "待完成", "星标工单", "已完成", "质保单", "退单处理", "所有工单"
    };
    // private CommonNavigator commonNavigator;
    private List<Fragment> mWorkOrderFragmentList = new ArrayList<>();
    private MyPagerAdapter mAdapter;
    SPUtils spUtils = SPUtils.getInstance("token");
    private String userid;

    public AllWorkOrdersFragment() {
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
    public static AllWorkOrdersFragment newInstance(String param1, String param2) {
        AllWorkOrdersFragment fragment = new AllWorkOrdersFragment();
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

    /**
     * 初始化沉浸式
     */
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
        return R.layout.fragment_all_work_order;
    }

    @Override
    protected void initData() {

//        for (String title:mTitleDataList){
//            if ("待审核".equals(mTitleDataList[0])){
//                mWorkOrderFragmentList.add(PendingReviewFragment.newInstance(title,""));
//            }
//            mWorkOrderFragmentList.add(WorkOrderFragment.newInstance(title,""));
//        }
        for (int i = 0; i < mTitleDataList.length; i++) {
            if (i == 0 || i == 1 || i == 3 || i == 5) {
                mWorkOrderFragmentList.add(WorkOrderFragment2.newInstance(mTitleDataList[i], ""));
            } else {
                mWorkOrderFragmentList.add(WorkOrderFragment.newInstance(mTitleDataList[i], ""));
            }

        }
        mAdapter = new MyPagerAdapter(getFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(mWorkOrderFragmentList.size());
        mTabReceivingLayout.setViewPager(mViewPager);

        userid = spUtils.getString("userName");
//        mPresenter.FactoryGetOrderRed(userid);
      /*  mViewPager.setAdapter(new MyPagerAdapter(getFragmentManager(),mTitleDataList,mWorkOrderFragmentList));
        commonNavigator = new CommonNavigator(mActivity);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return mTitleDataList == null ? 0 : mTitleDataList.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(Color.BLACK);
                colorTransitionPagerTitleView.setSelectedColor(Color.RED);
                colorTransitionPagerTitleView.setText(mTitleDataList[index]);
                colorTransitionPagerTitleView.setTextSize(18);
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mViewPager.setCurrentItem(index);
                        mTitle.setText(mTitleDataList[index]);
                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                indicator.setColors(Color.RED);
                return indicator;
            }
        });
        mMagicIndicator.setBackgroundColor(Color.WHITE);
        mMagicIndicator.setNavigator(commonNavigator);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTitle.setText(mTitleDataList[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        ViewPagerHelper.bind(mMagicIndicator, mViewPager);*/
    }

    @Override
    protected void initView() {

    }


    @Override
    protected void setListener() {
        mLlSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_search:
                startActivity(new Intent(mActivity, SearchActivity.class));
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(Integer num) {
        switch (num) {
            case Config.ORDER_READ:
//                mPresenter.FactoryGetOrderRed(userid);
                break;
            case 0:
                mViewPager.setCurrentItem(num);
                break;
            case 1:
                mViewPager.setCurrentItem(num);
                break;
            case 2:
                mViewPager.setCurrentItem(num);
                break;
            case 3:
                mViewPager.setCurrentItem(num);
                break;
            case 4:
                mViewPager.setCurrentItem(num);
                break;
            case 5:
                mViewPager.setCurrentItem(num);
                break;
            case 6:
                mViewPager.setCurrentItem(num);
                break;
            case 7:
                mViewPager.setCurrentItem(num);
                break;
            case 8:
                mViewPager.setCurrentItem(num);
                break;
            case 9:
                mViewPager.setCurrentItem(num);
                break;
//            case 6:
//                mViewPager.setCurrentItem(num);
//                break;
//            case 4:
//                mViewPager.setCurrentItem(num);
//                break;
//            case 2:
//                mViewPager.setCurrentItem(num);
//                break;
//            case 1:
//                mViewPager.setCurrentItem(num);
//                break;
//            case 7:
//                mViewPager.setCurrentItem(num);
//                break;
        }
    }

    @Override
    public void FactoryGetOrderRed(BaseResult<RedPointData> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData() != null) {
                    String data = baseResult.getData().getData();
                    for (int i = 0; i < data.length(); i++) {
                        char c = baseResult.getData().getData().charAt(i);
                        if (c != 'n') {
                            mTabReceivingLayout.showDot(i);
                        } else {
                            mTabReceivingLayout.hideMsg(i);
                        }
                    }
                } else {
                    return;
                }
                break;
            default:
                break;
        }

    }


    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mWorkOrderFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleDataList[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mWorkOrderFragmentList.get(position);
        }
    }


}
