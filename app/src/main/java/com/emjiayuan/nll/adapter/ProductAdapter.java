package com.emjiayuan.nll.adapter;

import android.graphics.Paint;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.model.Product;

import java.util.List;

public class ProductAdapter extends BaseQuickAdapter<Product,BaseViewHolder> {
    public ProductAdapter(int layoutResId, List<Product> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, Product item) {
//        helper.setImageResource(R.id.icon, item.getIcon());
        helper.setText(R.id.tv_name, item.getName());
        helper.setText(R.id.tv_price,"￥"+item.getPrice());
        helper.setText(R.id.tv_oldprice, "￥"+item.getPreprice());
        ((TextView)helper.getView(R.id.tv_oldprice)).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        ((TextView)helper.getView(R.id.tv_oldprice)).getPaint().setAntiAlias(true);
        helper.addOnClickListener(R.id.iv_add);

        // 加载网络图片
        Glide.with(mContext).load(item.getImages()).into((ImageView) helper.getView(R.id.icon));
    }
}
