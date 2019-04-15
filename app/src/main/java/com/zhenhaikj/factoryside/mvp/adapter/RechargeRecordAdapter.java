package com.zhenhaikj.factoryside.mvp.adapter;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.bean.Address;
import com.zhenhaikj.factoryside.mvp.bean.Bill;

import java.util.List;

public class RechargeRecordAdapter extends BaseQuickAdapter<Bill.DataBean,BaseViewHolder> {
    public RechargeRecordAdapter(int layoutResId, List<Bill.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Bill.DataBean item) {
        //充值


        if (item.getState().equals("1")){
            StringBuilder stringBuilder = new StringBuilder(item.getCreateTime());
            String time = "" + stringBuilder.replace(10, 11, " "); //去掉T
            helper.setText(R.id.time_tv,time);
            helper.setText(R.id.money_tv,""+item.getPayMoney());
            helper.setText(R.id.pay_type_tv,item.getPayTypeName());
            helper.setText(R.id.success_tv,item.getStateName());
        }
    }

}
