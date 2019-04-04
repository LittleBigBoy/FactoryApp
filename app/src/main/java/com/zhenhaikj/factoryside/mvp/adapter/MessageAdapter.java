package com.zhenhaikj.factoryside.mvp.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.bean.Message;

import java.util.List;

import androidx.annotation.Nullable;

public class MessageAdapter extends BaseQuickAdapter<Message, BaseViewHolder> {
    public MessageAdapter(int layoutResId, @Nullable List<Message> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Message item) {
            helper.setText(R.id.tv_order_message,"工单消息:"+item.getContent());
              StringBuilder stringBuilder = new StringBuilder(item.getNowtime());
              String time = "" + stringBuilder.replace(10, 11, " "); //替换"T"为" "
            helper.setText(R.id.tv_order_time,"时间:"+time);
    }
}
