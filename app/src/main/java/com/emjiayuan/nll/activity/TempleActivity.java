package com.emjiayuan.nll.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.adapter.TempleAdapter;
import com.emjiayuan.nll.base.BaseActivity;
import com.emjiayuan.nll.model.Temple;
import com.emjiayuan.nll.widget.RecyclerViewDivider;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class TempleActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.rv_home)
    RecyclerView rvHome;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

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
        return R.layout.activity_temple;
    }

    @Override
    protected void initData() {
        tvTitle.setText("清真寺");
        List<Temple> templeList = new ArrayList<>();
        for (int i = 0; i < 300; i++) {
            templeList.add(new Temple(R.drawable.all, "宁波清真寺", "后营巷18号", "10km"));
        }
        TempleAdapter templeAdapter = new TempleAdapter(R.layout.temple_item, templeList);
        rvHome.setLayoutManager(new LinearLayoutManager(mActivity));
        rvHome.addItemDecoration(new RecyclerViewDivider(mActivity, LinearLayoutManager.HORIZONTAL, 1, R.color.gray));
        templeAdapter.openLoadAnimation();
        templeAdapter.isFirstOnly(false);
        templeAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        rvHome.setAdapter(templeAdapter);
        templeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mActivity,TempleDetailActivity.class));
            }
        });
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