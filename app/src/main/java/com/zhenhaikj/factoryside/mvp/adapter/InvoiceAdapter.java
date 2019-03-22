package com.zhenhaikj.factoryside.mvp.adapter;


import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


/**
 * Created by hackware on 2016/9/10.
 */

public class InvoiceAdapter extends FragmentPagerAdapter {


    private List<Fragment> fragments;

    /**
     * 构造函数（关联fragments,viewpager,title）
     * @param fm 碎片管理者
     * @param fragments 碎片集合
     */
    public InvoiceAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
    //获取当前的标题
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return titles.get(position);
//    }

}
