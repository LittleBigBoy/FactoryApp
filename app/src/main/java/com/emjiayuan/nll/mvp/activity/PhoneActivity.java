package com.emjiayuan.nll.mvp.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.emjiayuan.nll.R;
import com.emjiayuan.nll.base.BaseActivity;
import com.emjiayuan.nll.utils.MyOkHttp;
import com.emjiayuan.nll.utils.MyUtils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

public class PhoneActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.icon_back)
    ImageView mIconBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.icon_search)
    ImageView mIconSearch;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.et_username)
    EditText mEtUsername;
    @BindView(R.id.et_yzm)
    EditText mEtYzm;
    @BindView(R.id.get_yzm)
    TextView mGetYzm;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.et_repassword)
    EditText mEtRepassword;
    @BindView(R.id.btn_next)
    Button mBtnNext;
    @BindView(R.id.no_yzm)
    TextView mNoYzm;
    private String mUserName;
    private String mYzm;
    private String mPassword;
    private String mRepassword;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_phone;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mIconSearch.setOnClickListener(this);
        mGetYzm.setOnClickListener(this);
        mBtnNext.setOnClickListener(this);
        mNoYzm.setOnClickListener(this);
    }

    public void register(){
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("username", mUserName);//传递键值对参数
        formBody.add("password", mPassword);//传递键值对参数
        formBody.add("confirmpassword", mRepassword);//传递键值对参数
        formBody.add("smscode", mYzm);//传递键值对参数
//new call
        Call call = MyOkHttp.GetCall("User.register", formBody);
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
                MyUtils.e("-----注册结果-------", result);
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
                            startActivity(new Intent(mActivity,PersonalInfoActivity.class));
                            finish();
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
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.get_yzm:
                String username = mEtUsername.getText().toString();
                if ("".equals(username)) {
                    MyUtils.showToast(mActivity, "用户名不能为空！");
                    return;
                }
                TimeCount time = new TimeCount(60000, 1000);
                time.start();
                FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
                formBody.add("telphone", username);//传递键值对参数
                formBody.add("sendtype", "1");//传递键值对参数
//new call
                Call call = MyOkHttp.GetCall("system.sendUserSms", formBody);
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
                        Log.d("------获取验证码结果------", result);
                    }
                });
                break;
            case R.id.no_yzm:
                AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                builder.setTitle("温馨提示");
                builder.setMessage("是否拨打客服电话4008123337？");
                builder.setCancelable(true);

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        Uri data = Uri.parse("tel:" + "4008123337");
                        intent.setData(data);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        /*
                         *  在这里实现你自己的逻辑
                         */
                    }
                });
                builder.create().show();
                break;
            case R.id.btn_next:
                mUserName = mEtUsername.getText().toString().trim();
                mYzm = mEtYzm.getText().toString().trim();
                mPassword = mEtPassword.getText().toString().trim();
                mRepassword = mEtRepassword.getText().toString().trim();
                if ("".equals(mUserName)){
                    MyUtils.showToast(mActivity,"请输入手机号！");
                    return;
                }
                if ("".equals(mYzm)){
                    MyUtils.showToast(mActivity,"请输入验证码！");
                    return;
                }
                if ("".equals(mPassword)){
                    MyUtils.showToast(mActivity,"请输入密码！");
                    return;
                }
                if ("".equals(mRepassword)){
                    MyUtils.showToast(mActivity,"请输入确认密码！");
                    return;
                }
                if (!mPassword.equals(mRepassword)){
                    MyUtils.showToast(mActivity,"两次密码不一致！");
                    return;
                }
                register();
                break;
        }
    }
    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
//            get_yzm.setBackgroundColor(Color.parseColor("#B6B6D8"));
            if (mGetYzm==null){
                return;
            }
            mGetYzm.setClickable(false);
            mGetYzm.setText(millisUntilFinished / 1000 + "s");
        }

        @Override
        public void onFinish() {
            if (mGetYzm==null){
                return;
            }
            mGetYzm.setText("重新获取验证码");
            mGetYzm.setClickable(true);
//            get_yzm.setBackgroundColor(Color.parseColor("#4EB84A"));

        }
    }
}