package com.emjiayuan.nll.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.model.Comment;
import com.emjiayuan.nll.viewholder.RatingBarViewHolder;

import java.util.List;

public class CommentAdapter extends BaseQuickAdapter<Comment,RatingBarViewHolder> {
    public CommentAdapter(int layoutResId, List<Comment> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(RatingBarViewHolder helper, Comment item) {
        helper.setImageResource(R.id.icon, item.getIcon());
        helper.setText(R.id.tv_name, item.getName());
        helper.setStars(R.id.stars,item.getStars());
        helper.setText(R.id.tv_time, item.getTime());
        helper.setText(R.id.tv_content, item.getContent());
        // 加载网络图片
//        Glide.with(mContext).load(item.getUserAvatar()).crossFade().into((ImageView) helper.getView(R.id.iv));
    }
}
