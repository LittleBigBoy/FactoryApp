package com.zhenhaikj.factoryside.mvp.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.bean.Temple;

import java.util.List;

public class TempleAdapter extends BaseQuickAdapter<Temple,BaseViewHolder> {
    public TempleAdapter(int layoutResId, List<Temple> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, Temple item) {
        helper.setImageResource(R.id.icon, item.getIcon());
        helper.setText(R.id.tv_name, item.getName());
        helper.setText(R.id.tv_addr, item.getAddress());
        helper.setText(R.id.tv_distance, item.getDistance());
        // 加载网络图片
//        Glide.with(mContext).load(item.getUserAvatar()).crossFade().into((ImageView) helper.getView(R.id.iv));
    }
}
