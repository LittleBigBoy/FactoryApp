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
                .setText(R.id.tv_name,item.getCategoryName() + " " + item.getBrandName() + " " + item.getSubCategoryName())
                .setText(R.id.tv_warranty,item.getTypeName()+"/"+item.getGuarantee())
                .setText(R.id.tv_status,item.getState())
                .setText(R.id.tv_info,item.getUserName()+"   "+item.getPhone())
                .setText(R.id.tv_address,item.getAddress())
//                .setText(R.id.tv_cost,"¥" + item.getQuaMoney())
                .addOnClickListener(R.id.tv_complaint)
                .addOnClickListener(R.id.tv_leave_message)
                .addOnClickListener(R.id.tv_see_detail)
                .addOnClickListener(R.id.iv_copy)
                .addOnClickListener(R.id.iv_star)
                .addOnClickListener(R.id.tv_obsolete);
//        if (item.getAccessoryMoney()!=null&&!"0.00".equals(item.getAccessoryMoney())){
//            helper.setText(R.id.tv_cost,"¥" + (Double.parseDouble(item.getAccessoryMoney())+Double.parseDouble(item.getBeyondMoney())+Double.parseDouble(item.getPostMoney())) + "");
//        }else{
//            helper.setText(R.id.tv_cost,"¥" + item.getOrderMoney() + "");
//        }
        if ("维修".equals(item.getTypeName())){
            helper.setText(R.id.tv_malfunction,"故障:"+item.getMemo());
        }else {
            helper.setText(R.id.tv_malfunction,item.getMemo());
        }
        if ("3".equals(item.getTypeID())) {
            helper.setText(R.id.tv_cost,"¥" + item.getQuaMoney());
        } else {
            if ("1".equals(item.getAccessoryApplyState())) {
                helper.setText(R.id.tv_cost,"¥" + item.getOrderMoney());
            } else {
                helper.setText(R.id.tv_cost,"¥" + item.getOrderMoney());
            }
        }

        if ("所有工单".equals(name)){
            helper.setVisible(R.id.tv_status,true);
        }else {
            helper.setGone(R.id.tv_status,false);

        }

        //        if ("待接单".equals(name)){
//            helper.setVisible(R.id.tv_obsolete,true);
//        }
//
//        if ("已接单待联系客户".equals(item.getState())||"待接单".equals(item.getState())){
//            helper.setVisible(R.id.tv_obsolete,true);
//        }

//        if ("0".equals(item.getBeyondState())){
//            helper.setText(R.id.tv_remind,"远程费待审核");
//        }else {
//            helper.setText(R.id.tv_remind,"");
//        }
        if (item.getBeyondState()==null){
            helper.setText(R.id.tv_remind,"");
            helper.setGone(R.id.tv_remind,false);
        }else if ("0".equals(item.getBeyondState())) {
            helper.setText(R.id.tv_remind,"远程费待审核");
        } else if ("1".equals(item.getBeyondState())) {
            helper.setText(R.id.tv_remind,"远程费审核通过");

        }else if ("2".equals(item.getBeyondState())){
            helper.setText(R.id.tv_remind,"远程费已修改");
        } else {
            helper.setText(R.id.tv_remind,"远程费已拒绝");
        }

//        if ("0".equals(item.getAccessoryApplyState())){
//            helper.setText(R.id.tv_remind_two,"配件待审核");
//        }else {
//            helper.setText(R.id.tv_remind_two,"");
//        }
        if(item.getAccessoryAndServiceApplyState()==null){
            helper.setText(R.id.tv_remind_two,"");
            helper.setGone(R.id.tv_remind_two,false);
        } else if ("0".equals(item.getAccessoryAndServiceApplyState())) {
            helper.setText(R.id.tv_remind_two,"待审核");
        } else if ("1".equals(item.getAccessoryAndServiceApplyState())) {
            helper.setText(R.id.tv_remind_two,"审核通过");
        }else if ("2".equals(item.getAccessoryAndServiceApplyState())) {
            helper.setText(R.id.tv_remind_two,"厂家寄件");
        } else {
            helper.setText(R.id.tv_remind_two,"已拒绝");
        }

//        if ("质保单".equals(name)){
//            if (!"".equals(item.getAppointmentMessage())){
//                helper.setVisible(R.id.tv_remind,true);
//                helper.setText(R.id.tv_remind,item.getAppointmentMessage());
//            }
//        }

    }

}
