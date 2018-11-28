package com.emjiayuan.nll.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.activity.CourseDetailActivity;
import com.emjiayuan.nll.activity.CourseSearchActivity;
import com.emjiayuan.nll.adapter.CourseAdapter;
import com.emjiayuan.nll.base.BaseLazyFragment;
import com.emjiayuan.nll.model.Course;
import com.emjiayuan.nll.utils.GlideImageLoader;
import com.emjiayuan.nll.utils.MyOkHttp;
import com.emjiayuan.nll.utils.MyUtils;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

public class CourseFragment extends BaseLazyFragment implements View.OnClickListener {
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

    private ArrayList<Course> mCourseArrayList=new ArrayList<>();
    private CourseAdapter mCourseAdapter;
    private int pageindex=1;

    public CourseFragment() {
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
    public static CourseFragment newInstance(String param1, String param2) {
        CourseFragment fragment = new CourseFragment();
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
        mCourseAdapter.openLoadAnimation();
        mCourseAdapter.isFirstOnly(false);
        mCourseAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
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
        ArrayList<String> imagelist = new ArrayList();
        for (int i = 0; i < images.length; i++) {
            imagelist.add(images[i]);
        }
        bannerTop.setImages(imagelist);
        bannerTop.start();
        getCollegeList(pageindex);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                pageindex=1;
                mCourseArrayList.clear();
                refreshLayout.setNoMoreData(false);
                getCollegeList(pageindex);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                pageindex++;
                getCollegeList(pageindex);
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

    public void getCollegeList(int pageindex) {
        /*if (!checkNetwork()) {
            stateLayout.changeState(StateFrameLayout.NET_ERROR);
            return;
        }
        if (flags) {
            stateLayout.changeState(StateFrameLayout.LOADING);
        }*/

        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("pageindex", Integer.toString(pageindex));//传递键值对参数
        formBody.add("pagesize", "10");//传递键值对参数
        Log.d("------参数------", formBody.build().toString());
//new call
        Call call = MyOkHttp.GetCall("College.getCollegeList", formBody);
//请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("------------", e.toString());
//                        myHandler.sendEmptyMessage(1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String result = response.body().string();
                MyUtils.e("------获取学院列表结果------", result);
                Message message = new Message();
                message.what = 0;
                Bundle bundle = new Bundle();
                bundle.putString("result", result);
                message.setData(bundle);
                myHandler.sendMessage(message);
            }
        });
    }



    Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            String result = bundle.getString("result");
            switch (msg.what) {
                case 0:
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String code = jsonObject.getString("code");
                        String message = jsonObject.getString("message");
                        String data = jsonObject.getString("data");
                        Gson gson = new Gson();
                        if ("200".equals(code)) {
//                            stateLayout.changeState(StateFrameLayout.SUCCESS);
                            JSONArray dataArray = new JSONArray(data);
//                            mCourseArrayList = new ArrayList<>();
                            for (int i = 0; i < dataArray.length(); i++) {
                                mCourseArrayList.add(gson.fromJson(dataArray.getJSONObject(i).toString(), Course.class));
                            }
                            mCourseAdapter.setNewData(mCourseArrayList);

                        } else {
                            if (pageindex!=1){
                                refreshLayout.setNoMoreData(true);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    refreshLayout.finishRefresh();
                    refreshLayout.finishLoadMore();
                    break;
            }
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.et_search:
                startActivity(new Intent(mActivity,CourseSearchActivity.class));
                break;
        }
    }
}
