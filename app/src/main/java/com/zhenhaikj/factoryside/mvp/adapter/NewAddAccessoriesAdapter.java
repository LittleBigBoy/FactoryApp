package com.zhenhaikj.factoryside.mvp.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.bean.Accessory;
import com.zhenhaikj.factoryside.mvp.bean.Accessory2;


import java.util.List;

public class NewAddAccessoriesAdapter extends BaseQuickAdapter<Accessory2, BaseViewHolder> {
    public NewAddAccessoriesAdapter(int layoutResId, @Nullable List<Accessory2> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, Accessory2 item) {
        helper.setText(R.id.tv_ac_name,item.getAccessoryName());
        helper.addOnClickListener(R.id.img_add);
        helper.addOnLongClickListener(R.id.img_add);

    }
}
