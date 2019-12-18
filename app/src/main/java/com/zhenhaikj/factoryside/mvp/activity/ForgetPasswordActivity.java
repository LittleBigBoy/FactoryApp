package com.zhenhaikj.factoryside.mvp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.tencent.android.tpush.XGPushConfig;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.MainActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.contract.ForgetPasswordContract;
import com.zhenhaikj.factoryside.mvp.model.ForgetPasswordModel;
import com.zhenhaikj.factoryside.mvp.presenter.ForgetPasswordPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgetPasswordActivity extends BaseActivity<ForgetPasswordPresenter, ForgetPasswordModel> implements View.OnClickListener, ForgetPasswordContract.View {


    @BindView(R.id.img_forget_back)
    ImageView mImgForgetBack;
    @BindView(R.id.et_forget_phone)
    EditText mEtForgetPhone;
    @BindView(R.id.et_forget_yzm)
    EditText mEtForgetYzm;
    @BindView(R.id.tv_send_yzm)
    TextView mTvSendYzm;
    @BindView(R.id.et_forget_password)
    EditText mEtForgetPassword;
    @BindView(R.id.et_forget_password_again)
    EditText mEtForgetPasswordAgain;
    @BindView(R.id.tv_login)
    TextView mTvLogin;

    private String phone;
    private String password;
    private String passwordagain;
    private SPUtils spUtils;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_forgetpassword;
    }

    @Override
    protected void initData() {
     /*   phone=getIntent().getStringExtra("phone");
        if ("".equals(phone)){
            mEtForgetPhone.setText("");
        }else {
            mEtForgetPhone.setText(phone);
            mEtForgetPhone.setSelection(mEtForgetPhone.getText().toString().length());
        }*/


    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mImgForgetBack.setOnClickListener(this);
        mTvSendYzm.setOnClickListener(this);
        mTvLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_forget_back:
                ForgetPasswordActivity.this.finish();
                break;
            case R.id.tv_send_yzm:
                phone=mEtForgetPhone.getText().toString();
                if (phone.isEmpty()){
                    ToastUtils.showShort("请输入手机号");
                    return;
                }
                if (!RegexUtils.isMobileExact(phone)){
                    ToastUtils.showShort("手机格式不正确！");
                    return;
                }
                mPresenter.ValidateUserName(phone);
                break;
                case R.id.tv_login:

                    password = mEtForgetPassword.getText().toString();
                    passwordagain = mEtForgetPasswordAgain.getText().toString();
                    phone=mEtForgetPhone.getText().toString();
                    String code=mEtForgetYzm.getText().toString();
                    if (phone.isEmpty()){
                        ToastUtils.showShort("请输入手机号");
                        return;
                    }
                    if (!RegexUtils.isMobileExact(phone)){
                        ToastUtils.showShort("手机格式不正确！");
                        return;
                    }
                    if ("".equals(code)){
                        ToastUtils.showShort("请输入验证码！");
                        return;
                    }

                    if ("".equals(password)||"".equals(passwordagain)){
                        ToastUtils.showShort("请输入密码！");
                        return;
                    }

                    if (!password.equals(passwordagain)){
                        ToastUtils.showShort("两次密码不一致！");
                        return;
                    }

                    mPresenter.ForgetPassword(phone,"2",code, password);



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
    public void Login(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<String> data = baseResult.getData();
                if (data.isItem1()) {
                    spUtils = SPUtils.getInstance("token");
                    spUtils.put("adminToken", data.getItem2());
                    spUtils.put("userName", phone);
                    spUtils.put("passWord", password);
                    spUtils.put("isLogin", true);
                    mPresenter.AddAndUpdatePushAccount(XGPushConfig.getToken(this), "7", phone);
                    startActivity(new Intent(mActivity, MainActivity.class));
                    ActivityUtils.finishAllActivities();
                } else {
                }
                hideProgress();
                break;
        }
    }

    @Override
    public void AddAndUpdatePushAccount(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void GetCode(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                if (baseResult.getData().isItem1()){

                }
                else {
                    Toast.makeText(this,"频繁请求验证码请稍后再试",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
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
                    mPresenter.GetCode(phone,"2");
                }
                break;
            case 401:
                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }

//    @Override
//    public void LoginOnMessage(BaseResult<Data<String>> baseResult) {
//
//    }

    @Override
    public void ForgetPassword(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                if (baseResult.getData().isItem1()){
                    ToastUtils.showShort("修改成功");
                    showProgress();
                    mPresenter.Login(phone,password);
                }else {
                    ToastUtils.showShort(baseResult.getData().getItem2());
                }

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
            if (mTvSendYzm==null){
                return;
            }
            mTvSendYzm.setClickable(false);
            mTvSendYzm.setTextColor(R.color.red);
            mTvSendYzm.setText(millisUntilFinished/1000+"秒后重新获取");
        }

        @Override
        public void onFinish() {
            if (mTvSendYzm==null){
                return;
            }
            mTvSendYzm.setText("重新获取验证码");
            mTvSendYzm.setClickable(true);
        }
    }
}
