package com.zhenhaikj.factoryside.mvp.bean;

import java.io.Serializable;

public class GAccessory implements Serializable {



    /*继续添加配件时获取可以已经添加的配件*/
    /**
     * Id : 60
     * AccessoryID : 60
     * FAccessoryID : 1
     * FAccessoryName : PC管
     * SendState : N
     * Quantity : 5
     * OrderID : 2000000110
     * CreateTime : 2019-03-06T11:17:22
     * Relation : 96535e17-cf71-4956-b586-3c8fa4c1aca3
     * Price : 35
     * DiscountPrice : 35
     * IsUse : Y
     * Version : 0
     */

    private String Id;
    private String AccessoryID;
    private String FAccessoryID;
    private String FAccessoryName;
    private String SendState;
    private String Quantity;
    private String OrderID;
    private String CreateTime;
    private String Relation;
    private String Price;
    private String DiscountPrice;
    private String IsUse;
    private String Version;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getAccessoryID() {
        return AccessoryID;
    }

    public void setAccessoryID(String AccessoryID) {
        this.AccessoryID = AccessoryID;
    }

    public String getFAccessoryID() {
        return FAccessoryID;
    }

    public void setFAccessoryID(String FAccessoryID) {
        this.FAccessoryID = FAccessoryID;
    }

    public String getFAccessoryName() {
        return FAccessoryName;
    }

    public void setFAccessoryName(String FAccessoryName) {
        this.FAccessoryName = FAccessoryName;
    }

    public String getSendState() {
        return SendState;
    }

    public void setSendState(String SendState) {
        this.SendState = SendState;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String Quantity) {
        this.Quantity = Quantity;
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

    public String getRelation() {
        return Relation;
    }

    public void setRelation(String Relation) {
        this.Relation = Relation;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }

    public String getDiscountPrice() {
        return DiscountPrice;
    }

    public void setDiscountPrice(String DiscountPrice) {
        this.DiscountPrice = DiscountPrice;
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
}
