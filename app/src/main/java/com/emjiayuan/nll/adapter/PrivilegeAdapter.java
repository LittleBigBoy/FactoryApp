package com.emjiayuan.nll.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.model.Privilege;

import java.util.List;

public class PrivilegeAdapter extends BaseQuickAdapter<Privilege,BaseViewHolder> {
    public PrivilegeAdapter(int layoutResId, List<Privilege> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, Privilege item) {
        helper.setImageResource(R.id.icon, item.getIcon());
        helper.setText(R.id.tv_lable, item.getLable());
        helper.setText(R.id.tv_content, item.getContent());
        // 加载网络图片
//        Glide.with(mContext).load(item.getUserAvatar()).crossFade().into((ImageView) helper.getView(R.id.iv));
    }
}
