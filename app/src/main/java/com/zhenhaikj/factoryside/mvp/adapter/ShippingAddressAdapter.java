package com.zhenhaikj.factoryside.mvp.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.bean.Address;

import java.util.List;

public class ShippingAddressAdapter extends BaseQuickAdapter<Address,BaseViewHolder> {
    public ShippingAddressAdapter(int layoutResId, List<Address> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, Address item) {

    }
}
