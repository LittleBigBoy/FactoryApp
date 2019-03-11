package com.zhenhaikj.factoryside.mvp.bean;

import java.io.Serializable;

public class ReturnaccessoryImg implements Serializable {

    /**
     * Id : 29
     * ReturnAccessoryID : 29
     * OrderID : 2000000176
     * CeateTime : 2019-03-09T15:00:23
     * Url : 61b67cae620d46ae909176fbf6fc5661.jpg
     * Relation : null
     * IsUse : Y
     * page : 1
     * limit : 999
     * Version : 0
     */

    private String Id;
    private String ReturnAccessoryID;
    private String OrderID;
    private String CeateTime;
    private String Url;
    private String Relation;
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

    public String getReturnAccessoryID() {
        return ReturnAccessoryID;
    }

    public void setReturnAccessoryID(String ReturnAccessoryID) {
        this.ReturnAccessoryID = ReturnAccessoryID;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String OrderID) {
        this.OrderID = OrderID;
    }

    public String getCeateTime() {
        return CeateTime;
    }

    public void setCeateTime(String CeateTime) {
        this.CeateTime = CeateTime;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }

    public String getRelation() {
        return Relation;
    }

    public void setRelation(String Relation) {
        this.Relation = Relation;
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
