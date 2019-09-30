package com.zhenhaikj.factoryside.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.adapter.ArticleAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Article;
import com.zhenhaikj.factoryside.mvp.bean.Message;
import com.zhenhaikj.factoryside.mvp.bean.MessageData;
import com.zhenhaikj.factoryside.mvp.contract.ArticleContract;
import com.zhenhaikj.factoryside.mvp.model.ArticleModel;
import com.zhenhaikj.factoryside.mvp.presenter.ArticlePresenter;

import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleActivity extends BaseActivity<ArticlePresenter, ArticleModel> implements ArticleContract.View ,View.OnClickListener{
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
    @BindView(R.id.rv_order_must_read)
    RecyclerView mRvOrderMustRead;
    private String category;
    private List<Article.DataBean> list;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_article;
    }

    @Override
    protected void initData() {
//        mTvTitle.setText("文章");
        mTvTitle.setVisibility(View.VISIBLE);
        category = getIntent().getStringExtra("CategoryId");
        String title=getIntent().getStringExtra("title");
        mTvTitle.setText(title);
        mPresenter.GetListCategoryContentByCategoryID(category,"1","999");

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void GetListCategoryContentByCategoryID(BaseResult<Article> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                list = baseResult.getData().getData();
                ArticleAdapter articleAdapter=new ArticleAdapter(R.layout.item_order_must_read,list);
                mRvOrderMustRead.setLayoutManager(new LinearLayoutManager(mActivity));
                mRvOrderMustRead.setAdapter(articleAdapter);
                articleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Intent intent=new Intent(mActivity,WebActivity.class);
                        intent.putExtra("Url",list.get(position).getUrl());
                        intent.putExtra("title",list.get(position).getTitle());
                        startActivity(intent);
                    }
                });
                break;
            case 401:
                break;
        }
    }

    @Override
    public void GetOrderMessageList(BaseResult<MessageData<List<Message>>> baseResult) {

    }

    @Override
    public void GetTransactionMessageList(BaseResult<MessageData<List<Message>>> baseResult) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.icon_back:
                finish();
                break;
        }
    }
}
