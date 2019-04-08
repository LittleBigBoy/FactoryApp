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
        helper.setText(R.id.tv_name_first,item.getUserName().substring(0,1));
        helper.setText(R.id.tv_name,item.getUserName());
        helper.setText(R.id.tv_phone,item.getPhone());
        helper.setText(R.id.tv_address,item.getAddress());
        helper.addOnClickListener(R.id.tv_edit);
        if ("1".equals(item.getIsDefault())){
            helper.setGone(R.id.tv_default,true);
        }else{
            helper.setGone(R.id.tv_default,false);
        }
    }
}
