package com.emjiayuan.nll.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.emjiayuan.nll.Global;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.adapter.LogisticsAdapter;
import com.emjiayuan.nll.base.BaseActivity;
import com.emjiayuan.nll.model.Order;
import com.emjiayuan.nll.utils.MyOkHttp;
import com.emjiayuan.nll.utils.MyUtils;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
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

public class LogisticsActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.icon_back)
    ImageView mIconBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.icon_search)
    ImageView mIconSearch;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rv_logistics)
    RecyclerView mRvLogistics;
    private LogisticsAdapter adapter;
    private ArrayList<Order> LogisticsList = new ArrayList<>();

    @Override
    protected int setLayoutId() {
        return R.layout.activity_logistics;
    }

    @Override
    protected void initData() {
        mTvTitle.setText("物流收货");
        mTvTitle.setVisibility(View.VISIBLE);
        getlist();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
    }

    public void getlist() {
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("userid", Global.loginResult.getId());
        formBody.add("status", "3");
        formBody.add("pageindex", "1");
        formBody.add("pagesize", "100");
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
                MyUtils.e("------获取物流列表------", result);
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
                            JSONArray jsonArray = new JSONArray(data);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                LogisticsList.add(gson.fromJson(jsonArray.getJSONObject(i).toString(), Order.class));
                            }
                            adapter = new LogisticsAdapter(R.layout.logistics_item, LogisticsList);
                            mRvLogistics.setLayoutManager(new LinearLayoutManager(mActivity));
                            adapter.setEmptyView(getEmptyView());
                            adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                @Override
                                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                    switch (view.getId()){
                                        case R.id.see:
                                            if (!MyUtils.isFastClick()){
                                                return;
                                            }
                                            Order item=LogisticsList.get(position);
                                            Intent intent=new Intent(mActivity, LogisticsDetailActivity.class);
                                            intent.putExtra("postid",item.getExpressno());
                                            intent.putExtra("postcom",item.getExpresscom());
                                            intent.putExtra("order_no",item.getOrder_no());
                                            intent.putExtra("date",item.getCreatedate());
                                            intent.putExtra("address",item.getAddress());
                                            startActivity(intent);
                                            break;
                                    }
                                }
                            });
                            mRvLogistics.setAdapter(adapter);
                        } else {
                            adapter = new LogisticsAdapter(R.layout.logistics_item, LogisticsList);
                            mRvLogistics.setLayoutManager(new LinearLayoutManager(mActivity));
                            adapter.setEmptyView(getEmptyView());
                            mRvLogistics.setAdapter(adapter);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };

    @Override
    public void onClick(View view) {
        if (!MyUtils.isFastClick()) {
            return;
        }
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
        }
    }

}
