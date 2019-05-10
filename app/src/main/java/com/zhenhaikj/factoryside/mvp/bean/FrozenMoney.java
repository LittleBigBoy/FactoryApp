package com.zhenhaikj.factoryside.mvp.bean;

import java.io.Serializable;

public class FrozenMoney implements Serializable {

    /*
    * "Id":1935,
    * "PayID":1935,
    * "ItemID":10,
    * "OrderID":2000001061,
    * "PayTypeCode":"Account",
    * "PayName":"账户余额",
    * "UserID":"15990491614",
    * "State":null,
    * "CreateTime":"2019-05-09T12:31:11",
    * "PayMoney":9.00,
    * "TypeID":"2",
    * "ApplyNum":0,
    * "QApplyNum":0,
    * "IsUse":"Y",
    * "Relation":"aad9e978-e966-4733-8436-6c3ec2461ce5",
    * "Version":0
    * */

    private String Id;
    private String PayID;
    private String ItemID;
    private String OrderID;
    private String PayTypeCode;
    private String PayName;
    private String UserID;
    private String State;
    private String CreateTime;
    private String PayMoney;
    private String TypeID;
    private String ApplyNum;
    private String QApplyNum;
    private String IsUse;
    private String Relation;
    private String Version;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getPayID() {
        return PayID;
    }

    public void setPayID(String payID) {
        PayID = payID;
    }

    public String getItemID() {
        return ItemID;
    }

    public void setItemID(String itemID) {
        ItemID = itemID;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public String getPayTypeCode() {
        return PayTypeCode;
    }

    public void setPayTypeCode(String payTypeCode) {
        PayTypeCode = payTypeCode;
    }

    public String getPayName() {
        return PayName;
    }

    public void setPayName(String payName) {
        PayName = payName;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getPayMoney() {
        return PayMoney;
    }

    public void setPayMoney(String payMoney) {
        PayMoney = payMoney;
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

    public String getIsUse() {
        return IsUse;
    }

    public void setIsUse(String isUse) {
        IsUse = isUse;
    }

    public String getRelation() {
        return Relation;
    }

    public void setRelation(String relation) {
        Relation = relation;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }
}
