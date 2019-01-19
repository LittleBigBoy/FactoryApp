package com.zhenhaikj.factoryside.mvp.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.adapter.MyPagerAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseLazyFragment;
import com.zhenhaikj.factoryside.mvp.event.UpdateEvent;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

public class NewsFragment extends BaseLazyFragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.magic_news)
    MagicIndicator mMagicNews;
//    @BindView(R.id.toolbar_news)
//    Toolbar mToolbarNews;
    @BindView(R.id.view_pager_news)
    ViewPager mViewPagerNews;


    private String mParam1;
    private String mParam2;
    private String[] mTitleDataList = new String[]{
            "消息", "通知"
    };
    private CommonNavigator commonNavigator;
    private List<Fragment> mNewsList;


    public NewsFragment() {
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
    public static NewsFragment newInstance(String param1, String param2) {
        NewsFragment fragment = new NewsFragment();
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
        super.initImmersionBar();
//        mImmersionBar.statusBarView(mToolbarNews);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initData() {
        mNewsList = new ArrayList<>();
        int i=0;
        mNewsList.add(InformationFragment.newInstance(mTitleDataList[i],""));
        int a=1;
        mNewsList.add(NoticeFragment.newInstance(mTitleDataList[a],""));
//        for (int i = 0; i < 2; i++) {
//            mNewsList.add(MessageFragment.newInstance(mTitleDataList[i],""));
//        }
        mViewPagerNews.setOffscreenPageLimit(mTitleDataList.length);
        mViewPagerNews.setAdapter(new MyPagerAdapter(this.getFragmentManager(),mTitleDataList,mNewsList));
        commonNavigator = new CommonNavigator(mActivity);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return mTitleDataList == null ? 0 : mTitleDataList.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(Color.WHITE);
                colorTransitionPagerTitleView.setSelectedColor(Color.WHITE);
                colorTransitionPagerTitleView.setTextSize(20);
                colorTransitionPagerTitleView.setLeft(10000);
                colorTransitionPagerTitleView.setText(mTitleDataList[index]);
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mViewPagerNews.setCurrentItem(index);
                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                indicator.setColors(Color.WHITE);
                return indicator;
            }
        });
        mMagicNews.setBackgroundResource(R.color.red);
        mMagicNews.setNavigator(commonNavigator);
        LinearLayout titleContainer = commonNavigator.getTitleContainer();
        titleContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        titleContainer.setDividerPadding(UIUtil.dip2px(mActivity, 15));
        titleContainer.setDividerDrawable(getResources().getDrawable(R.drawable.simple_splitter));

        ViewPagerHelper.bind(mMagicNews, mViewPagerNews);
    }

    @Override
    protected void initView() {

    }


    @Override
    protected void setListener() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.normal_dfk_ll:
                break;
            case R.id.normal_dfh_ll:
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(UpdateEvent event) {
    }
}
