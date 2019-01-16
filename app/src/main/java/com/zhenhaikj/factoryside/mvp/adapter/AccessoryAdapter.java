package com.zhenhaikj.factoryside.mvp.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.bean.Accessory;

import java.util.List;

public class AccessoryAdapter extends BaseQuickAdapter<Accessory.Item1Bean,BaseViewHolder> {
    public AccessoryAdapter(int layoutResId, List<Accessory.Item1Bean> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, Accessory.Item1Bean item) {
        // 加载网络图片
//        Glide.with(mContext).load(item.getImage()).into((ImageView) helper.getView(R.id.icon));
        helper.setText(R.id.tv_category,item.getAccessoryName());
    }
}
