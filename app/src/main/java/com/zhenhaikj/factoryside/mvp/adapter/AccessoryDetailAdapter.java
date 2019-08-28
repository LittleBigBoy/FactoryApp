package com.zhenhaikj.factoryside.mvp.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.bean.GAccessory;

import java.util.List;

public class AccessoryDetailAdapter extends BaseQuickAdapter<GAccessory,BaseViewHolder> {
    private String AccessoryState;
    public AccessoryDetailAdapter(int layoutResId, List<GAccessory> data,String AccessoryState) {
        super(layoutResId, data);
        this.AccessoryState=AccessoryState;
    }
    @Override
    protected void convert(BaseViewHolder helper, GAccessory item) {
        helper.setText(R.id.tv_accessories_name,item.getFAccessoryName());
        if ("0".equals(AccessoryState)){
            helper.setText(R.id.tv_accessories_number,item.getQuantity()+"个");
        }else {
            helper.setText(R.id.tv_accessories_number,"¥"+item.getDiscountPrice()+"/"+item.getQuantity()+"个");
        }
        helper.addOnClickListener(R.id.tv_reject);
        helper.addOnClickListener(R.id.tv_pass);
        if ("Y".equals(item.getNeedPlatformAuth())){
            helper.setGone(R.id.ll_accessories,false);
        }
        if ("0".equals(item.getState())){
            helper.setVisible(R.id.tv_reject,true);
            helper.setVisible(R.id.tv_pass,true);
            helper.setGone(R.id.tv_status_accessory,false);
        }else if ("1".equals(item.getState())){
            helper.setGone(R.id.tv_reject,false);
            helper.setGone(R.id.tv_pass,false);
            helper.setVisible(R.id.tv_status_accessory,true);
            helper.setText(R.id.tv_status_accessory,"审核通过");
        }else {
            helper.setGone(R.id.tv_reject,false);
            helper.setGone(R.id.tv_pass,false);
            helper.setVisible(R.id.tv_status_accessory,true);
            helper.setText(R.id.tv_status_accessory,"审核拒绝");
        }
    }
}
