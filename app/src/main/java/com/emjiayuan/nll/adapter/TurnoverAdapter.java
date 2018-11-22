package com.emjiayuan.nll.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.model.Turnover;

import java.util.List;

public class TurnoverAdapter extends BaseQuickAdapter<Turnover,BaseViewHolder> {
    public TurnoverAdapter(int layoutResId, List<Turnover> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, Turnover item) {
        helper.setText(R.id.text,item.getName());
    }
}
