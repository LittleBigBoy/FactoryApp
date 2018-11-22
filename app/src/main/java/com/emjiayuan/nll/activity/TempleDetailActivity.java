package com.emjiayuan.nll.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.emjiayuan.nll.R;
import com.emjiayuan.nll.base.BaseActivity;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;

public class TempleDetailActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.icon_search)
    ImageView iconSearch;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_temple_detail;
    }

    @Override
    protected void initData() {
        tvTitle.setText("详情");
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        iconBack.setOnClickListener(this);
        iconSearch.setOnClickListener(this);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.icon_back:
                finish();
                break;
            case R.id.icon_search:
                finish();
                break;
        }
    }
}