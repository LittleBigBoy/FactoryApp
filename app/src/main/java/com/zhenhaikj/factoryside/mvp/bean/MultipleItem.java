package com.zhenhaikj.factoryside.mvp.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class MultipleItem implements MultiItemEntity {
    public static final int MENU1 = 1;
    public static final int MENU2 = 2;
    public static final int SIZE1 = 4;
    public static final int SIZE2 = 2;
    private int mItemType;
    private int mSpanSize;
    private HomeMenu mHomeMenu;
    private HomeMenu2 mHomeMenu2;

    public MultipleItem(int itemType, int spanSize, HomeMenu homeMenu, HomeMenu2 homeMenu2) {
        this.mItemType = itemType;
        this.mSpanSize = spanSize;
        mHomeMenu = homeMenu;
        mHomeMenu2 = homeMenu2;
    }

    public MultipleItem(int itemType, int spanSize) {
        this.mItemType = itemType;
        this.mSpanSize = spanSize;
    }

    public int getSpanSize() {
        return mSpanSize;
    }

    public void setSpanSize(int spanSize) {
        this.mSpanSize = spanSize;
    }

    @Override
    public int getItemType() {
        return mItemType;
    }
}
