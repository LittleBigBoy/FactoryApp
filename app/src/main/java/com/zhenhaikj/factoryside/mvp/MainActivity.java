package com.zhenhaikj.factoryside.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.umeng.socialize.UMShareAPI;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Message;
import com.zhenhaikj.factoryside.mvp.bean.MessageData;
import com.zhenhaikj.factoryside.mvp.contract.MainContract;
import com.zhenhaikj.factoryside.mvp.fragment.AllWorkOrdersFragment;
import com.zhenhaikj.factoryside.mvp.fragment.DiscoveryFragment;
import com.zhenhaikj.factoryside.mvp.fragment.HomeFragment;
import com.zhenhaikj.factoryside.mvp.fragment.MineFragment;
import com.zhenhaikj.factoryside.mvp.fragment.NewsFragment;
import com.zhenhaikj.factoryside.mvp.model.MainModel;
import com.zhenhaikj.factoryside.mvp.presenter.MainPresenter;
import com.zhenhaikj.factoryside.mvp.widget.CustomViewPager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import q.rorbin.badgeview.QBadgeView;

/**
 * Created by geyifeng on 2017/5/8.
 */

public class MainActivity extends BaseActivity<MainPresenter, MainModel> implements View.OnClickListener, ViewPager.OnPageChangeListener, MainContract.View {

    @BindView(R.id.viewPager)
    CustomViewPager viewPager;
    @BindView(R.id.ll_home)
    LinearLayout ll_home;
    @BindView(R.id.ll_category)
    LinearLayout ll_category;
    @BindView(R.id.ll_car)
    LinearLayout ll_car;
    @BindView(R.id.ll_mine)
    LinearLayout ll_mine;
    @BindView(R.id.img_home)
    ImageView imgHome;
    @BindView(R.id.tv_home)
    TextView tvHome;
    @BindView(R.id.img_cate)
    ImageView imgCate;
    @BindView(R.id.tv_cate)
    TextView tvCate;
    @BindView(R.id.img_cart)
    ImageView imgCart;
    @BindView(R.id.tv_cart)
    TextView tvCart;
    @BindView(R.id.img_my)
    ImageView imgMy;
    @BindView(R.id.tv_my)
    TextView tvMy;
    @BindView(R.id.tab_menu)
    LinearLayout tabMenu;
    @BindView(R.id.img_message)
    ImageView mImgMessage;
    @BindView(R.id.tv_message)
    TextView mTvMessage;
    @BindView(R.id.ll_message)
    LinearLayout ll_message;
    @BindView(R.id.img_message_invisible)
    ImageView mImgMessageInvisible;
    private ArrayList<Fragment> mFragments;
    private long mExittime;
    private QBadgeView qBadgeView;
    SPUtils spUtils = SPUtils.getInstance("token");
    String userID = spUtils.getString("userName");

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        setSwipeBackEnable(false);
        mFragments = new ArrayList<>();
        mFragments.add(HomeFragment.newInstance("", ""));
        mFragments.add(NewsFragment.newInstance("", ""));
        mFragments.add(AllWorkOrdersFragment.newInstance("", ""));
        mFragments.add(DiscoveryFragment.newInstance("", ""));
        mFragments.add(MineFragment.newInstance("", ""));
        startService(new Intent(mActivity, LogcatScannerService.class));

        qBadgeView = new QBadgeView(mActivity);
        qBadgeView.bindTarget(mImgMessageInvisible);
        qBadgeView.setBadgeGravity(Gravity.END | Gravity.TOP);
        qBadgeView.setBadgeTextSize(0, false);

        mPresenter.GetMessageList(userID, "0", "999", "0");
        mPresenter.GetTransactionMessageList(userID, "0", "999", "0");
    }

    @Override
    protected void initView() {
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(mFragments.size());
        viewPager.setScroll(false);
        ll_home.setSelected(true);
    }

    @Override
    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarColor(R.color.transparent);
        mImmersionBar.fitsSystemWindows(false);
        mImmersionBar.init();
    }

    @Override
    protected void setListener() {
        ll_home.setOnClickListener(this);
        ll_message.setOnClickListener(this);
        ll_category.setOnClickListener(this);
        ll_car.setOnClickListener(this);
        ll_mine.setOnClickListener(this);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.ll_home:
                viewPager.setCurrentItem(0);
                tabSelected(ll_home);
                break;
            case R.id.ll_message:
                viewPager.setCurrentItem(1);
                tabSelected(ll_message);
                break;
            case R.id.ll_category:
                viewPager.setCurrentItem(2);
                tabSelected(ll_category);
                break;

            case R.id.ll_car:
                viewPager.setCurrentItem(3);
                tabSelected(ll_car);
                break;
            case R.id.ll_mine:
                viewPager.setCurrentItem(4);
                tabSelected(ll_mine);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                tabSelected(ll_home);
                break;
            case 1:
                tabSelected(ll_message);
                break;
            case 2:
                tabSelected(ll_category);
                break;
            case 3:
                tabSelected(ll_car);
                break;
            case 4:
                tabSelected(ll_mine);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void tabSelected(LinearLayout linearLayout) {
        ll_home.setSelected(false);
        ll_message.setSelected(false);
        ll_category.setSelected(false);
        ll_car.setSelected(false);
        ll_mine.setSelected(false);
        linearLayout.setSelected(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void GetMessageList(BaseResult<MessageData<List<Message>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().getCount() == 0) {
                    qBadgeView.hide(true);

                } else {
                    qBadgeView.setBadgeNumber(1);

                }
                break;
            default:
                break;
        }
    }

    @Override
    public void GetTransactionMessageList(BaseResult<MessageData<List<Message>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().getCount() == 0) {
                    qBadgeView.hide(true);
                } else {
                    qBadgeView.setBadgeNumber(1);
                }
                break;
            default:
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String message) {
        switch (message) {
            case "orderempty":
                mPresenter.GetMessageList(userID, "0", "999", "1");
                break;
            case "transactionempty":
                mPresenter.GetTransactionMessageList(userID, "0", "999", "1");
                break;
        }


    }

    private class MyAdapter extends FragmentPagerAdapter {
        MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        /*  两秒之内再按一下退出*/
        //判断用户是否点击了返回键
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //与上次点击返回键作差
            if ((System.currentTimeMillis() - mExittime) > 2000) {
                //大于2秒认为是误操作
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                //并记录记录下本次点击返回键的时刻
                mExittime = System.currentTimeMillis();
            } else {
                //小于2秒 则用户希望退出
                System.exit(0);
            }
            return true;

        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        UMShareAPI.get(this).release();
    }
}
