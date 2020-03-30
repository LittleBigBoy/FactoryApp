package com.zhenhaikj.factoryside.mvp.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.SPUtils;
import com.flyco.tablayout.SlidingTabLayout;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.Config;
import com.zhenhaikj.factoryside.mvp.adapter.MyPackageAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.MyPagerAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseLazyFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class WorkOrderFragment2 extends BaseLazyFragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";//
    private static final String ARG_PARAM2 = "param2";//
    @BindView(R.id.tab_receiving_layout)
    SlidingTabLayout mTabReceivingLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    private String mParam1;
    private String mParam2;
    private String[] mTitleUrgentlyList=new String[]{"远程费审核","配件审核","待寄件","留言"};
    private String[] mTitleToBeCompletedList=new String[]{"30日内","30日以上"};
    private String[] mTitleCompleteList=new String[]{"待支付","已支付"};
    private String[] mTitleChargebackList=new String[]{"取消工单","关闭工单"};
    private List<Fragment> mWorkOrderFragmentList ;
    private MyPagerAdapter mAdapter;
    SPUtils spUtils = SPUtils.getInstance("token");
    private String userid;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment V3HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WorkOrderFragment2 newInstance(String param1, String param2) {
        WorkOrderFragment2 fragment = new WorkOrderFragment2();
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
        return R.layout.fragment_work_order2;
    }

    @Override
    protected void initData() {
        mWorkOrderFragmentList = new ArrayList<>();
        if ("急需处理".equals(mParam1)){
            for (int i = 0; i < mTitleUrgentlyList.length; i++) {
                mWorkOrderFragmentList.add(WorkOrderFragment.newInstance(mTitleUrgentlyList[i], ""));
            }
            mAdapter = new MyPagerAdapter(getChildFragmentManager(),mTitleUrgentlyList,mWorkOrderFragmentList);
        }else if ("待完成".equals(mParam1)){
            for (int i = 0; i < mTitleToBeCompletedList.length; i++) {
                mWorkOrderFragmentList.add(WorkOrderFragment.newInstance(mTitleToBeCompletedList[i], ""));
                mAdapter = new MyPagerAdapter(getChildFragmentManager(),mTitleToBeCompletedList,mWorkOrderFragmentList);
            }
        }else if ("已完成".equals(mParam1)){
            for (int i = 0; i < mTitleCompleteList.length; i++) {
                mWorkOrderFragmentList.add(WorkOrderFragment.newInstance(mTitleCompleteList[i], ""));
                mAdapter = new MyPagerAdapter(getChildFragmentManager(),mTitleCompleteList,mWorkOrderFragmentList);
            }
        }else if ("退单处理".equals(mParam1)){
            for (int i = 0; i < mTitleChargebackList.length; i++) {
                mWorkOrderFragmentList.add(WorkOrderFragment.newInstance(mTitleChargebackList[i], ""));
                mAdapter = new MyPagerAdapter(getChildFragmentManager(),mTitleChargebackList,mWorkOrderFragmentList);
            }
        }

        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(mWorkOrderFragmentList.size());
        mTabReceivingLayout.setViewPager(mViewPager);

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onClick(View view) {

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String num) {
        switch (num) {
//            case Config.ORDER_READ:
////                mPresenter.FactoryGetOrderRed(userid);
//                break;
            case "待寄件":
                mViewPager.setCurrentItem(2);
                break;
            case "待支付":
                mViewPager.setCurrentItem(0);
                break;
            case "已完成":
                mViewPager.setCurrentItem(1);
                break;
        }
    }
}
