package com.emjiayuan.nll.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.emjiayuan.nll.Global;
import com.emjiayuan.nll.MainActivity;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.base.BaseActivity;
import com.emjiayuan.nll.model.LoginResult;
import com.emjiayuan.nll.model.UserInfo;
import com.emjiayuan.nll.utils.MyOkHttp;
import com.emjiayuan.nll.utils.MyUtils;
import com.emjiayuan.nll.utils.SpUtils;
import com.emjiayuan.nll.widget.RatioImageView;
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

public class LoginActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.icon_back)
    ImageView mIconBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.icon_search)
    ImageView mIconSearch;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.im_login)
    RatioImageView mImLogin;
    @BindView(R.id.et_username)
    EditText mEtUsername;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.login)
    Button mLogin;
    @BindView(R.id.register)
    TextView mRegister;
    @BindView(R.id.password_forget)
    TextView mPasswordForget;
    private String mUserName;
    private String mPassword;
    private UserInfo mUserInfo;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        Glide.with(mActivity).load(R.drawable.verify).into(mImLogin);
        mIconBack.setVisibility(View.INVISIBLE);
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText("账号登录");
        Global.loginResult=SpUtils.getObject(mActivity,"loginResult");
        if (Global.loginResult!=null){
            startActivity(new Intent(mActivity,MainActivity.class));
            finish();
        }
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mIconSearch.setOnClickListener(this);
        mLogin.setOnClickListener(this);
        mRegister.setOnClickListener(this);
        mPasswordForget.setOnClickListener(this);
    }
    public void getUserInfo(){
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("userid", Global.loginResult.getId());//传递键值对参数
//new call
        Call call = MyOkHttp.GetCall("User.getUserInfo", formBody);
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
                MyUtils.e("-----用户信息结果-------", result);
                Message message = new Message();
                message.what = 1;
                Bundle bundle = new Bundle();
                bundle.putString("result", result);
                message.setData(bundle);
                myHandler.sendMessage(message);
            }
        });
    }

    public void login(){
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("username", mUserName);//传递键值对参数
        formBody.add("password", mPassword);//传递键值对参数
//new call
        Call call = MyOkHttp.GetCall("User.login", formBody);
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
                MyUtils.e("-----登录结果-------", result);
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
                            Global.loginResult = new Gson().fromJson(data, LoginResult.class);
                            SpUtils.putObject(mActivity, "loginResult", Global.loginResult);
                            /*status 状态 -1=禁用 0=默认 1=待审核(已提交资料) 2=不通过(资料不正确) 3=待支付(通过审核) 10=正常(已支付)*/
                            mUserInfo=Global.loginResult.getInfo();
                            if ("0".equals(Global.loginResult.getStatus())){
                                if (null==mUserInfo.getTruename()){
                                    startActivity(new Intent(mActivity,PersonalInfoActivity.class));
                                }
                                if (null==mUserInfo.getShop_name()){
                                    startActivity(new Intent(mActivity,ShopInfoActivity.class));
                                }
                            }
                            if ("1".equals(Global.loginResult.getStatus())){
                                startActivity(new Intent(mActivity,VerifyActivity.class));
                            }
                            if ("2".equals(Global.loginResult.getStatus())){
                                Intent intent=new Intent(mActivity,RejectActivity.class);
                                intent.putExtra("userInfo",mUserInfo);
                                startActivity(intent);
                            }
                            if ("3".equals(Global.loginResult.getStatus())){
                                startActivity(new Intent(mActivity,VipActivity.class));
                            }
                            if ("10".equals(Global.loginResult.getStatus())){
                                startActivity(new Intent(mActivity,VipActivity.class));
//                                startActivity(new Intent(mActivity,MainActivity.class));
                            }
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
            case R.id.icon_search:
                finish();
                break;
            case R.id.register:
                startActivity(new Intent(mActivity,PhoneActivity.class));
                break;
            case R.id.password_forget:
                startActivity(new Intent(mActivity,ForgetPwdActivity.class));
                break;
            case R.id.login:
                mUserName = mEtUsername.getText().toString().trim();
                mPassword = mEtPassword.getText().toString().trim();
                if ("".equals(mUserName)) {
                    MyUtils.showToast(mActivity, "请输入手机号！");
                    return;
                }
                if ("".equals(mPassword)) {
                    MyUtils.showToast(mActivity, "请输入密码！");
                    return;
                }
                login();
                break;
        }
    }
}