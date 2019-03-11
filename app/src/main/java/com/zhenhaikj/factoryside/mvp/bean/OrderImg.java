package com.zhenhaikj.factoryside.mvp.bean;

import java.io.Serializable;

public class OrderImg implements Serializable {

    /**
     * Id : 11
     * OrderFinishID : 11
     * Url : b34e7ff3fe6c45c7834f552be3db7f0c.jpg
     * OrderID : 2000000185
     * CreateTime : 2019-03-09T15:39:52
     * IsUse : Y
     * Type : 1
     * page : 1
     * limit : 999
     * Version : 0
     */

    private String Id;
    private String OrderFinishID;
    private String Url;
    private String OrderID;
    private String CreateTime;
    private String IsUse;
    private String Type;
    private String page;
    private String limit;
    private String Version;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getOrderFinishID() {
        return OrderFinishID;
    }

    public void setOrderFinishID(String OrderFinishID) {
        this.OrderFinishID = OrderFinishID;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String Url) {
        this.Url = Url;
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

    public String getIsUse() {
        return IsUse;
    }

    public void setIsUse(String IsUse) {
        this.IsUse = IsUse;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
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
