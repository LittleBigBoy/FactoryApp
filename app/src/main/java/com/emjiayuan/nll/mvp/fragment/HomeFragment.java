package com.emjiayuan.nll.mvp.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.emjiayuan.nll.Global;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.mvp.activity.CourseDetailActivity;
import com.emjiayuan.nll.mvp.activity.MessageActivity;
import com.emjiayuan.nll.mvp.activity.NewsDetailActivity;
import com.emjiayuan.nll.adapter.CourseAdapter;
import com.emjiayuan.nll.adapter.NewsAdapter;
import com.emjiayuan.nll.base.BaseLazyFragment;
import com.emjiayuan.nll.base.BaseResult;
import com.emjiayuan.nll.model.Course;
import com.emjiayuan.nll.model.HomeData;
import com.emjiayuan.nll.model.News;
import com.emjiayuan.nll.mvp.contract.HomeContract;
import com.emjiayuan.nll.mvp.model.HomeModel;
import com.emjiayuan.nll.mvp.presenter.HomePresenter;
import com.emjiayuan.nll.widget.RecyclerViewDivider;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class HomeFragment extends BaseLazyFragment<HomePresenter,HomeModel> implements HomeContract.View {
    private static final String TAG = "HomeFragment";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    RecyclerView rvTop;
    @BindView(R.id.rv_home)
    RecyclerView rvHome;

    private String mParam1;
    private String mParam2;

    private List<Course> courseList = new ArrayList<>();
    private String mRanknum;
    private ArrayList<News> mNewsArrayList;
    private ArrayList<Course> mCourseArrayList;
    private CourseAdapter mCourseAdapter;
    private NewsAdapter mNewsAdapter;
    private TextView mHomeText;
    private LinearLayout mMoreLl;
    private HomeData mHomeData;

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
    protected int setLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
//        getHomeData();
//        mPresenter.getData("1");
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
//                getHomeData();
//                mPresenter.getData("1");
            }
        });

    }

    @Override
    protected void initView() {

        rvHome.setLayoutManager(new LinearLayoutManager(mActivity));
        rvHome.addItemDecoration(new RecyclerViewDivider(mActivity, LinearLayoutManager.HORIZONTAL, 2, Color.parseColor("#F2F2F2")));
        refreshLayout.setEnableLoadMore(false);
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

}
