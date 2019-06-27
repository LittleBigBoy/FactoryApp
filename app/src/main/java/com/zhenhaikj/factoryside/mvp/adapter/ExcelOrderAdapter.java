package com.zhenhaikj.factoryside.mvp.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.bean.Order;

import java.util.List;

public class ExcelOrderAdapter extends BaseQuickAdapter<Order, BaseViewHolder> {
    public ExcelOrderAdapter(int layoutResId, @Nullable List<Order> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Order item) {
    helper.setText(R.id.tv_name,"用户姓名:"+item.getUserName());
    helper.setText(R.id.tv_phone,"用户手机号:"+item.getPhone());
    helper.setText(R.id.tv_reason,"故障原因:"+item.getMemo());

    }
}
