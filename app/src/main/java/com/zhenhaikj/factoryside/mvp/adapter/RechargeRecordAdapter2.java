package com.zhenhaikj.factoryside.mvp.adapter;

import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.bean.Bill;
import com.zhenhaikj.factoryside.mvp.bean.Recharge;

import java.util.List;

public class RechargeRecordAdapter2 extends BaseQuickAdapter<Recharge.Data1Bean, BaseViewHolder> {
    public RechargeRecordAdapter2(int layoutResId, @Nullable List<Recharge.Data1Bean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Recharge.Data1Bean item) {
        if ("1".equals(item.getState())) {
            helper.setText(R.id.tv_recharge_type, item.getPayTypeName())
                    .setText(R.id.tv_pay_money, "+" + item.getPayMoney());
            if ("充值余额".equals(item.getStateName())) {
                helper.setText(R.id.tv_name_first, "余");
//            helper.setBackgroundColor(R.id.ll_first,R.drawable.yellow_circle);
                helper.setBackgroundRes(R.id.ll_first, R.drawable.yellow_circle);
            } else if ("充值诚意金".equals(item.getStateName())) {
                helper.setText(R.id.tv_name_first, "保");
//            helper.setBackgroundColor(R.id.ll_first,R.drawable.red_circle);
                helper.setBackgroundRes(R.id.ll_first, R.drawable.red_circle);
            }
            StringBuilder stringBuilder = new StringBuilder(item.getCreateTime());
            String time = "" + stringBuilder.replace(10, 11, " "); //去掉T
            helper.setText(R.id.tv_time, time);
        }else if ("2".equals(item.getState())){
            helper.setText(R.id.tv_recharge_type,"工单号:"+ item.getOrderID())
                    .setText(R.id.tv_pay_money, "-" + item.getPayMoney());
            helper.getView(R.id.ll_first).setVisibility(View.GONE);
            StringBuilder stringBuilder = new StringBuilder(item.getCreateTime());
            String time = "" + stringBuilder.replace(10, 11, " "); //去掉T
            helper.setText(R.id.tv_time, time);
        }

    }
}
