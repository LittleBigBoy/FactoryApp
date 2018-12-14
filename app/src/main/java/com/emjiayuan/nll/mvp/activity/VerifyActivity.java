package com.emjiayuan.nll.mvp.activity;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.base.BaseActivity;

import butterknife.BindView;

public class VerifyActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.verify)
    ImageView mVerify;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_verify;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
        Glide.with(mActivity).load(R.drawable.verify).into(mVerify);
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

        }
    }
}