package com.zhenhaikj.factoryside.mvp.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.bean.OrderConfirm;

import java.util.List;

public class ConfirmOrderProductAdapter extends BaseQuickAdapter<OrderConfirm.ProductsBean,BaseViewHolder> {
    public ConfirmOrderProductAdapter(int layoutResId, List<OrderConfirm.ProductsBean> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, OrderConfirm.ProductsBean item) {
        // 加载网络图片
        helper.setText(R.id.name,item.getName());
        helper.setText(R.id.price,"¥"+item.getBuyprice());
        helper.setText(R.id.num,"x"+item.getBuynum());
        Glide.with(mContext).load(item.getImages()).into((ImageView) helper.getView(R.id.icon));
    }
}
