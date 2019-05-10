package com.zhenhaikj.factoryside.mvp.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.bean.FrozenMoney;
import com.zhenhaikj.factoryside.mvp.bean.MonthBill;

import java.util.List;

public class FrozenMoneyAdapter extends BaseQuickAdapter<FrozenMoney, BaseViewHolder> {
    public FrozenMoneyAdapter(int layoutResId, List<FrozenMoney> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FrozenMoney item) {
        StringBuilder stringBuilder = new StringBuilder(item.getCreateTime());
        String time = "" + stringBuilder.replace(10, 20, " "); //去掉T
        helper.setText(R.id.tv_phone_number, item.getOrderID())
//                .setText(R.id.tv_service,item.getState())
                .setText(R.id.tv_money, "-" + item.getPayMoney())
                .setText(R.id.tv_time, time);
        if ("1".equals(item.getTypeID())) {
            helper.setText(R.id.tv_service, "安装");
        } else {
            helper.setText(R.id.tv_service, "维修");
        }


    }

}
