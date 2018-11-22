package com.emjiayuan.nll.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.model.MultipleItem;

public class MultiDelegateAdapter extends BaseQuickAdapter<MultipleItem, BaseViewHolder> {

    public MultiDelegateAdapter() {
        super(null);
        //Step.1
        setMultiTypeDelegate(new MultiTypeDelegate<MultipleItem>() {
            @Override
            protected int getItemType(MultipleItem entity) {
                //根据你的实体类来判断布局类型
                return entity.getItemType();
            }
        });
        //Step.2
        getMultiTypeDelegate()
                .registerItemType(MultipleItem.MENU1, R.layout.menu_item)
                .registerItemType(MultipleItem.MENU2, R.layout.menu_item2);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem entity) {
        //Step.3
        switch (helper.getItemViewType()) {
            case MultipleItem.MENU1:
                // do something
                break;
            case MultipleItem.MENU2:
                // do something
                break;
        }
    }
}
