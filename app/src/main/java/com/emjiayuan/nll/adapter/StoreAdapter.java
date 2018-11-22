package com.emjiayuan.nll.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.model.Store;
import com.emjiayuan.nll.viewholder.RatingBarViewHolder;

import java.util.List;

public class StoreAdapter extends BaseQuickAdapter<Store,RatingBarViewHolder> {
    public StoreAdapter(int layoutResId, List<Store> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(RatingBarViewHolder helper, Store item) {
        helper.setImageResource(R.id.icon, item.getIcon());
        helper.setText(R.id.tv_name, item.getName());
        helper.setStars(R.id.stars,item.getStars());
        helper.setText(R.id.tv_addr, item.getAddress());
        helper.setText(R.id.tv_distance, item.getDistance());
        // 加载网络图片
//        Glide.with(mContext).load(item.getUserAvatar()).crossFade().into((ImageView) helper.getView(R.id.iv));
    }
}
