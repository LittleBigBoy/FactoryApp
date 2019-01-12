package com.zhenhaikj.factoryside.mvp.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.bean.WorkOrder;

import java.util.List;

public class OrderProductAdapter extends BaseQuickAdapter<WorkOrder.ProductListBean,BaseViewHolder> {
    public OrderProductAdapter(int layoutResId, List<WorkOrder.ProductListBean> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, WorkOrder.ProductListBean item) {
        // 加载网络图片
        helper.setText(R.id.name,item.getName());
        helper.setText(R.id.price,"￥"+item.getProductprice());
        helper.setText(R.id.num,"x"+item.getBuycount());
        Glide.with(mContext).load(item.getImages()).into((ImageView) helper.getView(R.id.icon));
    }
}
