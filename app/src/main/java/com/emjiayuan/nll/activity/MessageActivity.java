package com.emjiayuan.nll.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.adapter.NewsAdapter;
import com.emjiayuan.nll.base.BaseActivity;
import com.emjiayuan.nll.model.News;
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

public class MessageActivity extends BaseActivity {


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
    @BindView(R.id.rv_message)
    RecyclerView mRvMessage;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_message;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvTitle.setText("伊穆头条");
        mTvTitle.setVisibility(View.VISIBLE);
        mIconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!MyUtils.isFastClick()) {
                    return;
                }
                finish();
            }
        });
        getNewsList();
    }
    public void getNewsList() {
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
//new call
        Call call = MyOkHttp.GetCall("news.getNewsList", formBody);
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
                MyUtils.e("------获取头条------",   result);
                Message message = new Message();
                message.what = 0;
                Bundle bundle = new Bundle();
                bundle.putString("result", result);
                message.setData(bundle);
                myHandler.sendMessage(message);
            }
        });
    }

    private ArrayList<News> mNewsArrayList;
    private NewsAdapter mNewsAdapter;
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
                            JSONArray newslist = new JSONArray(data);
                            mNewsArrayList = new ArrayList<>();
                            for (int i = 0; i < newslist.length(); i++) {
                                mNewsArrayList.add(gson.fromJson(newslist.getJSONObject(i).toString(),News.class));
                            }
                            mNewsAdapter = new NewsAdapter(R.layout.news_item, mNewsArrayList);
                            mRvMessage.setLayoutManager(new LinearLayoutManager(mActivity));
                            mRvMessage.addItemDecoration(new RecyclerViewDivider(mActivity, LinearLayoutManager.HORIZONTAL, 2, Color.parseColor("#E6E6E6")));
                            mNewsAdapter.setEmptyView(getEmptyView());
                            mRvMessage.setAdapter(mNewsAdapter);
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
