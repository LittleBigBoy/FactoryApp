package com.zhenhaikj.factoryside.mvp.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.bean.GetFactoryProdResult;
import com.zhenhaikj.factoryside.mvp.bean.GetProdModelResult;

import java.util.List;

public class ProdModelCommonAdapter extends BaseQuickAdapter<GetFactoryProdResult.DataBean,BaseViewHolder> {
    public ProdModelCommonAdapter(int layoutResId, List<GetFactoryProdResult.DataBean> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, GetFactoryProdResult.DataBean item) {
        // 加载网络图片
//        Glide.with(mContext).load(item.getImage()).into((ImageView) helper.getView(R.id.icon));
        helper.setText(R.id.tv_category,item.getBrandName()+"--"+item.getProdModel());
    }
}
