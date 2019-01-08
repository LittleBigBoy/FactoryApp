package com.emjiayuan.factoryside.mvp.activity;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.emjiayuan.nll.R;
import com.emjiayuan.nll.base.BaseActivity;

import butterknife.BindView;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.et_username)
    EditText mEtUsername;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.et_verification_code)
    EditText mEtVerificationCode;
    @BindView(R.id.tv_get_verification_code)
    TextView mTvGetVerificationCode;
    @BindView(R.id.login)
    Button mLogin;
    @BindView(R.id.tv_register)
    TextView mTvRegister;
    @BindView(R.id.tv_password_login)
    TextView mTvPasswordLogin;
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

    }

    @Override
    protected void setListener() {
        mLogin.setOnClickListener(this);
    }



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
        }
    }
}