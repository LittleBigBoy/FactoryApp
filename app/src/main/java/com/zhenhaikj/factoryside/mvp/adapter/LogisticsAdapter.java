package com.zhenhaikj.factoryside.mvp.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.bean.Order;

import java.util.List;

public class LogisticsAdapter extends BaseQuickAdapter<Order,BaseViewHolder> {
    public LogisticsAdapter(int layoutResId, List<Order> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, Order item) {
        helper.setText(R.id.order_num,"订单号："+item.getOrder_no());
        helper.setText(R.id.company,"物流公司："+item.getExpressname());
        helper.setText(R.id.wuliu_num,"物流编号："+item.getExpressno());
        helper.setText(R.id.time,"下单时间："+item.getCreatedate());
        helper.addOnClickListener(R.id.see);
    }
}
