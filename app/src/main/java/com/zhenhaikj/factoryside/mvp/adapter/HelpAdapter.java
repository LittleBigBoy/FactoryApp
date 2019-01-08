package com.zhenhaikj.factoryside.mvp.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.bean.HelpBean;

import java.util.List;

public class HelpAdapter extends BaseQuickAdapter<HelpBean,BaseViewHolder> {
    public HelpAdapter(int layoutResId, List<HelpBean> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, HelpBean item) {
        // 加载网络图片
        helper.setText(R.id.question,"问："+item.getQuestion());
        helper.setText(R.id.answer,"答："+item.getContent());
    }
}
