package com.zhenhaikj.factoryside.mvp.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.flyco.tablayout.SlidingTabLayout;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.Config;
import com.zhenhaikj.factoryside.mvp.adapter.MyPagerAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.RedPointData;
import com.zhenhaikj.factoryside.mvp.contract.RedPointContract;
import com.zhenhaikj.factoryside.mvp.fragment.WorkOrderFragment;
import com.zhenhaikj.factoryside.mvp.model.RedPointModel;
import com.zhenhaikj.factoryside.mvp.presenter.RedPointPresenter;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;


public class AllWorkOrdersActivity extends BaseActivity<RedPointPresenter, RedPointModel> implements View.OnClickListener, RedPointContract.View {


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
   /* @BindView(R.id.magic_indicator)
    MagicIndicator mMagicIndicator;*/
     @BindView(R.id.tab_receiving_layout)
     SlidingTabLayout mTabReceivingLayout;

    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.view)
    View mView;


    private String[] mTitleDataList = new String[]{
            "远程费审核","所有工单","待接单","已接单","待审核", "待支付", "已完成", "质保单","退单处理"
    };

    //private CommonNavigator commonNavigator;
    private MyPagerAdapter mAdapter;
    private ArrayList<Fragment> mWorkOrderFragmentList=new ArrayList<>();;
    private Bundle bundle;
    private SPUtils spUtils;
    private String userid;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_all_work_orders;
    }

    @Override
    protected void initData() {
    }
    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
//        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
//        mImmersionBar.statusBarView(mView);
        mImmersionBar.fitsSystemWindows(true);
        mImmersionBar.keyboardEnable(true);
        mImmersionBar.init();
    }
    @Override
    protected void initView() {

        for (int i = 0; i < 9; i++) {
            mWorkOrderFragmentList.add(WorkOrderFragment.newInstance(mTitleDataList[i], ""));
        }
        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(mWorkOrderFragmentList.size());
        mTabReceivingLayout.setViewPager(mViewPager);


        setSwipeBackEnable(false);
        mTvTitle.setVisibility(View.VISIBLE);
        bundle = getIntent().getExtras();
        mTvTitle.setText(bundle.getString("title"));
        mViewPager.setCurrentItem(bundle.getInt("position"));
        spUtils= SPUtils.getInstance("token");
        userid=spUtils.getString("userName");
        mPresenter.FactoryGetOrderRed(userid);
      /*    mWorkOrderFragmentList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            mWorkOrderFragmentList.add(WorkOrderFragment.newInstance(mTitleDataList[i], ""));
        }
        mViewPager.setOffscreenPageLimit(mTitleDataList.length);
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), mTitleDataList, mWorkOrderFragmentList));
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
                        mTvTitle.setText(mTitleDataList[index]);
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
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTvTitle.setText(mTitleDataList[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setCurrentItem(bundle.getInt("position"));
        commonNavigator.onPageSelected(bundle.getInt("position"));
        mMagicIndicator.setNavigator(commonNavigator);
        mMagicIndicator.setBackgroundColor(Color.WHITE);
        ViewPagerHelper.bind(mMagicIndicator, mViewPager);*/
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(Integer num) {
        switch (num){
            case Config.ORDER_READ:
               mPresenter.FactoryGetOrderRed(userid);
                break;
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.icon_search:
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void FactoryGetOrderRed(BaseResult<RedPointData> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                if (baseResult.getData()!=null){
                    String data=baseResult.getData().getData();
                    for (int i =0;i<data.length();i++){
                        char c=baseResult.getData().getData().charAt(i);
                        if (c!='n'){
                            mTabReceivingLayout.showDot(i);
                        }else {
                            mTabReceivingLayout.hideMsg(i);
                        }
                    }
                }
                else {
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