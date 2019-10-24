package com.zhenhaikj.factoryside.mvp.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.bean.Category;
import com.zhenhaikj.factoryside.mvp.bean.GetCategory;

import java.util.List;

public class CategoryAdapter2 extends BaseQuickAdapter<GetCategory,BaseViewHolder> {
    public CategoryAdapter2(int layoutResId, List<GetCategory> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, GetCategory item) {
        // 加载网络图片
//        Glide.with(mContext).load(item.getImage()).into((ImageView) helper.getView(R.id.icon));
        helper.setText(R.id.tv_category,item.getProductTypeName());
    }
}
