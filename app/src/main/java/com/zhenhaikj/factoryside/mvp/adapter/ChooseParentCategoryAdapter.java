package com.zhenhaikj.factoryside.mvp.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.bean.Category;

import java.util.List;

public class ChooseParentCategoryAdapter extends BaseQuickAdapter<Category.DataBean, BaseViewHolder> {
    public ChooseParentCategoryAdapter(int layoutResId, @Nullable List<Category.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Category.DataBean item) {
        helper.setText(R.id.tv_category,item.getFCategoryName());
    }
}
