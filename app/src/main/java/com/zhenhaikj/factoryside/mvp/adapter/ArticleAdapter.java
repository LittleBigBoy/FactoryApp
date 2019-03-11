package com.zhenhaikj.factoryside.mvp.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.bean.Article;

import java.util.List;

import androidx.annotation.Nullable;

public class ArticleAdapter extends BaseQuickAdapter<Article.DataBean,BaseViewHolder> {
    public ArticleAdapter(int layoutResId, @Nullable List<Article.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Article.DataBean item) {
        helper.setText(R.id.iv_message_name,item.getTitle());
    }
}
