package com.emjiayuan.nll.mvp.activity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.emjiayuan.nll.R;
import com.emjiayuan.nll.base.BaseActivity;
import com.emjiayuan.nll.fragment.ViewPagerFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

public class OrderNormalActivity extends BaseActivity implements View.OnClickListener {


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
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.vp_tab_pager)
    ViewPager mVpTabPager;
    private int curTab=0;
    private List<ViewPagerFragment> mViewPagerFragmentList=new ArrayList<>();
    private MyPagerAdapter mMyPagerAdapter;
    private String order_type;
    private String order_status;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_order_nomal;
    }

    @Override
    protected void initData() {

    }


    @Override
    protected void initView() {
        curTab=getIntent().getIntExtra("curTab",0);
        order_type=getIntent().getStringExtra("order_type");
        mTvTitle.setText(order_type);
        mTvTitle.setVisibility(View.VISIBLE);
        if ("普通订单".equals(order_type)){
            mViewPagerFragmentList.add(ViewPagerFragment.newInstance("1","0"));
            mViewPagerFragmentList.add(ViewPagerFragment.newInstance("2","0"));
            mViewPagerFragmentList.add(ViewPagerFragment.newInstance("3","0"));
            mViewPagerFragmentList.add(ViewPagerFragment.newInstance("4","0"));
            mViewPagerFragmentList.add(ViewPagerFragment.newInstance("","0"));
        }else{
            mViewPagerFragmentList.add(ViewPagerFragment.newInstance("1","1"));
            mViewPagerFragmentList.add(ViewPagerFragment.newInstance("2","1"));
            mViewPagerFragmentList.add(ViewPagerFragment.newInstance("3","1"));
            mViewPagerFragmentList.add(ViewPagerFragment.newInstance("","1"));
        }
        mMyPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mVpTabPager.setAdapter(mMyPagerAdapter);
        mVpTabPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        // 把TabLayout和ViewPager关联起来
        mTabLayout.setupWithViewPager(mVpTabPager);
        mVpTabPager.setCurrentItem(curTab);
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
        }
    }

    /**
     * ViewPager适配器
     */
    public class MyPagerAdapter extends FragmentPagerAdapter {

        // 定义Tab标题
        private String[] tabTitles;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            if ("普通订单".equals(order_type)){
                tabTitles = new String[]{
                        "待付款", "待发货", "待收货", "待评价","全部"
                };
            }else{
                tabTitles = new String[]{
                        "待付款", "待发货", "待收货","全部"
                };
            }
        }

        @Override
        public Fragment getItem(int position) {
            return mViewPagerFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return tabTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // 设置Page对应的Tab标题
            return tabTitles[position];
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
        }
    }

}
