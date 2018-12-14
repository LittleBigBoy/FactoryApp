package com.emjiayuan.nll.mvp.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.base.BaseActivity;
import com.emjiayuan.nll.model.UserInfo;

import butterknife.BindView;

public class RejectActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.verify)
    ImageView mVerify;
    @BindView(R.id.again)
    TextView mAgain;

    private UserInfo mUserInfo;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_reject;
    }

    @Override
    protected void initData() {
        mUserInfo= (UserInfo) getIntent().getSerializableExtra("userInfo");
    }

    @Override
    protected void initView() {
        Glide.with(mActivity).load(R.drawable.reject).into(mVerify);
    }

    @Override
    protected void setListener() {
        mAgain.setOnClickListener(this);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.again:
                Intent intent=new Intent(mActivity,PersonalInfoActivity.class);
                intent.putExtra("userInfo", mUserInfo);
                startActivity(intent);
                finish();
                break;
        }
    }
}