package com.emjiayuan.nll.model;

import java.io.Serializable;

public class Privilege implements Serializable {
    private int mIcon;
    private String mLable;
    private String mContent;

    public Privilege() {
    }

    public Privilege(int icon, String lable, String content) {
        mIcon = icon;
        mLable = lable;
        mContent = content;
    }

    public int getIcon() {
        return mIcon;
    }

    public void setIcon(int icon) {
        mIcon = icon;
    }

    public String getLable() {
        return mLable;
    }

    public void setLable(String lable) {
        mLable = lable;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }
}
