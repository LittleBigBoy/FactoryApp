package com.zhenhaikj.factoryside.mvp.bean;

import java.io.Serializable;

public class GService implements Serializable {


    /**
     * Id : 34
     * OrderServiceID : 34
     * BrandID : 0
     * CategoryID : 0
     * ServiceName : 加氟
     * ServiceID : 2
     * Price : 50.0
     * DiscountPrice : 50.0
     * IsUse : Y
     * Relation : b3b069b2-1c3a-452b-a696-117b3f7597f7
     * OrderID : 2000000102
     * CreateTime : 2019-03-07T15:08:15
     * page : 1
     * limit : 999
     * Version : 0
     */

    private String Id;
    private String OrderServiceID;
    private String BrandID;
    private String CategoryID;
    private String ServiceName;
    private String ServiceID;
    private double Price;
    private double DiscountPrice;
    private String IsUse;
    private String Relation;
    private String OrderID;
    private String CreateTime;
    private String page;
    private String limit;
    private String Version;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getOrderServiceID() {
        return OrderServiceID;
    }

    public void setOrderServiceID(String OrderServiceID) {
        this.OrderServiceID = OrderServiceID;
    }

    public String getBrandID() {
        return BrandID;
    }

    public void setBrandID(String BrandID) {
        this.BrandID = BrandID;
    }

    public String getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(String CategoryID) {
        this.CategoryID = CategoryID;
    }

    public String getServiceName() {
        return ServiceName;
    }

    public void setServiceName(String ServiceName) {
        this.ServiceName = ServiceName;
    }

    public String getServiceID() {
        return ServiceID;
    }

    public void setServiceID(String ServiceID) {
        this.ServiceID = ServiceID;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public double getDiscountPrice() {
        return DiscountPrice;
    }

    public void setDiscountPrice(double DiscountPrice) {
        this.DiscountPrice = DiscountPrice;
    }

    public String getIsUse() {
        return IsUse;
    }

    public void setIsUse(String IsUse) {
        this.IsUse = IsUse;
    }

    public String getRelation() {
        return Relation;
    }

    public void setRelation(String Relation) {
        this.Relation = Relation;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String OrderID) {
        this.OrderID = OrderID;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
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
