package com.emjiayuan.nll.mvp.activity;

import android.content.Intent;
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
import com.emjiayuan.nll.adapter.JudgeAdapter;
import com.emjiayuan.nll.base.BaseActivity;
import com.emjiayuan.nll.event.UpdateEvent;
import com.emjiayuan.nll.model.Order;
import com.emjiayuan.nll.utils.MyOkHttp;
import com.emjiayuan.nll.utils.MyUtils;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
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

public class JudgeActivity extends BaseActivity {


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
    @BindView(R.id.lv_goods)
    ListView mLvGoods;
    private Order order;
    private ArrayList<Order.ProductListBean> list = new ArrayList();
    private JudgeAdapter adapter;
    private String commentdata;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_judge;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        order = (Order) intent.getSerializableExtra("order");
        mTvTitle.setText("发表评价");
        mTvTitle.setVisibility(View.VISIBLE);
        mTvSave.setVisibility(View.VISIBLE);
        mTvSave.setText("发布");
        mTvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!MyUtils.isFastClick()) {
                    return;
                }
                if (adapter != null) {
                    commentdata = adapter.getCommentdata();
                    if (commentdata != null) {
                        addProductComment();
                    }
                }
            }
        });
        mIconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!MyUtils.isFastClick()) {
                    return;
                }
                finish();
            }
        });
        adapter = new JudgeAdapter(mActivity, order.getProduct_list());
        mLvGoods.setAdapter(adapter);
    }

    public void saveEditData(int position, String str) {
        Gson gson = new Gson();
        str = gson.toJson(order.getProduct_list());
        MyUtils.e("json", str);
        MyUtils.showToast(this, str + "----" + position);
    }

    public void addProductComment() {

        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体

        formBody.add("userid", Global.loginResult.getId());
        formBody.add("orderid", order.getId());
        formBody.add("commentdata", commentdata);

        Log.d("------参数------", formBody.build().toString());
//new call
        Call call = MyOkHttp.GetCall("product.addProductComment", formBody);
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
                MyUtils.e("------评价------", result);
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
                            MyUtils.showToast(mActivity, message);
                            EventBus.getDefault().post(new UpdateEvent(""));
                            finish();
                        }else{
                            MyUtils.showToast(mActivity, message);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };

    @Override
    protected void setListener() {

    }
}
