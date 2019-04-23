package com.zhenhaikj.factoryside.mvp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.tencent.android.tpush.XGPushConfig;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.MainActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.contract.RegisterContract;
import com.zhenhaikj.factoryside.mvp.model.RegisterModel;
import com.zhenhaikj.factoryside.mvp.presenter.RegisterPresenter;
import com.zhenhaikj.factoryside.mvp.utils.SpUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterNewActivity extends BaseActivity<RegisterPresenter, RegisterModel> implements View.OnClickListener, RegisterContract.View {

    @BindView(R.id.img_register_back)
    ImageView mImgRegisterBack;
    @BindView(R.id.et_register_phone)
    EditText mEtRegisterPhone;
    @BindView(R.id.et_register_yzm)
    EditText mEtRegisterYzm;
    @BindView(R.id.tv_send_yzm)
    TextView mTvSendYzm;
    @BindView(R.id.et_register_password)
    EditText mEtRegisterPassword;
    @BindView(R.id.tv_register)
    TextView mTvRegister;
    @BindView(R.id.img_agreement)
    ImageView mImgAgreement;
    @BindView(R.id.tv_agreement)
    TextView mTvAgreement;
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.et_register_password_again)
    EditText mEtRegisterPasswordAgain;
    private String phone;
    private String password;
    private String passwordAgain;
    private String verificationCode;

    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarView(mView);
        mImmersionBar.keyboardEnable(true);
        mImmersionBar.init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_register_new;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mImgRegisterBack.setOnClickListener(this);
        mTvRegister.setOnClickListener(this);
        mImgAgreement.setOnClickListener(this);
        mTvAgreement.setOnClickListener(this);
        mTvSendYzm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_register_back:
                finish();
                break;
            case R.id.tv_send_yzm:
                phone = mEtRegisterPhone.getText().toString();
                if (phone.isEmpty()) {
                    ToastUtils.showShort("请输入手机号！");
                } else if (!RegexUtils.isMobileExact(phone)) {
                    ToastUtils.showShort("手机格式不正确！");
                } else {
                    mPresenter.ValidateUserName(phone);
                }
                break;
            case R.id.tv_register:
                phone = mEtRegisterPhone.getText().toString();
                password = mEtRegisterPassword.getText().toString();
                passwordAgain = mEtRegisterPasswordAgain.getText().toString();
                verificationCode = mEtRegisterYzm.getText().toString();
                if (phone.isEmpty()) {
                    ToastUtils.showShort("手机不能为空");
                } else if (verificationCode.isEmpty()) {
                    ToastUtils.showShort("验证码不能为空");
                } else if (password.isEmpty()) {
                    ToastUtils.showShort("密码不能为空");
                } else if (passwordAgain.isEmpty()) {
                    ToastUtils.showShort("请再次输入密码");
                } else if (mImgAgreement.isSelected()) {
                    ToastUtils.showShort("请阅读并同意用户协议");
                } else if (!password.equals(passwordAgain)) {
                    ToastUtils.showShort("两次密码不一致");
                } else {
                    mPresenter.Reg(phone, verificationCode, password);
                }
                break;
            case R.id.img_agreement:
                if (mImgAgreement.isSelected()) {
                    mImgAgreement.setSelected(false);
                } else {
                    mImgAgreement.setSelected(true);
                }
                break;
            case R.id.tv_agreement:
                Intent intent = new Intent(mActivity, WebActivity.class);
                intent.putExtra("title", "用户协议");
                intent.putExtra("Url", "http://47.96.126.145:8080/Agreement");
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void Reg(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    finish();
                    mPresenter.Login(phone, password);
                }else {
                    ToastUtils.showShort("验证码出错");
                }
                break;
        }
    }

    @Override
    public void GetCode(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                ToastUtils.showShort("验证码已发送");
                break;
        }
    }

    @Override
    public void Login(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                Data<String> data=baseResult.getData();
                if (data.isItem1()){
                    SPUtils spUtils=SPUtils.getInstance("token");
                    spUtils.put("adminToken", data.getItem2());
                    spUtils.put("userName", phone);
                    spUtils.put("passWord", password);
                    spUtils.put("isLogin", true);
                    mPresenter.AddAndUpdatePushAccount(XGPushConfig.getToken(this), "6", phone);
                    startActivity(new Intent(mActivity, MainActivity.class));
                    finish();
                }
                break;
        }
    }

    @Override
    public void ValidateUserName(BaseResult<String> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if ("true".equals(baseResult.getData())) {
                    TimeCount timeCount = new TimeCount(60000, 1000);
                    timeCount.start();
                    mPresenter.GetCode(phone, "Reg");
                } else {
                    ToastUtils.showShort("手机号已经注册！");
                }
                break;
        }

    }

    @Override
    public void AddAndUpdatePushAccount(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                break;
        }
    }

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @SuppressLint("ResourceAsColor")
        @Override
        public void onTick(long millisUntilFinished) {
            if (mTvSendYzm == null) {
                return;
            }
            mTvSendYzm.setClickable(false);
            mTvSendYzm.setTextColor(R.color.gray_three);
            mTvSendYzm.setText(millisUntilFinished / 1000 + "秒后重新获取");
        }

        @Override
        public void onFinish() {
            if (mTvSendYzm == null) {
                return;
            }
            mTvSendYzm.setText("重新获取验证码");
            mTvSendYzm.setClickable(true);
        }
    }
}
