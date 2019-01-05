package com.emjiayuan.factoryside.mvp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.emjiayuan.factoryside.mvp.activity.CourseDetailActivity;
import com.emjiayuan.factoryside.mvp.activity.CourseSearchActivity;
import com.emjiayuan.factoryside.mvp.contract.CourseContract;
import com.emjiayuan.factoryside.mvp.model.CourseModel;
import com.emjiayuan.factoryside.mvp.presenter.CoursePresenter;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.adapter.CourseAdapter;
import com.emjiayuan.nll.base.BaseLazyFragment;
import com.emjiayuan.nll.base.BaseResult;
import com.emjiayuan.nll.model.Course;
import com.emjiayuan.nll.utils.GlideImageLoader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class DiscoveryFragment extends BaseLazyFragment<CoursePresenter,CourseModel> implements View.OnClickListener,CourseContract.View {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    Banner bannerTop;
    @BindView(R.id.rv_college)
    RecyclerView mRvCollege;
    @BindView(R.id.et_search)
    TextView mEtSearch;
    @BindView(R.id.iv_car)
    ImageView mIvCar;


    private String mParam1;
    private String mParam2;
    private String[] images = new String[]{
            "http://qiniu.emjiayuan.com/upload_file/ems/2018092111329525354",
            "http://qiniu.emjiayuan.com/upload_file/ems/2018100815514348272",
            "http://qiniu.emjiayuan.com/upload_file/ems/2018101113731538120",
            "http://qiniu.emjiayuan.com/upload_file/ems/2018100911071275167",
            "http://qiniu.emjiayuan.com/upload_file/ems/2018071817991346559",
    };

    private List<Course> mCourseArrayList=new ArrayList<>();
    private CourseAdapter mCourseAdapter;
    private int pageindex=1;
    private ArrayList<String> mImagelist;

    public DiscoveryFragment() {
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
    public static DiscoveryFragment newInstance(String param1, String param2) {
        DiscoveryFragment fragment = new DiscoveryFragment();
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
        return R.layout.fragment_college;
    }

    @Override
    protected void initData() {
        mCourseAdapter = new CourseAdapter(R.layout.course_item, mCourseArrayList);
        View top = LayoutInflater.from(mActivity).inflate(R.layout.banner_top_college, null);
        bannerTop = top.findViewById(R.id.banner_top);
        mCourseAdapter.addHeaderView(top);
//        mCourseAdapter.openLoadAnimation();
//        mCourseAdapter.isFirstOnly(false);
//        mCourseAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        mRvCollege.setAdapter(mCourseAdapter);
        mCourseAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(mActivity, CourseDetailActivity.class);
                intent.putExtra("collegeid", mCourseArrayList.get(position).getId());
                startActivity(intent);
            }
        });

        bannerTop.setImageLoader(new GlideImageLoader());

//        getCollegeList(pageindex);
        mPresenter.getData(Integer.toString(pageindex),"3");
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                pageindex=1;
                mCourseArrayList.clear();
                refreshLayout.setNoMoreData(false);
//                getCollegeList(pageindex);
                mPresenter.getData(Integer.toString(pageindex),"3");
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                pageindex++;
//                getCollegeList(pageindex);
                mPresenter.getData(Integer.toString(pageindex),"3");
            }
        });
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarColor(R.color.white);
    }

    @Override
    protected void initView() {
        mRvCollege.setLayoutManager(new LinearLayoutManager(mActivity));
//        mRvCollege.addItemDecoration(new RecyclerViewDivider(mActivity, LinearLayoutManager.HORIZONTAL, 2, Color.parseColor("#E6E6E6")));
    }

    @Override
    protected void setListener() {
        mEtSearch.setOnClickListener(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.et_search:
                startActivity(new Intent(mActivity,CourseSearchActivity.class));
                break;
        }
    }

    @Override
    public void success(BaseResult<List<Course>> baseResult) {
        mCourseArrayList.addAll(baseResult.getData());
        mCourseAdapter.setNewData(mCourseArrayList);
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();
    }

    @Override
    public void fail(BaseResult<List<Course>> baseResult) {
        if (pageindex!=1){
            refreshLayout.finishLoadMoreWithNoMoreData();
        }else{
            ToastUtils.showShort(baseResult.getMessage());
            refreshLayout.finishRefresh();
            refreshLayout.finishLoadMore();
        }
    }
}
