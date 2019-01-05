package com.emjiayuan.factoryside.mvp.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.emjiayuan.nll.R;
import com.emjiayuan.nll.base.BaseActivity;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;

public class AddActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.icon_back)
    ImageView mIconBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.icon_search)
    ImageView mIconSearch;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_add;
    }

    @Override
    protected void initData() {
        mTvTitle.setText("添加信息");
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mIconSearch.setOnClickListener(this);
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