package com.zhenhaikj.factoryside.mvp.bean;

import java.io.Serializable;

public class GAccessory implements Serializable {



    /*继续添加配件时获取可以已经添加的配件*/
    /**
     * "Id":737,"
     * AccessoryID":737,
     * "FAccessoryID":22,
     * "FAccessoryName":"更换门体",
     * "SendState":"Y",
     * "Quantity":1,
     * "OrderID":2000001107,
     * "CreateTime":2019-05-10T15:03:50",
     * "Relation":"af8c7a57-39ec-425c-9459-60bb2e22f41e",
     * "Price":66.00,
     * "DiscountPrice":66.00,
     * "IsUse":"Y",
     * "IsPay":"Y",
     * "ExpressNo":"123456789",
     * "State":"1",
     * "AccessoryState":"0",
     * "TypeID":"1",
     * "ApplyNum":1,
     * "QApplyNum":0,
     * "Version":0
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
    private String State;
    private String IsUse;
    private String Version;
    private String IsPay;
    private String ExpressNo;
    private String AccessoryState;
    private String TypeID;
    private String ApplyNum;
    private String QApplyNum;
    private String Photo1;
    private String Photo2;
    private String SizeID;

    public String getSizeID() {
        return SizeID;
    }

    public void setSizeID(String sizeID) {
        SizeID = sizeID;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

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

    public String getIsPay() {
        return IsPay;
    }

    public void setIsPay(String isPay) {
        IsPay = isPay;
    }

    public String getExpressNo() {
        return ExpressNo;
    }

    public void setExpressNo(String expressNo) {
        ExpressNo = expressNo;
    }

    public String getAccessoryState() {
        return AccessoryState;
    }

    public void setAccessoryState(String accessoryState) {
        AccessoryState = accessoryState;
    }

    public String getTypeID() {
        return TypeID;
    }

    public void setTypeID(String typeID) {
        TypeID = typeID;
    }

    public String getApplyNum() {
        return ApplyNum;
    }

    public void setApplyNum(String applyNum) {
        ApplyNum = applyNum;
    }

    public String getQApplyNum() {
        return QApplyNum;
    }

    public void setQApplyNum(String QApplyNum) {
        this.QApplyNum = QApplyNum;
    }

    public String getPhoto1() {
        return Photo1;
    }

    public void setPhoto1(String photo1) {
        Photo1 = photo1;
    }

    public String getPhoto2() {
        return Photo2;
    }

    public void setPhoto2(String photo2) {
        Photo2 = photo2;
    }
}
