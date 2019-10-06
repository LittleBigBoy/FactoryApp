package com.zhenhaikj.factoryside.mvp.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.bean.FAccessory;

import java.util.List;

import io.reactivex.annotations.Nullable;

/*预接单的添加配置*/
public class Pre_order_Add_Ac_Adapter extends BaseQuickAdapter<FAccessory.OrderAccessoryStrBean.OrderAccessoryBean, BaseViewHolder> {
    private String AccessoryState;
    public Pre_order_Add_Ac_Adapter(int layoutResId, @Nullable List<FAccessory.OrderAccessoryStrBean.OrderAccessoryBean> data, String AccessoryState) {
        super(layoutResId, data);
        this.AccessoryState=AccessoryState;
    }

    @Override
    protected void convert(BaseViewHolder helper, FAccessory.OrderAccessoryStrBean.OrderAccessoryBean item) {
        helper.setText(R.id.tv_accessories_name,item.getFAccessoryName());
        if ("0".equals(AccessoryState)){
            helper.setText(R.id.tv_accessories_number,item.getQuantity()+"个");
        }else {
            helper.setText(R.id.tv_accessories_number,"¥"+item.getDiscountPrice()+"/"+item.getQuantity()+"个");
        }

        helper.addOnClickListener(R.id.iv_accessories_delete);
           if (item.getSendState()==null){
               helper.setGone(R.id.tv_accessories_sendstate,false);
           }
           else if (item.getSendState().equals("N")){//未返件
            helper.setGone(R.id.tv_accessories_sendstate,false);
           }else { //已返件
            helper.setGone(R.id.tv_accessories_sendstate,true);
            helper.setText(R.id.tv_accessories_sendstate,"已返件");
        }
    }



   /* @Override
    protected void convert(BaseViewHolder helper, FAccessory item) {
     helper.setText(R.id.tv_accessories_name,item.);
     helper.setText(R.id.tv_accessories_number,item.getQuantity());
     helper.addOnClickListener(R.id.iv_accessories_delete);
    }*/
}
