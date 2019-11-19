package com.zhenhaikj.factoryside.mvp.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.bean.Bill;
import com.zhenhaikj.factoryside.mvp.bean.Freezing;

import java.util.List;

public class FrozenAmountAdapter extends BaseQuickAdapter<Freezing.DataBean, BaseViewHolder> {
    public FrozenAmountAdapter(int layoutResId, @Nullable List<Freezing.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Freezing.DataBean item) {
        helper.setText(R.id.tv_type,item.getState());
        StringBuilder stringBuilder = new StringBuilder(item.getCreateTime());
        String time = "" + stringBuilder.replace(10, 11, " "); //去掉T
        helper.setText(R.id.tv_time, time);
        helper.setText(R.id.tv_order,"工单号:"+item.getOrderID());
        helper.setText(R.id.tv_pay_money,"-"+item.getPayMoney());
        helper.setText(R.id.tv_name_first,item.getState().substring(0,1));
    }
}
