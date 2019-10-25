package com.zhenhaikj.factoryside.mvp.bean;

import java.io.Serializable;
import java.util.List;

public class Recharge implements Serializable {

    /**
     * Total : 12081.74
     * data1 : [{"Id":2675,"FinancialID":2675,"PayTypeCode":"Weixin","PayTypeName":"微信","PayMoney":1,"ThirdPartyNo":null,"OutTradeNo":"1569293556480167480167","CreateTime":"2019-09-24T10:52:36","State":"1","StateName":"充值余额","IsInvoice":"0","OrderID":0,"IsUse":"Y","UserID":"15757964771","BisID":null,"BuyerAccount":null,"ActualMoney":0,"ShareMoney":0,"ShareUserId":null,"page":0,"limit":0,"StartTime":null,"EndTime":null,"StateList":[],"Version":0},{"Id":2671,"FinancialID":2671,"PayTypeCode":"Weixin","PayTypeName":"微信","PayMoney":1,"ThirdPartyNo":null,"OutTradeNo":"1569221127152752152752","CreateTime":"2019-09-23T14:45:27","State":"1","StateName":"充值余额","IsInvoice":"0","OrderID":0,"IsUse":"Y","UserID":"15757964771","BisID":null,"BuyerAccount":null,"ActualMoney":0,"ShareMoney":0,"ShareUserId":null,"page":0,"limit":0,"StartTime":null,"EndTime":null,"StateList":[],"Version":0},{"Id":2669,"FinancialID":2669,"PayTypeCode":"Weixin","PayTypeName":"微信","PayMoney":1,"ThirdPartyNo":null,"OutTradeNo":"1569220491455652455652","CreateTime":"2019-09-23T14:34:51","State":"1","StateName":"充值余额","IsInvoice":"0","OrderID":0,"IsUse":"Y","UserID":"15757964771","BisID":null,"BuyerAccount":null,"ActualMoney":0,"ShareMoney":0,"ShareUserId":null,"page":0,"limit":0,"StartTime":null,"EndTime":null,"StateList":[],"Version":0},{"Id":2666,"FinancialID":2666,"PayTypeCode":"Weixin","PayTypeName":"微信","PayMoney":0.01,"ThirdPartyNo":null,"OutTradeNo":"1569220377115597115597","CreateTime":"2019-09-23T14:32:57","State":"1","StateName":"充值余额","IsInvoice":"0","OrderID":0,"IsUse":"Y","UserID":"15757964771","BisID":null,"BuyerAccount":null,"ActualMoney":0,"ShareMoney":0,"ShareUserId":null,"page":0,"limit":0,"StartTime":null,"EndTime":null,"StateList":[],"Version":0},{"Id":2577,"FinancialID":2577,"PayTypeCode":"Weixin","PayTypeName":"微信","PayMoney":0.01,"ThirdPartyNo":null,"OutTradeNo":"1568957124417305417305","CreateTime":"2019-09-20T13:25:24","State":"1","StateName":"充值余额","IsInvoice":"0","OrderID":0,"IsUse":"Y","UserID":"15757964771","BisID":null,"BuyerAccount":null,"ActualMoney":0,"ShareMoney":0,"ShareUserId":null,"page":0,"limit":0,"StartTime":null,"EndTime":null,"StateList":[],"Version":0},{"Id":2530,"FinancialID":2530,"PayTypeCode":"Weixin","PayTypeName":"微信","PayMoney":1000,"ThirdPartyNo":null,"OutTradeNo":"1568875916121099121099","CreateTime":"2019-09-19T14:51:56","State":"1","StateName":"充值诚意金","IsInvoice":"0","OrderID":0,"IsUse":"Y","UserID":"15757964771","BisID":null,"BuyerAccount":null,"ActualMoney":0,"ShareMoney":0,"ShareUserId":null,"page":0,"limit":0,"StartTime":null,"EndTime":null,"StateList":[],"Version":0},{"Id":2205,"FinancialID":2205,"PayTypeCode":"Alipay","PayTypeName":"支付宝","PayMoney":0.01,"ThirdPartyNo":"2019090922001467521052264882","OutTradeNo":"1568014378624600624600","CreateTime":"2019-09-09T15:32:58","State":"1","StateName":"充值诚意金","IsInvoice":"0","OrderID":0,"IsUse":"Y","UserID":"15757964771","BisID":null,"BuyerAccount":"765***@qq.com","ActualMoney":0,"ShareMoney":0,"ShareUserId":null,"page":0,"limit":0,"StartTime":null,"EndTime":null,"StateList":[],"Version":0},{"Id":1887,"FinancialID":1887,"PayTypeCode":"Alipay","PayTypeName":"支付宝","PayMoney":0.01,"ThirdPartyNo":"2019081622001467520551124182","OutTradeNo":"1565925518608568608568","CreateTime":"2019-08-16T11:18:38","State":"1","StateName":"充值余额","IsInvoice":"0","OrderID":0,"IsUse":"Y","UserID":"15757964771","BisID":null,"BuyerAccount":"765***@qq.com","ActualMoney":0,"ShareMoney":0,"ShareUserId":null,"page":0,"limit":0,"StartTime":null,"EndTime":null,"StateList":[],"Version":0}]
     */

