package com.zhenhaikj.factoryside.mvp.adapter;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.bean.Address;
import com.zhenhaikj.factoryside.mvp.bean.Bill;

import java.util.List;

public class BillAdapter extends BaseQuickAdapter<Bill.DataBean,BaseViewHolder> {
    public BillAdapter(int layoutResId, List<Bill.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Bill.DataBean item) {
        //收入或支出

        if (item.getState().equals("2")||item.getState().equals("5")){
            StringBuilder stringBuilder = new StringBuilder(item.getCreateTime());
            String time = "" + stringBuilder.replace(10, 11, " "); //去掉T
            helper.setText(R.id.time_tv,time);

            if (item.getState().equals("2")){
                double recharge=item.getPayMoney();
                helper.setText(R.id.recharge_tv,"充值");
//                helper.setText(R.id.recharge_money_tv,"+");
                helper.setText(R.id.recharge_money_tv,"+"+item.getPayMoney());
//                helper.setTextColor(R.id.expenditure_money_tv, Color.GREEN);
            }else {
                helper.setText(R.id.recharge_tv,"支出");
//                helper.setText(R.id.tv_record_add_reduce,"-");
                helper.setText(R.id.expenditure_money_tv,"-"+item.getPayMoney());
//                helper.setTextColor(R.id.tv_record_money, Color.RED);
            }

        }

    }


}
