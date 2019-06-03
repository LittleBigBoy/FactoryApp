package com.zhenhaikj.factoryside.mvp.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.bean.Address;
import com.zhenhaikj.factoryside.mvp.bean.Brand;
import com.zhenhaikj.factoryside.mvp.bean.Category;
import com.zhenhaikj.factoryside.mvp.bean.Product;
import com.zhenhaikj.factoryside.mvp.bean.ProductType;

import java.util.List;

public class TypeAdapter extends BaseQuickAdapter<Category,BaseViewHolder> {
    public TypeAdapter(int layoutResId, List<Category> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Category item) {
        helper.setText(R.id.tv_brand,item.getBrandName())
                .setText(R.id.tv_category_name,item.getParentName())
//                .setText(R.id.tv_brand_name,item.getFCategoryName())
                .setText(R.id.tv_brand_number,item.getFCategoryName())
                .setText(R.id.tv_price,"¥"+item.getInitPrice())
                .addOnClickListener(R.id.iv_delete)
                .addOnClickListener(R.id.rl_brand);
    }


//    @Override
//    protected void convert(BaseViewHolder helper, Brand item) {
//        helper.setText(R.id.tv_brand,item.getFBrandName());
//    }
//    @Override
//    protected void convert(BaseViewHolder helper, Address item) {
//        // 加载网络图片
//        /*helper.setText(R.id.name,item.getUsername());
//        helper.setText(R.id.phone,item.getTelphone());
//        helper.setText(R.id.address,item.getAddress());
//        helper.addOnClickListener(R.id.edit_ll);
//        helper.addOnClickListener(R.id.delete_ll);
//        helper.addOnClickListener(R.id.default_ll);
//        if ("1".equals(item.getIs_default())){
//            helper.setChecked(R.id.check,true);
//        }else{
//            helper.setChecked(R.id.check,false);
//        }*/
//
////        helper.setText(R.id.tv_band)
//    }
}
