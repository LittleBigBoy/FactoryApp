package com.emjiayuan.nll.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.emjiayuan.nll.Global;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.activity.OrderDetailActivity;
import com.emjiayuan.nll.adapter.OrderAdapter;
import com.emjiayuan.nll.adapter.SoupOrderAdapter;
import com.emjiayuan.nll.base.BaseLazyFragment;
import com.emjiayuan.nll.model.Order;
import com.emjiayuan.nll.model.SoupOrder;
import com.emjiayuan.nll.utils.MyOkHttp;
import com.emjiayuan.nll.utils.MyUtils;
import com.emjiayuan.nll.widget.RecyclerViewDivider;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

public class ViewPagerFragment extends BaseLazyFragment {
    private static final String ARG_PARAM1 = "param1";//""全部 1待付款 2待发货 3待收货
    private static final String ARG_PARAM2 = "param2";//0普通订单 1汤料订单
    @BindView(R.id.rv_order)
    RecyclerView mRvOrder;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;


    private String mParam1;
    private String mParam2;

    private ArrayList<Order> mOrderArrayList=new ArrayList<>();
    private ArrayList<SoupOrder> mSoupOrderArrayList=new ArrayList<>();
    private OrderAdapter mOrderAdapter;
    private SoupOrderAdapter mSoupOrderAdapter;
    private int pageindex = 1;

    public ViewPagerFragment() {
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
    public static ViewPagerFragment newInstance(String param1, String param2) {
        ViewPagerFragment fragment = new ViewPagerFragment();
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
        return R.layout.fragment_order_page;
    }
    @Override
    protected void initData() {
        if ("0".equals(mParam2)){
            mOrderAdapter = new OrderAdapter(R.layout.order_item, mOrderArrayList);
            mOrderAdapter.setEmptyView(getEmptyView());
//            mOrderAdapter.openLoadAnimation();
//            mOrderAdapter.isFirstOnly(false);
//            mOrderAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
            mRvOrder.setAdapter(mOrderAdapter);
            mOrderAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent = new Intent(mActivity, OrderDetailActivity.class);
                    intent.putExtra("orderid", mOrderArrayList.get(position).getId());
                    startActivity(intent);
                }
            });
            getOrderList();
            mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(RefreshLayout refreshLayout) {
                    pageindex = 1;
                    mOrderArrayList.clear();
                    mRefreshLayout.setNoMoreData(false);
                    getOrderList();
                }
            });
            mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(RefreshLayout refreshLayout) {
                    pageindex++;
                    getOrderList();
                }
            });
        }else{
            mSoupOrderAdapter = new SoupOrderAdapter(R.layout.order_item, mSoupOrderArrayList);
            mSoupOrderAdapter.setEmptyView(getEmptyView());
//            mSoupOrderAdapter.openLoadAnimation();
//            mSoupOrderAdapter.isFirstOnly(false);
//            mSoupOrderAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
            mRvOrder.setAdapter(mSoupOrderAdapter);
            mSoupOrderAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                                    Intent intent = new Intent(mActivity, OrderDetailActivity.class);
//                                    intent.putExtra("collegeid", mOrderArrayList.get(position).getId());
//                                    startActivity(intent);
                }
            });
            mSoupOrderAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    switch (view.getId()){
                        case R.id.up_down:
                            View view1=adapter.getViewByPosition(R.id.rv_goods_in,position);
                            view1.setVisibility(view1.getVisibility()==View.VISIBLE?View.GONE:View.VISIBLE);
                            break;
                    }
                }
            });
            getSoupOrder();
            mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(RefreshLayout refreshLayout) {
                    pageindex = 1;
                    mSoupOrderArrayList.clear();
                    mRefreshLayout.setNoMoreData(false);
                    getSoupOrder();
                }
            });
            mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(RefreshLayout refreshLayout) {
                    pageindex++;
                    getSoupOrder();
                }
            });
        }

    }

    @Override
    protected void initView() {
        mRvOrder.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvOrder.addItemDecoration(new RecyclerViewDivider(mActivity, LinearLayoutManager.HORIZONTAL, 20, Color.parseColor("#EEEEEE")));
    }

    @Override
    protected void setListener() {

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {

    }

    public void getOrderList() {
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("userid", Global.loginResult.getId());
        formBody.add("status", mParam1);
        formBody.add("compute", "");
        formBody.add("isProduct", "1");
        formBody.add("type", "0");
        formBody.add("pageindex", Integer.toString(pageindex));
        formBody.add("pagesize", "1");
        Log.d("------参数------", formBody.build().toString());
//new call
        Call call = MyOkHttp.GetCall("order.getOrderList", formBody);
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
                MyUtils.e("------获取普通订单列表结果------", result);
                Message message = new Message();
                message.what = 0;
                Bundle bundle = new Bundle();
                bundle.putString("result", result);
                message.setData(bundle);
                myHandler.sendMessage(message);
            }
        });
    }

    public void getSoupOrder() {
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("userid", Global.loginResult.getId());
        formBody.add("type", mParam1);
        formBody.add("compute", "");
        formBody.add("isProduct", "1");
        formBody.add("pageindex", Integer.toString(pageindex));
        formBody.add("pagesize", "40");
        Log.d("------参数------", formBody.build().toString());
//new call
        Call call = MyOkHttp.GetCall("soupOrder.getSoupOrderList", formBody);
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
                MyUtils.e("------获取汤料订单列表结果------", result);
                Message message = new Message();
                message.what = 1;
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
                            for (int i = 0; i < dataArray.length(); i++) {
                                mOrderArrayList.add(gson.fromJson(dataArray.getJSONObject(i).toString(), Order.class));
                            }
                            mOrderAdapter.setNewData(mOrderArrayList);
                        } else {
                            if (pageindex!=1){
                                mRefreshLayout.setNoMoreData(true);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    mRefreshLayout.finishRefresh();
                    mRefreshLayout.finishLoadMore();
                    break;
                case 1:
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String code = jsonObject.getString("code");
                        String message = jsonObject.getString("message");
                        String data = jsonObject.getString("data");
                        Gson gson = new Gson();
                        if ("200".equals(code)) {
//                            stateLayout.changeState(StateFrameLayout.SUCCESS);
                            JSONArray dataArray = new JSONArray(data);
                            mSoupOrderArrayList = new ArrayList<>();
                            for (int i = 0; i < dataArray.length(); i++) {
                                mSoupOrderArrayList.add(gson.fromJson(dataArray.getJSONObject(i).toString(), SoupOrder.class));
                            }
                            mSoupOrderAdapter.setNewData(mSoupOrderArrayList);
                        } else {
                            if (pageindex!=1){
                                mRefreshLayout.setNoMoreData(true);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    mRefreshLayout.finishRefresh();
                    mRefreshLayout.finishLoadMore();
                    break;
            }
        }
    };
}
