package com.zhenhaikj.factoryside.mvp.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;

import java.util.List;

public class ConstellationAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public ConstellationAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.lable, item);
        // 加载网络图片
//        Glide.with(mContext).load(item.getUserAvatar()).crossFade().into((ImageView) helper.getView(R.id.iv));
    }
}
