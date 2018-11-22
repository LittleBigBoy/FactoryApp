package com.emjiayuan.nll.model;

import java.io.Serializable;

public class HomeMenu2 implements Serializable {
    private int mDrawable;
    private String mTitle;
    private String mContent;

    public HomeMenu2() {
    }

    public HomeMenu2(int drawable, String title, String content) {
        this.mDrawable = drawable;
        this.mTitle = title;
        this.mContent = content;
    }

    public int getDrawable() {
        return mDrawable;
    }

    public void setDrawable(int drawable) {
        this.mDrawable = drawable;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        this.mContent = content;
    }
}
