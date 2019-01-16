package com.zhenhaikj.factoryside.mvp.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.bean.Brand;

import java.util.List;

public class BrandsAdapter extends BaseQuickAdapter<Brand,BaseViewHolder> {
    public BrandsAdapter(int layoutResId, List<Brand> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, Brand item) {
        // 加载网络图片
//        Glide.with(mContext).load(item.getImage()).into((ImageView) helper.getView(R.id.icon));
        helper.setText(R.id.tv_category,item.getFBrandName());
    }
}
