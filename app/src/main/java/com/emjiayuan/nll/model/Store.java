package com.emjiayuan.nll.model;

import java.io.Serializable;

public class Store implements Serializable {
    private int mIcon;
    private String mName;
    private float mStars;
    private String mAddress;
    private String mDistance;

    public Store() {
    }

    public Store(int icon, String name, int stars, String address, String distance) {
        this.mIcon = icon;
        this.mName = name;
        this.mStars = stars;
        this.mAddress = address;
        this.mDistance = distance;
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

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        this.mAddress = address;
    }

    public String getDistance() {
        return mDistance;
    }

    public void setDistance(String distance) {
        this.mDistance = distance;
    }
}
