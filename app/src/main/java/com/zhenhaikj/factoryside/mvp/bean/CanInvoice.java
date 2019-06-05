package com.zhenhaikj.factoryside.mvp.bean;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

public class CanInvoice implements Serializable {

    /**
     * Id : 1230
     * FinancialID : 1230
     * PayTypeCode : Account
     * PayTypeName : null
     * PayMoney : 40
     * ThirdPartyNo : null
     * OutTradeNo : null
     * CreateTime : 2019-06-04T10:26:42
     * State : 1
     * StateName : null
     * IsInvoice : 0
     * OrderID : 0
     * IsUse : Y
     * UserID : 15990491614
     * BisID : null
     * BuyerAccount : null
     * page : 1
     * limit : 999
     * StartTime : null
     * EndTime : null
     * StateList : []
     * Version : 0
     */

    private String Id;
    private int FinancialID;
    private String PayTypeCode;
    private String PayTypeName;
    private String PayMoney;
    private String ThirdPartyNo;
    private String OutTradeNo;
    private String CreateTime;
    private String State;
    private String StateName;
    private String IsInvoice;
    private int OrderID;
    private String IsUse;
    private String UserID;
    private String BisID;
    private String BuyerAccount;
    private int page;
    private int limit;
    private String StartTime;
    private String EndTime;
    private String Version;
    private List<?> StateList;
    private boolean ischeck;

    public boolean isIscheck() {
        return ischeck;
    }

    public void setIscheck(boolean ischeck) {
        this.ischeck = ischeck;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public int getFinancialID() {
        return FinancialID;
    }

    public void setFinancialID(int financialID) {
        FinancialID = financialID;
    }

    public String getPayTypeCode() {
        return PayTypeCode;
    }

    public void setPayTypeCode(String payTypeCode) {
        PayTypeCode = payTypeCode;
    }

    public String getPayTypeName() {
        return PayTypeName;
    }

    public void setPayTypeName(String payTypeName) {
        PayTypeName = payTypeName;
    }

    public String getPayMoney() {
        return PayMoney;
    }

    public void setPayMoney(String payMoney) {
        PayMoney = payMoney;
    }

    public String getThirdPartyNo() {
        return ThirdPartyNo;
    }

    public void setThirdPartyNo(String thirdPartyNo) {
        ThirdPartyNo = thirdPartyNo;
    }

    public String getOutTradeNo() {
        return OutTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        OutTradeNo = outTradeNo;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getStateName() {
        return StateName;
    }

    public void setStateName(String stateName) {
        StateName = stateName;
    }

    public String getIsInvoice() {
        return IsInvoice;
    }

    public void setIsInvoice(String isInvoice) {
        IsInvoice = isInvoice;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int orderID) {
        OrderID = orderID;
    }

    public String getIsUse() {
        return IsUse;
    }

    public void setIsUse(String isUse) {
        IsUse = isUse;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getBisID() {
        return BisID;
    }

    public void setBisID(String bisID) {
        BisID = bisID;
    }

    public String getBuyerAccount() {
        return BuyerAccount;
    }

    public void setBuyerAccount(String buyerAccount) {
        BuyerAccount = buyerAccount;
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

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }

    public List<?> getStateList() {
        return StateList;
    }

    public void setStateList(List<?> StateList) {
        this.StateList = StateList;
    }
}
