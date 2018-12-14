package com.emjiayuan.nll.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.emjiayuan.nll.R;
import com.emjiayuan.nll.adapter.LogisticsDetailAdapter;
import com.emjiayuan.nll.base.BaseActivity;
import com.emjiayuan.nll.model.LogisticsBean;
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

public class LogisticsDetailActivity extends BaseActivity implements View.OnClickListener {


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
    @BindView(R.id.order_num)
    TextView mOrderNum;
    @BindView(R.id.wuliu_num)
    TextView mWuliuNum;
    @BindView(R.id.address)
    TextView mAddress;
    @BindView(R.id.time)
    TextView mTime;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    private LogisticsDetailAdapter adapter;
    private ArrayList<LogisticsBean> list = new ArrayList<>();
    private String postid = "";
    private String postcom = "";
    private String orderno = "";
    private String date = "";
    private String Address = "";
    private Order order;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_logistics_detail;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvTitle.setText("物流详细");
        mTvTitle.setVisibility(View.VISIBLE);
        Intent intent = getIntent();
        if (intent != null) {
            postid = intent.getStringExtra("postid");
            postcom = intent.getStringExtra("postcom");
            orderno = intent.getStringExtra("order_no");
            date = intent.getStringExtra("date");
            Address = intent.getStringExtra("address");
        }
//        order= (Order) intent.getSerializableExtra("logistics");
//        postid=order.getExpressno();
//        postcom=order.getExpresscom();
//        order_num.setText("订单号："+order.getOrder_no());
//        wuliu_num.setText("物流编号："+order.getExpressno());
//        address.setText("收货地址："+order.getAddress());
//        time.setText("下单时间："+order.getCreatedate());
        mOrderNum.setText("订单号：" + orderno);
        mWuliuNum.setText("物流编号：" + postid);
        mAddress.setText("收货地址：" + Address);
        mTime.setText("下单时间：" + date);
        getlist();
        //构造与装配布局管理器
        mRvList.setLayoutManager(new LinearLayoutManager(mActivity));

    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
    }

    public void getlist() {
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体

        formBody.add("postid", postid);
        formBody.add("postcom", postcom);

        Log.d("------参数------", formBody.build().toString());
//new call
        Call call = MyOkHttp.GetCall("public.getExpress", formBody);
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
                MyUtils.e("------获取物流详情------", result);
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
                                list.add(gson.fromJson(jsonArray.getJSONObject(i).toString(), LogisticsBean.class));
                            }
                            adapter = new LogisticsDetailAdapter(R.layout.logistics_recycle_item, list);
                            mRvList.setAdapter(adapter);
                        } else {
                            MyUtils.showToast(LogisticsDetailActivity.this, message);
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
