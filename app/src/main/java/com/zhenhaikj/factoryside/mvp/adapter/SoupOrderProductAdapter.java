package com.zhenhaikj.factoryside.mvp.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.bean.SoupOrder;

import java.util.List;

public class SoupOrderProductAdapter extends BaseQuickAdapter<SoupOrder.ProductListBean,BaseViewHolder> {
    private SoupOrder mSoupOrder;
    public SoupOrderProductAdapter(int layoutResId, List<SoupOrder.ProductListBean> data,SoupOrder soupOrder) {
        super(layoutResId, data);
        this.mSoupOrder=soupOrder;
    }
    @Override
    protected void convert(BaseViewHolder helper, SoupOrder.ProductListBean item) {
        // 加载网络图片
        helper.setText(R.id.name,mSoupOrder.getOrder_name());
        helper.setText(R.id.price,"￥"+mSoupOrder.getTotalmoney());
        helper.setText(R.id.num,mSoupOrder.getLeixing());
        Glide.with(mContext).load(item.getImages()).into((ImageView) helper.getView(R.id.icon));
    }

}
