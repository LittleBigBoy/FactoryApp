package com.zhenhaikj.factoryside.mvp.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.mvp.bean.Privilege;
import com.zhenhaikj.factoryside.R;

import java.util.List;

public class PrivilegeAdapter extends BaseQuickAdapter<Privilege,BaseViewHolder> {
    public PrivilegeAdapter(int layoutResId, List<Privilege> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, Privilege item) {
        helper.setText(R.id.tv_lable, item.getTitle());
        helper.setText(R.id.tv_content, item.getContent());
        // 加载网络图片
        Glide.with(mContext).load(item.getImages()).into((ImageView) helper.getView(R.id.icon));
    }
}
