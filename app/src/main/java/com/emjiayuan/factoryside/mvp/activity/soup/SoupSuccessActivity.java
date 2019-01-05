package com.emjiayuan.factoryside.mvp.activity.soup;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.emjiayuan.nll.MainActivity;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.base.BaseActivity;
import com.emjiayuan.nll.utils.MyUtils;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;

public class SoupSuccessActivity extends BaseActivity {

    @BindView(R.id.back_home)
    Button backHome;
    @BindView(R.id.see_order)
    Button seeOrder;
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
    @BindView(R.id.share_order)
    Button mShareOrder;

    private String orderid = "";


    @Override
    protected int setLayoutId() {
        return R.layout.activity_success;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            orderid = intent.getStringExtra("orderid");
        }
    }

    @Override
    protected void initView() {
        mTvTitle.setText("支付成功");
        mIconBack.setVisibility(View.GONE);
        mTvTitle.setVisibility(View.VISIBLE);
        mTvSave.setVisibility(View.VISIBLE);
        mTvSave.setText("完成");
        mTvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!MyUtils.isFastClick()) {
                    return;
                }
                finish();
            }
        });
    }


    @Override
    protected void setListener() {
        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!MyUtils.isFastClick()) {
                    return;
                }
                startActivity(new Intent(mActivity, MainActivity.class));
                finish();
            }
        });
        seeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!MyUtils.isFastClick()) {
                    return;
                }
                Intent intent = new Intent(mActivity, SoupOrderDetailActivity.class);
                intent.putExtra("orderid", orderid);
                mActivity.startActivity(intent);
                finish();
            }
        });
    }
}
