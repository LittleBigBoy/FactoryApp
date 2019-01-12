package com.zhenhaikj.factoryside.mvp.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.bean.Category;

import java.util.List;

public class CategoryAdapter extends BaseQuickAdapter<Category,BaseViewHolder> {
    public CategoryAdapter(int layoutResId, List<Category> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, Category item) {
        // 加载网络图片
        Glide.with(mContext).load(item.getImage()).into((ImageView) helper.getView(R.id.icon));
    }
}
