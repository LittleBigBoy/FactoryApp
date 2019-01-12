package com.zhenhaikj.factoryside.mvp.adapter;

import com.zhenhaikj.factoryside.mvp.fragment.WorkOrderFragment;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Created by hackware on 2016/9/10.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {
    // 定义Tab标题
    private String[] tabTitles;
    private List<Fragment> mViewPagerFragmentList;

    public MyPagerAdapter(FragmentManager fm, String[] tabTitles, List<Fragment> mViewPagerFragmentList) {
        super(fm);
        this.tabTitles = tabTitles;
        this.mViewPagerFragmentList = mViewPagerFragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mViewPagerFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
