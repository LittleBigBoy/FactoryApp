package com.zhenhaikj.factoryside.mvp.bean;

import java.io.Serializable;

public class ProductType implements Serializable {


    /**
     * Id : 0
     * FProductTypeID : 0
     * FProductTypeName : string
     * FBrandID : 0
     * FCategoryID : 0
     * InitPrice : 0
     * IsUse : string
     * Version : 0
     */

    private String Id;
    private String FProductTypeID;
    private String FProductTypeName;
    private String FBrandID;
    private String FCategoryID;
    private String InitPrice;
    private String IsUse;
    private String Version;
    private String FBrandName;
    private String FCategoryName;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getFProductTypeID() {
        return FProductTypeID;
    }

    public void setFProductTypeID(String FProductTypeID) {
        this.FProductTypeID = FProductTypeID;
    }

    public String getFProductTypeName() {
        return FProductTypeName;
    }

    public void setFProductTypeName(String FProductTypeName) {
        this.FProductTypeName = FProductTypeName;
    }

    public String getFBrandID() {
        return FBrandID;
    }

    public void setFBrandID(String FBrandID) {
        this.FBrandID = FBrandID;
    }

    public String getFCategoryID() {
        return FCategoryID;
    }

    public void setFCategoryID(String FCategoryID) {
        this.FCategoryID = FCategoryID;
    }

    public String getInitPrice() {
        return InitPrice;
    }

    public void setInitPrice(String InitPrice) {
        this.InitPrice = InitPrice;
    }

    public String getIsUse() {
        return IsUse;
    }

    public void setIsUse(String IsUse) {
        this.IsUse = IsUse;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String Version) {
        this.Version = Version;
    }

    public String getFBrandName() {
        return FBrandName;
    }

    public void setFBrandName(String FBrandName) {
        this.FBrandName = FBrandName;
    }

    public String getFCategoryName() {
        return FCategoryName;
    }

    public void setFCategoryName(String FCategoryName) {
        this.FCategoryName = FCategoryName;
    }
}

