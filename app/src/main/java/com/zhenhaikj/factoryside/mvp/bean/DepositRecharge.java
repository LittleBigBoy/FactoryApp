package com.zhenhaikj.factoryside.mvp.bean;

import java.io.Serializable;
import java.util.List;

public class DepositRecharge implements Serializable {


    /**
     * code : 0
     * msg : success
     * count : 1
     * data : [{"Id":2205,"FinancialID":2205,"PayTypeCode":"Alipay","PayTypeName":"支付宝","PayMoney":0.01,"ThirdPartyNo":"2019090922001467521052264882","OutTradeNo":"1568014378624600624600","CreateTime":"2019-09-09T15:32:58","State":"1","StateName":"充值诚意金","IsInvoice":"0","OrderID":0,"IsUse":"Y","UserID":"15757964771","BisID":null,"BuyerAccount":"765***@qq.com","ActualMoney":0,"ShareMoney":0,"ShareUserId":null,"page":1,"limit":999,"StartTime":null,"EndTime":null,"StateList":[],"Version":0}]
     */

    private String code;
    private String msg;
    private String count;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * Id : 2205
         * FinancialID : 2205
         * PayTypeCode : Alipay
         * PayTypeName : 支付宝
         * PayMoney : 0.01
         * ThirdPartyNo : 2019090922001467521052264882
         * OutTradeNo : 1568014378624600624600
         * CreateTime : 2019-09-09T15:32:58
         * State : 1
         * StateName : 充值诚意金
         * IsInvoice : 0
         * OrderID : 0
         * IsUse : Y
         * UserID : 15757964771
         * BisID : null
         * BuyerAccount : 765***@qq.com
         * ActualMoney : 0
         * ShareMoney : 0
         * ShareUserId : null
         * page : 1
         * limit : 999
         * StartTime : null
         * EndTime : null
         * StateList : []
         * Version : 0
         */

        private int Id;
        private int FinancialID;
        private String PayTypeCode;
        private String PayTypeName;
        private double PayMoney;
        private String ThirdPartyNo;
        private String OutTradeNo;
        private String CreateTime;
        private String State;
        private String StateName;
        private String IsInvoice;
        private int OrderID;
        private String IsUse;
        private String UserID;
        private Object BisID;
        private String BuyerAccount;
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

        public double getPayMoney() {
            return PayMoney;
        }

        public void setPayMoney(double PayMoney) {
            this.PayMoney = PayMoney;
        }

        public String getThirdPartyNo() {
            return ThirdPartyNo;
        }

        public void setThirdPartyNo(String ThirdPartyNo) {
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

        public String getBuyerAccount() {
            return BuyerAccount;
        }

        public void setBuyerAccount(String BuyerAccount) {
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
