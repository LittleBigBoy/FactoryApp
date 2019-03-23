package com.zhenhaikj.factoryside.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AccountAndSecurityActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.view)
    View mView;
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
    @BindView(R.id.ll_login_password)
    LinearLayout mLlLoginPassword;
    @BindView(R.id.ll_payment_password)
    LinearLayout mLlPaymentPassword;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_account_and_security;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText("账户与安全");
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mLlLoginPassword.setOnClickListener(this);
        mLlPaymentPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.icon_back:
                finish();
                break;
            case R.id.ll_login_password:
                startActivity(new Intent(mActivity, ChagePasswordActivity.class));
                break;
            case R.id.ll_payment_password:
                startActivity(new Intent(mActivity,PaymentPasswordActivity.class));
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
