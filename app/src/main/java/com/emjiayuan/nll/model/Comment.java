package com.emjiayuan.nll.model;

import java.io.Serializable;

public class Comment implements Serializable {
    private int mIcon;
    private String mName;
    private float mStars;
    private String mContent;
    private String mTime;

    public Comment() {
    }

    public Comment(int icon, String name, float stars, String content, String time) {
        this.mIcon = icon;
        this.mName = name;
        this.mStars = stars;
        this.mContent = content;
        this.mTime = time;
    }

    public int getIcon() {
        return mIcon;
    }

    public void setIcon(int icon) {
        this.mIcon = icon;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public float getStars() {
        return mStars;
    }

    public void setStars(float stars) {
        this.mStars = stars;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        this.mContent = content;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        this.mTime = time;
    }
}
