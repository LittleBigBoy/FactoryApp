package com.zhenhaikj.factoryside.mvp.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.bean.Bill;
import com.zhenhaikj.factoryside.mvp.bean.MonthBill;

import java.util.List;

public class MonthBillAdapter extends BaseQuickAdapter<MonthBill.DataBean,BaseViewHolder> {
    public MonthBillAdapter(int layoutResId, List<MonthBill.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MonthBill.DataBean item) {
        helper.setText(R.id.time_tv,item.getYear()+"-"+item.getMonth())
                .setText(R.id.recharge_money_tv,"+"+item.getMoney1())
                .setText(R.id.expenditure_money_tv,"-"+item.getMoney2());


    }

}
