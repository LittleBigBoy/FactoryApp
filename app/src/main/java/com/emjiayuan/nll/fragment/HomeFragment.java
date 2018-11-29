package com.emjiayuan.nll.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.emjiayuan.nll.Global;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.activity.CourseDetailActivity;
import com.emjiayuan.nll.activity.MessageActivity;
import com.emjiayuan.nll.activity.NewsDetailActivity;
import com.emjiayuan.nll.adapter.CourseAdapter;
import com.emjiayuan.nll.adapter.NewsAdapter;
import com.emjiayuan.nll.base.BaseLazyFragment;
import com.emjiayuan.nll.model.Course;
import com.emjiayuan.nll.model.News;
import com.emjiayuan.nll.utils.MyOkHttp;
import com.emjiayuan.nll.utils.MyUtils;
import com.emjiayuan.nll.widget.RecyclerViewDivider;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

public class HomeFragment extends BaseLazyFragment {
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
        getHomeData();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                getHomeData();
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

    public void getHomeData() {
        /*if (!checkNetwork()) {
            stateLayout.changeState(StateFrameLayout.NET_ERROR);
            return;
        }
        if (flag) {
            stateLayout.changeState(StateFrameLayout.LOADING);
            flag = false;
        }*/
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        Log.d("------参数------", formBody.build().toString());
        formBody.add("userid",Global.loginResult.getId());
//new call
        Call call = MyOkHttp.GetCall("public.appHome", formBody);
//        Call call = MyOkHttp.GetCall("public.getAddress", formBody);
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
                MyUtils.e("------获取首页结果------",   result);
                Message message = new Message();
                message.what = 0;
                Bundle bundle = new Bundle();
                bundle.putString("result", result);
                message.setData(bundle);
                myHandler.sendMessage(message);
            }
        });
    }


    private TextView mHomeText;
    private LinearLayout mMoreLl;
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
                            JSONObject jsonObject1 = new JSONObject(data);
                            JSONArray newslist=jsonObject1.getJSONArray("newslist");
                            JSONArray collegelist=jsonObject1.getJSONArray("collegelist");
                            mRanknum = jsonObject1.getString("ranknum");
                            mNewsArrayList = new ArrayList<>();
                            mCourseArrayList = new ArrayList<>();
                            for (int i = 0; i < newslist.length(); i++) {
                                mNewsArrayList.add(gson.fromJson(newslist.getJSONObject(i).toString(),News.class));
                            }
                            for (int i = 0; i < collegelist.length(); i++) {
                                mCourseArrayList.add(gson.fromJson(collegelist.getJSONObject(i).toString(),Course.class));
                            }
                            mCourseAdapter = new CourseAdapter(R.layout.course_item, mCourseArrayList);
                            mNewsAdapter = new NewsAdapter(R.layout.news_item, mNewsArrayList);
                            View top=LayoutInflater.from(mActivity).inflate(R.layout.home_top,null);
                            rvTop=top.findViewById(R.id.rv_top);
                            mMoreLl =top.findViewById(R.id.more_ll);
                            mMoreLl.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    startActivity(new Intent(mActivity,MessageActivity.class));
                                }
                            });
                            mHomeText =top.findViewById(R.id.home_text);
//                            mHomeText.setText(Html.fromHtml("尊贵的<span style='color:#33A900; font-size:200'>"+Global.loginResult.getInfo().getTruename()+"<span/>您好！\n恭喜您成为伊穆家园第<span style='color:#33A900; font-size:20; font_weight:bold'>"+mRanknum+"<span/>位会员！"));
                            SpannableString styledText = new SpannableString("尊贵的"+Global.loginResult.getInfo().getTruename()+"您好！\n恭喜您成为伊穆家园第"+mRanknum+"位会员！");

                            styledText.setSpan(new TextAppearanceSpan(mActivity, R.style.style0), 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            styledText.setSpan(new TextAppearanceSpan(mActivity, R.style.style1), 3, ("尊贵的"+Global.loginResult.getInfo().getTruename()).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            styledText.setSpan(new TextAppearanceSpan(mActivity, R.style.style0), ("尊贵的"+Global.loginResult.getInfo().getTruename()).length(), ("尊贵的"+Global.loginResult.getInfo().getTruename()+"您好！").length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            styledText.setSpan(new TextAppearanceSpan(mActivity, R.style.style2), ("尊贵的"+Global.loginResult.getInfo().getTruename()+"您好！").length(), ("尊贵的"+Global.loginResult.getInfo().getTruename()+"您好！\n恭喜您成为伊穆家园第").length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            styledText.setSpan(new TextAppearanceSpan(mActivity, R.style.style3), ("尊贵的"+Global.loginResult.getInfo().getTruename()+"您好！\n恭喜您成为伊穆家园第").length(), ("尊贵的"+Global.loginResult.getInfo().getTruename()+"您好！\n恭喜您成为伊穆家园第"+mRanknum).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            styledText.setSpan(new TextAppearanceSpan(mActivity, R.style.style2), ("尊贵的"+Global.loginResult.getInfo().getTruename()+"您好！\n恭喜您成为伊穆家园第"+mRanknum).length(), ("尊贵的"+Global.loginResult.getInfo().getTruename()+"您好！\n恭喜您成为伊穆家园第"+mRanknum+"位会员！").length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                            mHomeText.setText(styledText, TextView.BufferType.SPANNABLE);

                            mCourseAdapter.addHeaderView(top);
                            mCourseAdapter.openLoadAnimation();
                            mCourseAdapter.isFirstOnly(false);
                            mCourseAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
                            rvTop.setLayoutManager(new LinearLayoutManager(mActivity));
                            rvTop.addItemDecoration(new RecyclerViewDivider(mActivity, LinearLayoutManager.HORIZONTAL, 2, Color.parseColor("#E6E6E6")));
                            rvTop.setAdapter(mNewsAdapter);
                            rvHome.setAdapter(mCourseAdapter);
                            mCourseAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent=new Intent(mActivity, CourseDetailActivity.class);
                                    intent.putExtra("collegeid", mCourseArrayList.get(position).getId());
                                    startActivity(intent);
                                }
                            });
                            mNewsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent=new Intent(mActivity, NewsDetailActivity.class);
                                    intent.putExtra("newsid", mNewsArrayList.get(position).getId());
                                    startActivity(intent);
                                }
                            });
                        } else {
                            MyUtils.showToast(mActivity, message);
                        }
                        refreshLayout.finishRefresh();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        refreshLayout.finishRefresh();
                    }
                    break;

            }

        }
    };


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {

    }
}
