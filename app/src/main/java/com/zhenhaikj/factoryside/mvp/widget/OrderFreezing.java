package com.zhenhaikj.factoryside.mvp.widget;

import java.io.Serializable;

public class OrderFreezing implements Serializable {

    /**
     * Id : 5548
     * PayID : 5548
     * ItemID : 10
     * OrderID : 2000003217
     * PayTypeCode : Account
     * PayName : 账户余额
     * UserID : 15757964771
     * State : 6
     * AccessoryState : null
     * CreateTime : 2019-11-27T09:33:32
     * PayMoney : 115
     * TypeID : 1
     * ApplyNum : 0
     * QApplyNum : 0
     * IsUse : Y
     * Relation : c0c793d1-a526-4a4c-a1fc-1ba3e6d19a9b
     * OrderAccessoryId : 0
     * page : 0
     * limit : 0
     * CreateTimeStart : null
     * CreateTimeEnd : null
     * Version : 0
     */

    private int Id;
    private int PayID;
    private int ItemID;
    private int OrderID;
    private String PayTypeCode;
    private String PayName;
    private String UserID;
    private String State;
    private Object AccessoryState;
    private String CreateTime;
    private Double PayMoney;
    private String TypeID;
    private int ApplyNum;
    private int QApplyNum;
    private String IsUse;
    private String Relation;
    private int OrderAccessoryId;
    private int page;
    private int limit;
    private Object CreateTimeStart;
    private Object CreateTimeEnd;
    private int Version;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getPayID() {
        return PayID;
    }

    public void setPayID(int PayID) {
        this.PayID = PayID;
    }

    public int getItemID() {
        return ItemID;
    }

    public void setItemID(int ItemID) {
        this.ItemID = ItemID;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public String getPayTypeCode() {
        return PayTypeCode;
    }

    public void setPayTypeCode(String PayTypeCode) {
        this.PayTypeCode = PayTypeCode;
    }

    public String getPayName() {
        return PayName;
    }

    public void setPayName(String PayName) {
        this.PayName = PayName;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public Object getAccessoryState() {
        return AccessoryState;
    }

    public void setAccessoryState(Object AccessoryState) {
        this.AccessoryState = AccessoryState;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public Double getPayMoney() {
        return PayMoney;
    }

    public void setPayMoney(Double PayMoney) {
        this.PayMoney = PayMoney;
    }

    public String getTypeID() {
        return TypeID;
    }

    public void setTypeID(String TypeID) {
        this.TypeID = TypeID;
    }

    public int getApplyNum() {
        return ApplyNum;
    }

    public void setApplyNum(int ApplyNum) {
        this.ApplyNum = ApplyNum;
    }

    public int getQApplyNum() {
        return QApplyNum;
    }

    public void setQApplyNum(int QApplyNum) {
        this.QApplyNum = QApplyNum;
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

    public int getOrderAccessoryId() {
        return OrderAccessoryId;
    }

    public void setOrderAccessoryId(int OrderAccessoryId) {
        this.OrderAccessoryId = OrderAccessoryId;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public Object getCreateTimeStart() {
        return CreateTimeStart;
    }

    public void setCreateTimeStart(Object CreateTimeStart) {
        this.CreateTimeStart = CreateTimeStart;
    }

    public Object getCreateTimeEnd() {
        return CreateTimeEnd;
    }

    public void setCreateTimeEnd(Object CreateTimeEnd) {
        this.CreateTimeEnd = CreateTimeEnd;
    }

    public int getVersion() {
        return Version;
    }

    public void setVersion(int Version) {
        this.Version = Version;
    }
}
