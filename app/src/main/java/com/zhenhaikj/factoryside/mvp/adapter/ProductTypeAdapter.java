package com.zhenhaikj.factoryside.mvp.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.bean.Brand;
import com.zhenhaikj.factoryside.mvp.bean.ProductType;

import java.util.List;

public class ProductTypeAdapter extends BaseQuickAdapter<ProductType,BaseViewHolder> {
    public ProductTypeAdapter(int layoutResId, List<ProductType> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, ProductType item) {
        // 加载网络图片
//        Glide.with(mContext).load(item.getImage()).into((ImageView) helper.getView(R.id.icon));
        helper.setText(R.id.tv_category,item.getFProductTypeName());
    }
}
