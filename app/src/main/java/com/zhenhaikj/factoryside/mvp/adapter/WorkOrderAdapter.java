package com.zhenhaikj.factoryside.mvp.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.bean.WorkOrder;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class WorkOrderAdapter extends BaseQuickAdapter<WorkOrder,BaseViewHolder> {
    public WorkOrderAdapter(int layoutResId, List<WorkOrder> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, WorkOrder item) {
        /*helper.setText(R.id.order_time,item.getCreatetime());
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
        switch (item.getOrdertype()){
            *//*0.已取消1.待付款2.待发货3.待收货4.待评价*//*
            case "0":
                helper.setGone(R.id.btn1,false);
                helper.setGone(R.id.btn2,false);
                break;
            case "1":
                helper.setText(R.id.btn1,"取消订单");
                helper.setText(R.id.btn2,"去付款");
                helper.setVisible(R.id.btn1,true);
                helper.setVisible(R.id.btn2,true);
                break;
            case "2":
                helper.setText(R.id.btn2,"提醒发货");
                helper.setVisible(R.id.btn1,false);
                helper.setVisible(R.id.btn2,true);
                break;
            case "3":
                helper.setText(R.id.btn1,"查看物流");
                helper.setText(R.id.btn2,"确认收货");
                helper.setVisible(R.id.btn1,true);
                helper.setVisible(R.id.btn2,true);
                break;
            case "4":
                helper.setText(R.id.btn2,"去评价");
                helper.setVisible(R.id.btn1,false);
                helper.setVisible(R.id.btn2,true);
                break;
        }
        helper.addOnClickListener(R.id.btn1);
        helper.addOnClickListener(R.id.btn2);*/
    }

}
