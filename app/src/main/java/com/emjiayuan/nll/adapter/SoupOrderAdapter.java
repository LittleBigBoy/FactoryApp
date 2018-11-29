package com.emjiayuan.nll.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.model.SoupOrder;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SoupOrderAdapter extends BaseQuickAdapter<SoupOrder,BaseViewHolder> {
    public SoupOrderAdapter(int layoutResId, List<SoupOrder> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, SoupOrder item) {
        // 加载网络图片
        helper.setText(R.id.order_time,item.getCreatetime());
        helper.setText(R.id.order_num,item.getOrder_no());
        helper.setText(R.id.status_tv,item.getOrder_status());
        helper.setText(R.id.total_tv,"共1件商品 合计: ¥"+item.getTotalmoney());
        RecyclerView rv_product=helper.getView(R.id.rv_product);
        final RecyclerView rv_goods_in=helper.getView(R.id.rv_goods_in);
        rv_product.setLayoutManager(new LinearLayoutManager(mContext));
        rv_goods_in.setLayoutManager(new LinearLayoutManager(mContext));
        rv_product.setAdapter(new SoupOrderProductAdapter(R.layout.order_item_in,item.getProduct_list(),item));
        rv_goods_in.setAdapter(new SoupInAdapter(R.layout.soup_item_in,item.getProduct_list()));
        helper.setVisible(R.id.up_down,true);
        helper.setOnClickListener(R.id.up_down, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rv_goods_in.setVisibility(rv_goods_in.getVisibility()==View.VISIBLE?View.GONE:View.VISIBLE);
            }
        });
        switch (item.getOrdertype()){
            /*0.已取消1.待付款2.待发货3.待收货*/
            case "0":
                helper.setVisible(R.id.btn1,false);
                helper.setVisible(R.id.btn2,false);
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
        }
        helper.addOnClickListener(R.id.btn1);
        helper.addOnClickListener(R.id.btn2);
    }
}
