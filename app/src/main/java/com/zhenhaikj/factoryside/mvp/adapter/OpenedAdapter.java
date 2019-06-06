package com.zhenhaikj.factoryside.mvp.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.bean.Address;
import com.zhenhaikj.factoryside.mvp.bean.CanInvoice;

import java.util.List;

import androidx.annotation.Nullable;

public class OpenedAdapter extends BaseQuickAdapter<CanInvoice, BaseViewHolder> {
    public OpenedAdapter(int layoutResId, @Nullable List<CanInvoice> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CanInvoice item) {
        helper.setText(R.id.tv_open, "已开");
        helper.setVisible(R.id.checkbox, false);
        StringBuilder stringBuilder = new StringBuilder(item.getCreateTime());
        String time = "" + stringBuilder.replace(10, 11, " "); //去掉T
        helper.setText(R.id.time_tv, time)
                .setText(R.id.money_tv, item.getPayMoney());

    }
}
