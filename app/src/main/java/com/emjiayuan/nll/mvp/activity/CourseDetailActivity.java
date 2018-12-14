package com.emjiayuan.nll.mvp.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.emjiayuan.nll.R;
import com.emjiayuan.nll.base.BaseActivity;
import com.emjiayuan.nll.utils.MyOkHttp;
import com.emjiayuan.nll.utils.MyUtils;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

public class CourseDetailActivity extends BaseActivity {
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.icon_back)
    ImageView mIconBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.icon_search)
    ImageView mIconSearch;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    private String url = "";
    private String collegeid;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initData() {
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText("详情");
        collegeid=getIntent().getStringExtra("collegeid");
        getCollegeDetail();
    }

    @Override
    protected void initView() {

    }


    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void getCollegeDetail() {
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("collegeid", collegeid);
        Log.d("------参数------", formBody.build().toString());
//new call
        Call call = MyOkHttp.GetCall("College.getCollegeDetail", formBody);
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
                MyUtils.e("-----课程详情-------", result);
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
                            JSONObject dataJson = new JSONObject(data);
                            String html = "<!DOCTYPE html>\n" +
                                    "<html lang=\"en\">\n" +
                                    "<head>\n" +
                                    "\t<meta charset=\"utf-8\">\n" +
                                    "\t<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                                    "\t\n" +
                                    "\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                                    "\n" +
                                    "\t<title>detail</title>\n" +
                                    "\n" +
                                    "\t<style>*{padding:0;margin:0;}img{width:100%;padding:0;margin:0;}</style>\n" +
                                    "</head>"
                                    + "<body>"
                                    + dataJson.getString("content") + "</body>" + "</html>";

                            webview.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
                            webview.getSettings().setJavaScriptEnabled(true);
                            webview.setWebChromeClient(new WebChromeClient());
                        } else {

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };
}
