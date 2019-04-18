package com.zhenhaikj.factoryside.mvp.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.bean.WorkOrder;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class WorkOrderAdapter extends BaseQuickAdapter<WorkOrder.DataBean,BaseViewHolder> {
    String name;
    public WorkOrderAdapter(int layoutResId, List<WorkOrder.DataBean> data,String name) {
        super(layoutResId, data);
        this.name=name;
    }
    @Override
    protected void convert(BaseViewHolder helper, WorkOrder.DataBean item) {
        helper.setText(R.id.tv_order_num,"工单号："+item.getOrderID())
                .setText(R.id.tv_name,item.getMemo())
                .setText(R.id.tv_warranty,item.getTypeName()+"/"+item.getGuarantee())
                .setText(R.id.tv_status,item.getState())
                .setText(R.id.tv_info,item.getUserName()+item.getPhone())
                .setText(R.id.tv_address,item.getAddress())
                .addOnClickListener(R.id.tv_complaint)
                .addOnClickListener(R.id.tv_leave_message)
                .addOnClickListener(R.id.tv_see_detail)
                .addOnClickListener(R.id.iv_copy)
                .addOnClickListener(R.id.tv_obsolete);
        if (item.getAccessoryMoney()!=null&&!"0.00".equals(item.getAccessoryMoney())){
            helper.setText(R.id.tv_cost,"￥" + (Double.parseDouble(item.getAccessoryMoney())+Double.parseDouble(item.getBeyondMoney())+Double.parseDouble(item.getPostMoney())) + "");
        }else{
            helper.setText(R.id.tv_cost,"￥" + item.getOrderMoney() + "");
        }
        if ("待接单".equals(name)){
            helper.setVisible(R.id.tv_obsolete,true);
        }

    }

}
