package com.zhenhaikj.factoryside.mvp.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.bean.GService;

import java.util.List;

public class ServiceAdapter extends BaseQuickAdapter<GService,BaseViewHolder> {
    public ServiceAdapter(int layoutResId, List<GService> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, GService item) {
        helper.setText(R.id.tv_accessories_name,item.getServiceName());
        helper.setVisible(R.id.tv_accessories_number,false);
        helper.addOnClickListener(R.id.tv_reject);
        helper.addOnClickListener(R.id.tv_pass);
        helper.setGone(R.id.tv_reject,false);
        helper.setGone(R.id.tv_pass,false);
//        if ("0".equals(item.getState())){
//            helper.setVisible(R.id.tv_reject,true);
//            helper.setVisible(R.id.tv_pass,true);
//            helper.setGone(R.id.tv_status_accessory,false);
//        }else if ("1".equals(item.getState())){
//            helper.setGone(R.id.tv_reject,false);
//            helper.setGone(R.id.tv_pass,false);
//            helper.setVisible(R.id.tv_status_accessory,true);
//            helper.setText(R.id.tv_status_accessory,"审核通过");
//        }else {
//            helper.setGone(R.id.tv_reject,false);
//            helper.setGone(R.id.tv_pass,false);
//            helper.setVisible(R.id.tv_status_accessory,true);
//            helper.setText(R.id.tv_status_accessory,"审核拒绝");
//        }
    }
}
