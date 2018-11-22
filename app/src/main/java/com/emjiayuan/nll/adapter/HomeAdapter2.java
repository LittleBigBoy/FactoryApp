package com.emjiayuan.nll.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.model.HomeMenu2;

import java.util.List;

public class HomeAdapter2 extends BaseQuickAdapter<HomeMenu2,BaseViewHolder> {
    public HomeAdapter2(int layoutResId, List<HomeMenu2> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, HomeMenu2 item) {
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_content, item.getContent());
        helper.setImageResource(R.id.icon, item.getDrawable());
        // 加载网络图片
//        Glide.with(mContext).load(item.getUserAvatar()).crossFade().into((ImageView) helper.getView(R.id.iv));
    }
}
