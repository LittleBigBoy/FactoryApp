package com.zhenhaikj.factoryside.mvp.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.mvp.bean.Address;

import java.util.List;

import androidx.annotation.Nullable;

public class UninvoicedAdapter extends BaseQuickAdapter<Address, BaseViewHolder> {
    public UninvoicedAdapter(int layoutResId, @Nullable List<Address> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Address item) {

    }
}
