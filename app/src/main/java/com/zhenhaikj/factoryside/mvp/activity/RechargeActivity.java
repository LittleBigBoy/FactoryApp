package com.zhenhaikj.factoryside.mvp.activity;

import android.view.View;

import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;


public class RechargeActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int setLayoutId() {
        return R.layout.activity_recharge ;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
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