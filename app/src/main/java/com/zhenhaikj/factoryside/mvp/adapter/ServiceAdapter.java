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
    }
}
