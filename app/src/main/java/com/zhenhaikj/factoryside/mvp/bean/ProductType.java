package com.zhenhaikj.factoryside.mvp.bean;

import java.io.Serializable;

public class ProductType implements Serializable {


    /**
     * Id : 27
     * FProductTypeID : 27
     * FProductTypeName : 哦哦
     * FBrandID : 62
     * FBrandName : 品牌1
     * FCategoryID : 197
     * FCategoryName : 商用油水分离油炸锅
     * InitPrice : 0.0
     * IsUse : Y
     * FParentCategoryName : 商用烘烤煮类
     * FParentCategoryID : 25
     * Version : 0
     */

    private String Id;
    private String FProductTypeID;
    private String FProductTypeName;
    private String FBrandID;
    private String FBrandName;
    private String FCategoryID;
    private String FCategoryName;
    private String InitPrice;
    private String IsUse;
    private String FParentCategoryName;
    private String FParentCategoryID;
    private String Version;

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

    public String getFBrandName() {
        return FBrandName;
    }

    public void setFBrandName(String FBrandName) {
        this.FBrandName = FBrandName;
    }

    public String getFCategoryID() {
        return FCategoryID;
    }

    public void setFCategoryID(String FCategoryID) {
        this.FCategoryID = FCategoryID;
    }

    public String getFCategoryName() {
        return FCategoryName;
    }

    public void setFCategoryName(String FCategoryName) {
        this.FCategoryName = FCategoryName;
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

    public String getFParentCategoryName() {
        return FParentCategoryName;
    }

    public void setFParentCategoryName(String FParentCategoryName) {
        this.FParentCategoryName = FParentCategoryName;
    }

    public String getFParentCategoryID() {
        return FParentCategoryID;
    }

    public void setFParentCategoryID(String FParentCategoryID) {
        this.FParentCategoryID = FParentCategoryID;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String Version) {
        this.Version = Version;
    }
}

