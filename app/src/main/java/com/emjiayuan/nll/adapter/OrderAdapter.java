package com.emjiayuan.nll.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.model.Order;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class OrderAdapter extends BaseQuickAdapter<Order,BaseViewHolder> {
    public OrderAdapter(int layoutResId, List<Order> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, Order item) {
        // 加载网络图片
        helper.setText(R.id.order_time,item.getCreatetime());
        helper.setText(R.id.order_num,item.getOrder_no());
        helper.setText(R.id.status_tv,item.getOrder_status());
        int count=0;
        for (int i = 0; i < item.getProduct_list().size(); i++) {
            count+=Integer.parseInt(item.getProduct_list().get(i).getBuycount());
        }
        helper.setText(R.id.total_tv,"共"+count+"件商品 合计: ¥"+item.getTotalmoney() +"(含运费及优惠券优惠)");
        RecyclerView rv_product=helper.getView(R.id.rv_product);
        rv_product.setLayoutManager(new LinearLayoutManager(mContext));
        rv_product.setAdapter(new OrderProductAdapter(R.layout.order_item_in,item.getProduct_list()));
        helper.addOnClickListener(R.id.btn1);
        helper.addOnClickListener(R.id.btn2);
    }
}
