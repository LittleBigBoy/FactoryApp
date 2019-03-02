package com.zhenhaikj.factoryside.mvp.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.bean.Area;
import com.zhenhaikj.factoryside.mvp.bean.District;

import java.util.List;

public class DistrictAdapter extends BaseQuickAdapter<District,BaseViewHolder> {
    public DistrictAdapter(int layoutResId, List<District> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, District item) {
        // 加载网络图片
//        Glide.with(mContext).load(item.getImage()).into((ImageView) helper.getView(R.id.icon));
        helper.setText(R.id.tv_category,item.getName());
    }
}
