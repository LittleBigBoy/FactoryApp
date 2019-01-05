package com.emjiayuan.factoryside.mvp.activity.soup;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.emjiayuan.nll.Global;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.base.BaseActivity;
import com.emjiayuan.nll.model.SoupOrder;
import com.emjiayuan.nll.utils.MyOkHttp;
import com.emjiayuan.nll.utils.MyUtils;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

public class MySoupOrderActivity extends BaseActivity implements View.OnClickListener {
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
    @BindView(R.id.lv_list)
    ListView mLvList;

    private MySoupOrderAdapter adapter;
    private ArrayList<SoupOrder> soupOrders = new ArrayList<>();

    @Override
    protected int setLayoutId() {
        return R.layout.activity_my_soup;
    }

    @Override
    protected void initData() {
        mTvTitle.setText("我的料单");
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
//        formBody.add("status", order_type);
//        formBody.add("type", order_type);
        formBody.add("isProduct", "1");
        formBody.add("pageindex", "1");
        formBody.add("pagesize", "100");
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
                MyUtils.e("------获取料单列表------", result);
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
                                soupOrders.add(gson.fromJson(jsonArray.getJSONObject(i).toString(), SoupOrder.class));
                            }
                            adapter = new MySoupOrderAdapter(mActivity, soupOrders);
                            mLvList.setAdapter(adapter);
//                            MyUtils.showToast(LogisticsActivity.this, result);
                        } else {
                            MyUtils.showToast(MySoupOrderActivity.this, message);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case 1:
//                    MyUtils.showToast(LoginActivity.this,result);
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
            case R.id.back:
                finish();
                break;
        }
    }

}
