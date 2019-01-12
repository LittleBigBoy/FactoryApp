package com.zhenhaikj.factoryside.mvp.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.bean.Address;

import java.util.List;

public class RechargeRecordAdapter extends BaseQuickAdapter<Address,BaseViewHolder> {
    public RechargeRecordAdapter(int layoutResId, List<Address> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, Address item) {
        // 加载网络图片
        /*helper.setText(R.id.name,item.getUsername());
        helper.setText(R.id.phone,item.getTelphone());
        helper.setText(R.id.address,item.getAddress());
        helper.addOnClickListener(R.id.edit_ll);
        helper.addOnClickListener(R.id.delete_ll);
        helper.addOnClickListener(R.id.default_ll);
        if ("1".equals(item.getIs_default())){
            helper.setChecked(R.id.check,true);
        }else{
            helper.setChecked(R.id.check,false);
        }*/
    }
}
