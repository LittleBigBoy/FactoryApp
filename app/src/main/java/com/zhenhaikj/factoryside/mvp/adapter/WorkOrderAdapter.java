package com.zhenhaikj.factoryside.mvp.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.bean.WorkOrder;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class WorkOrderAdapter extends BaseQuickAdapter<WorkOrder.DataBean,BaseViewHolder> {
    public WorkOrderAdapter(int layoutResId, List<WorkOrder.DataBean> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, WorkOrder.DataBean item) {
        helper.setText(R.id.tv_order_num,"工单号："+item.getOrderID())
                .setText(R.id.tv_name,item.getMemo())
                .setText(R.id.tv_warranty,item.getTypeName())
                .setText(R.id.tv_status,item.getState())
                .setText(R.id.tv_info,item.getUserName()+item.getPhone())
                .setText(R.id.tv_address,item.getAddress())
                .setText(R.id.tv_cost,"￥"+item.getOrderMoney())
                .addOnClickListener(R.id.tv_complaint)
                .addOnClickListener(R.id.tv_leave_message)
                .addOnClickListener(R.id.tv_see_detail);
    }

}
