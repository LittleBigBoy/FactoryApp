package com.emjiayuan.nll.activity;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.adapter.CommentAdapter;
import com.emjiayuan.nll.base.BaseActivity;
import com.emjiayuan.nll.model.Comment;
import com.emjiayuan.nll.widget.RecyclerViewDivider;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class StoreDetailActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.rv_comment)
    RecyclerView rvComment;
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
        return R.layout.activity_store_detail;
    }

    @Override
    protected void initData() {
        tvTitle.setText("详情");
        List<Comment> commentList = new ArrayList<>();
        for (int i = 0; i < 300; i++) {
            commentList.add(new Comment(R.drawable.all, "亚索",3, "挺不错的！味道不错服务好！对我们出门的穆斯林来说很方便！", "两年前"));
        }
        CommentAdapter commentAdapter = new CommentAdapter(R.layout.comment_item, commentList);
        rvComment.setLayoutManager(new LinearLayoutManager(mActivity));
        rvComment.addItemDecoration(new RecyclerViewDivider(mActivity, LinearLayoutManager.HORIZONTAL, 1, Color.parseColor("#E5E5E5")));
        commentAdapter.openLoadAnimation();
        commentAdapter.isFirstOnly(false);
        commentAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        View view=LayoutInflater.from(mActivity).inflate(R.layout.store_top,null);
        commentAdapter.addHeaderView(view);
        rvComment.setAdapter(commentAdapter);
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