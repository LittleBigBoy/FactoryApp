package com.zhenhaikj.factoryside.mvp.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.bean.Accessory2;
import com.zhenhaikj.factoryside.mvp.bean.GetProdCategoryResult;

import java.util.List;

public class AccAdapter extends BaseQuickAdapter<Accessory2,BaseViewHolder> {
    public AccAdapter(int layoutResId, List<Accessory2> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, Accessory2 item) {
        // 加载网络图片
//        Glide.with(mContext).load(item.getImage()).into((ImageView) helper.getView(R.id.icon));
        helper.setText(R.id.tv_category,item.getAccessoryName()+"("+item.getCount()+"个)");
    }
}
