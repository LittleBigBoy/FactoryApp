package com.zhenhaikj.factoryside.mvp.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.bean.FrozenMoney;
import com.zhenhaikj.factoryside.mvp.bean.MonthBill;

import java.util.List;

public class FrozenMoneyAdapter extends BaseQuickAdapter<FrozenMoney.DataBean, BaseViewHolder> {
    public FrozenMoneyAdapter(int layoutResId, List<FrozenMoney.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FrozenMoney.DataBean item) {
        StringBuilder stringBuilder = new StringBuilder(item.getCreateTime());
        String time = "" + stringBuilder.replace(10, 20, " "); //去掉T
        helper.setText(R.id.tv_phone_number, item.getOrderID())
//                .setText(R.id.tv_service,item.getState())
                .setText(R.id.tv_money, "-" + item.getPayMoney())
                .setText(R.id.tv_time, time);
        if ("0".equals(item.getState())) {
            helper.setText(R.id.tv_service, "空");
        } else if ("1".equals(item.getState())){
            helper.setText(R.id.tv_service, "配件费");
        }else if ("2".equals(item.getState())){
            helper.setText(R.id.tv_service, "远程费");
        }else if ("3".equals(item.getState())){
            helper.setText(R.id.tv_service, "邮费");
        }else {
            helper.setText(R.id.tv_service, "上门服务费");
        }


    }

}
