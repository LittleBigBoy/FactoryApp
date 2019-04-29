package com.zhenhaikj.factoryside.mvp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.bugly.crashreport.CrashReport;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.MainActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.contract.LoginContract;
import com.zhenhaikj.factoryside.mvp.model.LoginModel;
import com.zhenhaikj.factoryside.mvp.presenter.LoginPresenter;
import com.zhenhaikj.factoryside.mvp.utils.MyUtils;
import com.zhenhaikj.factoryside.mvp.widget.ClearEditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity<LoginPresenter, LoginModel> implements View.OnClickListener, LoginContract.View {


    @BindView(R.id.et_username)
    ClearEditText mEtUsername;
    @BindView(R.id.et_password)
    ClearEditText mEtPassword;
    @BindView(R.id.ll_password)
    LinearLayout mLlPassword;
    @BindView(R.id.et_verification_code)
    ClearEditText mEtVerificationCode; //验证码框
    @BindView(R.id.tv_get_verification_code)
    TextView mTvGetVerificationCode;//获取验证码
    @BindView(R.id.login)
    Button mLogin;
    @BindView(R.id.tv_register)
    TextView mTvRegister;
    @BindView(R.id.cb)
    CheckBox mCb;
    @BindView(R.id.tv_agreement)
    TextView mTvAgreement;
    @BindView(R.id.iv_qq)
    ImageView mIvQq;
    @BindView(R.id.iv_weixin)
    ImageView mIvWeixin;
    @BindView(R.id.iv_weibo)
    ImageView mIvWeibo;
    @BindView(R.id.tv_change)
    TextView mTvChange;
    @BindView(R.id.ll_code)
    LinearLayout mLlCode;
    private String userName;
    private String passWord;
    private String code;
    private SPUtils spUtils;
    private boolean isLogin;
    private int login_state=0;//默认为账号密码登陆
    @Override
    protected int setLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
        setSwipeBackEnable(false);
    }

    @Override
    protected void initView() {
//        mEtUsername.setText("admin");
//        mEtPassword.setText("123");
        spUtils = SPUtils.getInstance("token");
        userName=spUtils.getString("userName");
        passWord=spUtils.getString("passWord");
        isLogin =spUtils.getBoolean("isLogin");
        mEtUsername.setText(userName);
        mEtPassword.setText(passWord);
//        if (userName!=null&&passWord!=null&&isLogin){
//            mPresenter.Login(userName, passWord);
//        }
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarColor(R.color.white);
        mImmersionBar.fitsSystemWindows(false);
        mImmersionBar.keyboardEnable(true).navigationBarWithKitkatEnable(false).init();
    }

    @Override
    protected void setListener() {
        mLogin.setOnClickListener(this);
        mTvRegister.setOnClickListener(this);
        mTvChange.setOnClickListener(this);
        mIvQq.setOnClickListener(this);
        mIvWeixin.setOnClickListener(this);
        mIvWeibo.setOnClickListener(this);
        mTvAgreement.setOnClickListener(this);
        mTvGetVerificationCode.setOnClickListener(this);
        mCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                userName = mEtUsername.getText().toString();
                passWord = mEtPassword.getText().toString();
                code=mEtVerificationCode.getText().toString();
                if (login_state==0){

                    if ("".equals(userName)) {
                        ToastUtils.showShort("请输入手机号！");
                        return;
                    }
//                if (!RegexUtils.isMobileExact(userName)){
//                    ToastUtils.showShort("手机号格式不正确！");
//                    return;
//                }
                    if ("".equals(passWord)) {
                        ToastUtils.showShort("请输入密码！");
                        return;
                    }
                    mPresenter.Login(userName, passWord);
                }else {
                    if ("".equals(userName)) {
                        ToastUtils.showShort("请输入手机号！");
                        return;
                    }

                    if ("".equals(code)) {
                        ToastUtils.showShort("请输入验证码！");
                        return;
                    }
                    mPresenter.LoginOnMessage(userName,code);

                }
//
                break;
            case R.id.tv_agreement:
                Intent intent=new Intent(mActivity,WebActivity.class);
                intent.putExtra("Url","http://47.96.126.145:8080/Agreement");
                intent.putExtra("title","用户协议");
                startActivity(intent);
                break;
            case R.id.tv_register:
                startActivity(new Intent(mActivity, RegisterNewActivity.class));
                break;
            case R.id.tv_change:
                if (mLlCode.getVisibility()==View.GONE){
                    mLlCode.setVisibility(View.VISIBLE);
                    mLlPassword.setVisibility(View.GONE);
                    mTvChange.setText("密码登录>");
                    //状态为验证码登陆
                    login_state=1;

                }else{
                    mLlCode.setVisibility(View.GONE);
                    mLlPassword.setVisibility(View.VISIBLE);
                    mTvChange.setText("短信验证码登录>");
                    login_state=0;
                }
                    break;
            case R.id.iv_qq:
                break;
            case R.id.iv_weixin:
                break;
            case R.id.iv_weibo:
                break;
            case R.id.tv_get_verification_code:

                userName=mEtUsername.getText().toString();
                if (userName.isEmpty()){
                    ToastUtils.showShort("请输入手机号");
                    return;
                }
                if (!RegexUtils.isMobileExact(userName)){
                    ToastUtils.showShort("手机格式不正确！");
                    return;
                }
                mPresenter.ValidateUserName(userName);
                break;

        }
    }

    @Override
    public void Login(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<String> data=baseResult.getData();
                if (data.isItem1()){
                    spUtils.put("adminToken", data.getItem2());
                    spUtils.put("userName", userName);
                    spUtils.put("passWord", passWord);
                    spUtils.put("isLogin", true);
                    mPresenter.AddAndUpdatePushAccount(XGPushConfig.getToken(this),"6",userName);
                    startActivity(new Intent(mActivity, MainActivity.class));
                    finish();
                }else{
                    ToastUtils.showShort(data.getItem2());
                }
                break;
        }
    }

    @Override
    public void GetUserInfo(BaseResult<String> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                MyUtils.e("userInfo", baseResult.getData());
                ToastUtils.showShort(baseResult.getData());
                break;
            case 401:
                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }

    @Override
    public void AddAndUpdatePushAccount(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<String> data=baseResult.getData();
                if (data.isItem1()){

                }else{
                    ToastUtils.showShort(data.getItem2());
                }
                break;
        }
    }

    @Override
    public void ValidateUserName(BaseResult<String> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                if ("true".equals(baseResult.getData())){
                    ToastUtils.showShort("手机号还未注册！");
                }else {
                    TimeCount timeCount=new TimeCount(60000,1000);
                    timeCount.start();
                    mPresenter.GetCode(userName,"Login");
                }
                break;
            case 401:
                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }

    @Override
    public void GetCode(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void LoginOnMessage(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<String> data=baseResult.getData();
                if (data.isItem1()){
                    spUtils.put("adminToken", data.getItem2());
                    spUtils.put("userName", userName);
                   // spUtils.put("passWord", passWord);
                    spUtils.put("isLogin", true);
                    mPresenter.AddAndUpdatePushAccount(XGPushConfig.getToken(this),"6",userName);
                    startActivity(new Intent(mActivity, MainActivity.class));
                    finish();
                }else{
                    ToastUtils.showShort(data.getItem2());
                }
                break;
        }
    }

    @Override
    public void LoginOut(BaseResult<Data<String>> baseResult) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            if (mTvGetVerificationCode==null){
                return;
            }
            mTvGetVerificationCode.setClickable(false);
            mTvGetVerificationCode.setText(millisUntilFinished/1000+"秒后重新获取");
        }

        @Override
        public void onFinish() {
            if (mTvGetVerificationCode==null){
                return;
            }
            mTvGetVerificationCode.setText("重新获取验证码");
            mTvGetVerificationCode.setClickable(true);
        }
    }
}