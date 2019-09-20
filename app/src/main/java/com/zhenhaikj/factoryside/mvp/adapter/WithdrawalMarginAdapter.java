package com.zhenhaikj.factoryside.mvp.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.bean.Address;
import com.zhenhaikj.factoryside.mvp.bean.DepositWithDraw;

import java.util.List;

public class WithdrawalMarginAdapter extends BaseQuickAdapter<DepositWithDraw.DataBean,BaseViewHolder> {
    public WithdrawalMarginAdapter(int layoutResId, List<DepositWithDraw.DataBean> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, DepositWithDraw.DataBean item) {
        // 加载网络图片
        helper.setText(R.id.money_tv,item.getPayMoney()+"");
        StringBuilder stringBuilder = new StringBuilder(item.getCreateTime());
        String time = "" + stringBuilder.replace(10, 11, " "); //去掉T
        helper.setText(R.id.time_tv,time);
    }
}
