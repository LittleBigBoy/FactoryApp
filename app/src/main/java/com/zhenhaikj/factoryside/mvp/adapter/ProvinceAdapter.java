package com.zhenhaikj.factoryside.mvp.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.bean.Accessory;
import com.zhenhaikj.factoryside.mvp.bean.Province;

import java.util.List;

public class ProvinceAdapter extends BaseQuickAdapter<Province,BaseViewHolder> {
    public ProvinceAdapter(int layoutResId, List<Province> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, Province item) {
        // 加载网络图片
//        Glide.with(mContext).load(item.getImage()).into((ImageView) helper.getView(R.id.icon));
        helper.setText(R.id.tv_category,item.getName());
    }
}
