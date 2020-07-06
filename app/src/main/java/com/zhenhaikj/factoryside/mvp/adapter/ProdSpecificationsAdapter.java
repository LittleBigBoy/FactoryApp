package com.zhenhaikj.factoryside.mvp.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.bean.GetProdCategoryResult;
import com.zhenhaikj.factoryside.mvp.bean.GetProdSpecificationsResult;

import java.util.List;

public class ProdSpecificationsAdapter extends BaseQuickAdapter<GetProdSpecificationsResult.DataBean,BaseViewHolder> {
    public ProdSpecificationsAdapter(int layoutResId, List<GetProdSpecificationsResult.DataBean> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, GetProdSpecificationsResult.DataBean item) {
        // 加载网络图片
//        Glide.with(mContext).load(item.getImage()).into((ImageView) helper.getView(R.id.icon));
        helper.setText(R.id.tv_category,item.getFCategoryName());
    }
}
