package com.zhenhaikj.factoryside.mvp.bean;

import java.io.Serializable;

public class GetCategory implements Serializable {

    /**
     * Id : 45
     * BrandCategoryID : 45
     * BrandID : 213
     * BrandName : 小霸王
     * UserID : 15757964771
     * CategoryID : 287
     * CategoryName : 冰洗类
     * SubCategoryID : 281
     * SubCategoryName : 洗衣机
     * ProductTypeID : 283
     * ProductTypeName : 全自动波轮洗衣机
     * IsUse : Y
     * page : 1
     * limit : 999
     * Version : 0
     */

    private String Id;
    private String BrandCategoryID;
    private String BrandID;
    private String BrandName;
    private String UserID;
    private String CategoryID;
    private String CategoryName;
    private String SubCategoryID;
    private String SubCategoryName;
    private String ProductTypeID;
    private String ProductTypeName;
    private String IsUse;
    private String page;
    private String limit;
    private String Version;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getBrandCategoryID() {
        return BrandCategoryID;
    }

    public void setBrandCategoryID(String BrandCategoryID) {
        this.BrandCategoryID = BrandCategoryID;
    }

    public String getBrandID() {
        return BrandID;
    }

    public void setBrandID(String BrandID) {
        this.BrandID = BrandID;
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String BrandName) {
        this.BrandName = BrandName;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(String CategoryID) {
        this.CategoryID = CategoryID;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String CategoryName) {
        this.CategoryName = CategoryName;
    }

    public String getSubCategoryID() {
        return SubCategoryID;
    }

    public void setSubCategoryID(String SubCategoryID) {
        this.SubCategoryID = SubCategoryID;
    }

    public String getSubCategoryName() {
        return SubCategoryName;
    }

    public void setSubCategoryName(String SubCategoryName) {
        this.SubCategoryName = SubCategoryName;
    }

    public String getProductTypeID() {
        return ProductTypeID;
    }

    public void setProductTypeID(String ProductTypeID) {
        this.ProductTypeID = ProductTypeID;
    }

    public String getProductTypeName() {
        return ProductTypeName;
    }

    public void setProductTypeName(String ProductTypeName) {
        this.ProductTypeName = ProductTypeName;
    }

    public String getIsUse() {
        return IsUse;
    }

    public void setIsUse(String IsUse) {
        this.IsUse = IsUse;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String Version) {
        this.Version = Version;
    }
}
