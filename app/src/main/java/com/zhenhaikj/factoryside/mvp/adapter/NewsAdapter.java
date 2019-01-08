package com.zhenhaikj.factoryside.mvp.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.bean.News;

import java.util.List;

public class NewsAdapter extends BaseQuickAdapter<News,BaseViewHolder> {
    public NewsAdapter(int layoutResId, List<News> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, News item) {
        helper.setText(R.id.tv_name, item.getTitle());
        helper.setText(R.id.tv_time,item.getCreatetime());
        // 加载网络图片
        Glide.with(mContext).load(item.getImages()).into((ImageView) helper.getView(R.id.icon));
    }
}