    private double Total;
    private List<Data1Bean> data1;

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }

    public List<Data1Bean> getData1() {
        return data1;
    }

    public void setData1(List<Data1Bean> data1) {
        this.data1 = data1;
    }

    public static class Data1Bean {
        /**
         * Id : 2675
         * FinancialID : 2675
         * PayTypeCode : Weixin
         * PayTypeName : 微信
         * PayMoney : 1
         * ThirdPartyNo : null
         * OutTradeNo : 1569293556480167480167
         * CreateTime : 2019-09-24T10:52:36
         * State : 1
         * StateName : 充值余额
         * IsInvoice : 0
         * OrderID : 0
         * IsUse : Y
         * UserID : 15757964771
         * BisID : null
         * BuyerAccount : null
         * ActualMoney : 0
         * ShareMoney : 0
         * ShareUserId : null
         * page : 0
         * limit : 0
         * StartTime : null
         * EndTime : null
         * StateList : []
         * Version : 0
         */

        private int Id;
        private int FinancialID;
        private String PayTypeCode;
        private String PayTypeName;
        private Double PayMoney;
        private Object ThirdPartyNo;
        private String OutTradeNo;
        private String CreateTime;
        private String State;
        private String StateName;
        private String IsInvoice;
        private int OrderID;
        private String IsUse;
        private String UserID;
        private Object BisID;
        private Object BuyerAccount;
        private int ActualMoney;
        private int ShareMoney;
        private Object ShareUserId;
        private int page;
        private int limit;
        private Object StartTime;
        private Object EndTime;
        private int Version;
        private List<?> StateList;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public int getFinancialID() {
            return FinancialID;
        }

        public void setFinancialID(int FinancialID) {
            this.FinancialID = FinancialID;
        }

        public String getPayTypeCode() {
            return PayTypeCode;
        }

        public void setPayTypeCode(String PayTypeCode) {
            this.PayTypeCode = PayTypeCode;
        }

        public String getPayTypeName() {
            return PayTypeName;
        }

        public void setPayTypeName(String PayTypeName) {
            this.PayTypeName = PayTypeName;
        }

        public Double getPayMoney() {
            return PayMoney;
        }

        public void setPayMoney(Double PayMoney) {
            this.PayMoney = PayMoney;
        }

        public Object getThirdPartyNo() {
            return ThirdPartyNo;
        }

        public void setThirdPartyNo(Object ThirdPartyNo) {
            this.ThirdPartyNo = ThirdPartyNo;
        }

        public String getOutTradeNo() {
            return OutTradeNo;
        }

        public void setOutTradeNo(String OutTradeNo) {
            this.OutTradeNo = OutTradeNo;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getState() {
            return State;
        }

        public void setState(String State) {
            this.State = State;
        }

        public String getStateName() {
            return StateName;
        }

        public void setStateName(String StateName) {
            this.StateName = StateName;
        }

        public String getIsInvoice() {
            return IsInvoice;
        }

        public void setIsInvoice(String IsInvoice) {
            this.IsInvoice = IsInvoice;
        }

        public int getOrderID() {
            return OrderID;
        }

        public void setOrderID(int OrderID) {
            this.OrderID = OrderID;
        }

        public String getIsUse() {
            return IsUse;
        }

        public void setIsUse(String IsUse) {
            this.IsUse = IsUse;
        }

        public String getUserID() {
            return UserID;
        }

        public void setUserID(String UserID) {
            this.UserID = UserID;
        }

        public Object getBisID() {
            return BisID;
        }

        public void setBisID(Object BisID) {
            this.BisID = BisID;
        }

        public Object getBuyerAccount() {
            return BuyerAccount;
        }

        public void setBuyerAccount(Object BuyerAccount) {
            this.BuyerAccount = BuyerAccount;
        }

        public int getActualMoney() {
            return ActualMoney;
        }

        public void setActualMoney(int ActualMoney) {
            this.ActualMoney = ActualMoney;
        }

        public int getShareMoney() {
            return ShareMoney;
        }

        public void setShareMoney(int ShareMoney) {
            this.ShareMoney = ShareMoney;
        }

        public Object getShareUserId() {
            return ShareUserId;
        }

        public void setShareUserId(Object ShareUserId) {
            this.ShareUserId = ShareUserId;
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

        public Object getStartTime() {
            return StartTime;
        }

        public void setStartTime(Object StartTime) {
            this.StartTime = StartTime;
        }

        public Object getEndTime() {
            return EndTime;
        }

        public void setEndTime(Object EndTime) {
            this.EndTime = EndTime;
        }

        public int getVersion() {
            return Version;
        }

        public void setVersion(int Version) {
            this.Version = Version;
        }

        public List<?> getStateList() {
            return StateList;
        }

        public void setStateList(List<?> StateList) {
            this.StateList = StateList;
        }
    }
}
