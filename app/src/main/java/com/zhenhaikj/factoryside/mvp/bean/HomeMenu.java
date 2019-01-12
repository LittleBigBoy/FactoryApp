package com.zhenhaikj.factoryside.mvp.bean;

import java.io.Serializable;

public class HomeMenu implements Serializable {
    private int mDrawable;
    private String mLable;

    public HomeMenu() {
    }

    public HomeMenu(int drawable, String lable) {
        this.mDrawable = drawable;
        this.mLable = lable;
    }

    public int getDrawable() {
        return mDrawable;
    }

    public void setDrawable(int drawable) {
        this.mDrawable = drawable;
    }

    public String getLable() {
        return mLable;
    }

    public void setLable(String lable) {
        this.mLable = lable;
    }
}
