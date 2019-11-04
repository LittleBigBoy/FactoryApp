package com.zhenhaikj.factoryside.mvp.widget;

import java.io.Serializable;

public class OrderFreezing implements Serializable {

    /**
     * Id : 255
     * FrozenID : 255
     * ExplainMemo : 发单费
     * UserID : 15757964771
     * Money : 75
     * CreateTime : 2019-10-31T17:13:38
     * IsUse : Y
     * OrderID : 2000002662
     * page : 0
     * limit : 0
     * Version : 0
     */

    private int Id;
    private int FrozenID;
    private String ExplainMemo;
    private String UserID;
    private Double Money;
    private String CreateTime;
    private String IsUse;
    private int OrderID;
    private int page;
    private int limit;
    private int Version;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getFrozenID() {
        return FrozenID;
    }

    public void setFrozenID(int FrozenID) {
        this.FrozenID = FrozenID;
    }

    public String getExplainMemo() {
        return ExplainMemo;
    }

    public void setExplainMemo(String ExplainMemo) {
        this.ExplainMemo = ExplainMemo;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public Double getMoney() {
        return Money;
    }

    public void setMoney(Double Money) {
        this.Money = Money;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public String getIsUse() {
        return IsUse;
    }

    public void setIsUse(String IsUse) {
        this.IsUse = IsUse;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
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

    public int getVersion() {
        return Version;
    }

    public void setVersion(int Version) {
        this.Version = Version;
    }
}
