package com.zhenhaikj.factoryside.mvp.adapter;

import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.bean.SingleQuantity;

import java.util.List;

public class SingleQuantityAdapter extends BaseQuickAdapter<SingleQuantity.DataBean, BaseViewHolder> {
    public SingleQuantityAdapter(int layoutResId, @Nullable List<SingleQuantity.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SingleQuantity.DataBean item) {
        helper.setText(R.id.tv_order,"工单号:"+item.getOrderID());
        StringBuilder stringBuilder = new StringBuilder(item.getCreateDate());
        String time = "" + stringBuilder.replace(10, 11, " "); //去掉T
        helper.setText(R.id.tv_time, time);
        if (item.getTypeID()==1){
            helper.setText(R.id.tv_name_first,"修");
        }else if (item.getTypeID()==2){
            helper.setText(R.id.tv_name_first,"安");
        }

        helper.addOnClickListener(R.id.iv_copy);
        helper.addOnClickListener(R.id.ll_order);
    }
}
