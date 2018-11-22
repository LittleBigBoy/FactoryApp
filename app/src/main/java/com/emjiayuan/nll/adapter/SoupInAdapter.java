package com.emjiayuan.nll.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.model.SoupOrder;

import java.util.List;

public class SoupInAdapter extends BaseQuickAdapter<SoupOrder.ProductListBean,BaseViewHolder> {
    public SoupInAdapter(int layoutResId, List<SoupOrder.ProductListBean> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, SoupOrder.ProductListBean item) {
        // 加载网络图片
        helper.setText(R.id.name,item.getName());
        helper.setText(R.id.num,item.getWeight_jin()+"斤");
        Glide.with(mContext).load(item.getImages()).into((ImageView) helper.getView(R.id.icon));
    }
}
