package com.emjiayuan.nll.mvp.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.emjiayuan.nll.R;
import com.emjiayuan.nll.adapter.HelpAdapter;
import com.emjiayuan.nll.base.BaseActivity;
import com.emjiayuan.nll.model.HelpBean;
import com.emjiayuan.nll.utils.MyOkHttp;
import com.emjiayuan.nll.utils.MyUtils;
import com.emjiayuan.nll.widget.RecyclerViewDivider;
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

public class HelpActivity extends BaseActivity {

    @BindView(R.id.icon_back)
    ImageView mIconBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.icon_search)
    ImageView mIconSearch;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rv)
    RecyclerView mRv;
    private HelpAdapter adapter;
    private ArrayList<HelpBean> arrayList = new ArrayList<>();

    @Override
    protected int setLayoutId() {
        return R.layout.activity_help;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText("帮助中心");
        adapter = new HelpAdapter(R.layout.help_item, arrayList);
        adapter.setEmptyView(getEmptyView());
        mRv.setLayoutManager(new LinearLayoutManager(mActivity));
        mRv.addItemDecoration(new RecyclerViewDivider(mActivity,LinearLayoutManager.HORIZONTAL));
        mRv.setAdapter(adapter);
        request();
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {                if (!MyUtils.isFastClick()) {
                    return;
                }
                finish();
            }
        });
    }

    public void request() {
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体

//new call
        Call call = MyOkHttp.GetCall("public.getHelpList", formBody);
//请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("------------", e.toString());
//                myHandler.sendEmptyMessage(1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String result = response.body().string();
                MyUtils.e("-----帮助中心-------", result);
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
                            arrayList=new ArrayList<>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                arrayList.add(gson.fromJson(jsonArray.getJSONObject(i).toString(), HelpBean.class));
                            }
                            adapter.setNewData(arrayList);
                        } else {
                            arrayList=new ArrayList<>();
                            adapter.setNewData(arrayList);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }

        }
    };
}
