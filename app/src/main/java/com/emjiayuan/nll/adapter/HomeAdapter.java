package com.emjiayuan.nll.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.model.HomeMenu;

import java.util.List;

public class HomeAdapter extends BaseQuickAdapter<HomeMenu,BaseViewHolder> {
    public HomeAdapter(int layoutResId, List<HomeMenu> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, HomeMenu item) {
        helper.setText(R.id.txt_content, item.getLable());
        helper.setImageResource(R.id.icon, item.getDrawable());
        // 加载网络图片
//        Glide.with(mContext).load(item.getUserAvatar()).crossFade().into((ImageView) helper.getView(R.id.iv));
    }
}
