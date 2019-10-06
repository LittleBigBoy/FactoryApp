package com.zhenhaikj.factoryside.mvp.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.bean.Accessory;
import com.zhenhaikj.factoryside.mvp.bean.Accessory2;

import java.util.List;

import io.reactivex.annotations.Nullable;

/**
 * Data:2019/7/30
 * Time:15:33
 * author:ying
 **/
public class MyPackageAdapter extends BaseQuickAdapter<Accessory2, BaseViewHolder> {

    public MyPackageAdapter(int layoutResId, @Nullable List<Accessory2> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, Accessory2 item) {
        helper.setText(R.id.tv_ac_name,item.getAccessoryName());
        helper.setText(R.id.tv_num,"数量:"+item.getCount()+"件");
    }
}
