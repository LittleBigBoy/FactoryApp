package com.zhenhaikj.factoryside.mvp.v3.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.activity.ArticleActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SystemNotificationActivity extends BaseActivity implements View.OnClickListener {

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
    @BindView(R.id.ll_platform_policy)
    LinearLayout mLlPlatformPolicy;
    @BindView(R.id.ll_news)
    LinearLayout mLlNews;
    @BindView(R.id.ll_order_must_read)
    LinearLayout mLlOrderMustRead;
    private Intent intent;

    @Override
    protected int setLayoutId() {
        return R.layout.v3_activity_system_notification;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvTitle.setText("系统通知");
    }

    @Override
    protected void setListener() {
//        mIvBack.setOnClickListener(this);
//        mLlCustomerService.setOnClickListener(this);
        mIconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mLlOrderMustRead.setOnClickListener(this);
        mLlPlatformPolicy.setOnClickListener(this);
        mLlNews.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.iv_back:
//                finish();
//                break;
//        }

        intent = new Intent(mActivity, ArticleActivity.class);
        switch (v.getId()){
//            case R.id.ll_announcement:
//                intent.putExtra("CategoryId","3");
//                intent.putExtra("title","系统消息");
//                break;
            case R.id.ll_platform_policy:
                intent.putExtra("CategoryId","4");
                intent.putExtra("title","平台政策");
                break;
            case R.id.ll_news:
                intent.putExtra("CategoryId","5");
                intent.putExtra("title","平台新闻");
                break;
            case R.id.ll_order_must_read:
                intent.putExtra("CategoryId","6");
                intent.putExtra("title","发单必读");
                break;
        }
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
