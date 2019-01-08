package com.zhenhaikj.factoryside.mvp.bean;

import java.io.Serializable;

public class Temple implements Serializable {
    private int mIcon;
    private String mName;
    private String mAddress;
    private String mDistance;

    public Temple() {
    }

    public Temple(int icon, String name, String address, String distance) {
        this.mIcon = icon;
        this.mName = name;
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
