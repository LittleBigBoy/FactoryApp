package com.zhenhaikj.factoryside.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.MainActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
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
    ClearEditText mEtVerificationCode;
    @BindView(R.id.tv_get_verification_code)
    TextView mTvGetVerificationCode;
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
        mEtUsername.setText("admin");
        mEtPassword.setText("123");
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
                break;
            case R.id.tv_agreement:
                break;
            case R.id.tv_register:
                startActivity(new Intent(mActivity, RegisterActivity.class));
                break;
            case R.id.tv_change:
                if (mLlCode.getVisibility()==View.GONE){
                    mLlCode.setVisibility(View.VISIBLE);
                    mLlPassword.setVisibility(View.GONE);
                    mTvChange.setText("密码登录>");
                }else{
                    mLlCode.setVisibility(View.GONE);
                    mLlPassword.setVisibility(View.VISIBLE);
                    mTvChange.setText("短信验证码登录>");
                }
                    break;
            case R.id.iv_qq:
                break;
            case R.id.iv_weixin:
                break;
            case R.id.iv_weibo:
                break;
        }
    }

    @Override
    public void Login(BaseResult<String> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                SPUtils spUtils = SPUtils.getInstance("token");
                spUtils.put("adminToken", baseResult.getData());
                spUtils.put("userName", userName);
//                GetUserInfo getUserInfo=new GetUserInfo(userName,baseResult.getData(),"","");
//                Gson gson=new Gson();
//                RequestBody json=RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"),gson.toJson(getUserInfo));
//                mPresenter.GetUserInfo(json);
//                mPresenter.GetUserInfo(userName);
                startActivity(new Intent(mActivity, MainActivity.class));
                finish();
                break;
            case 401:
                ToastUtils.showShort(baseResult.getData());
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}