package com.zhenhaikj.factoryside.mvp.bean;

import java.io.Serializable;
import java.util.List;

public class Freezing implements Serializable {

    /**
     * Total : 95
     * data : [{"Id":2,"FrozenID":2,"Explain":"安装费","UserID":"15757964771","Money":45,"CreateTime":"2019-10-20T00:00:00","IsUse":"Y","OrderID":"2000123123","page":1,"limit":999,"Version":0},{"Id":3,"FrozenID":3,"Explain":"安装费","UserID":"15757964771","Money":25,"CreateTime":"2019-10-20T00:00:00","IsUse":"Y","OrderID":"2000002463","page":1,"limit":999,"Version":0},{"Id":4,"FrozenID":4,"Explain":"维修费","UserID":"15757964771","Money":25,"CreateTime":"2019-10-20T00:00:00","IsUse":"Y","OrderID":"2000002463","page":1,"limit":999,"Version":0}]
     */


    private int Total;
    private List<DataBean> data;
    private String Money;

    public String getMoney() {
        return Money;
    }

    public void setMoney(String money) {
        Money = money;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int Total) {
        this.Total = Total;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * Id : 2
         * FrozenID : 2
         * Explain : 安装费
         * UserID : 15757964771
         * Money : 45
         * CreateTime : 2019-10-20T00:00:00
         * IsUse : Y
         * OrderID : 2000123123
         * page : 1
         * limit : 999
         * Version : 0
         */

        private int Id;
        private int FrozenID;
        private String ExplainMemo;
        private String UserID;
        private Double Money;
        private String CreateTime;
        private String IsUse;
        private String OrderID;
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

        public void setExplainMemo(String explainMemo) {
            ExplainMemo = explainMemo;
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

        public String getOrderID() {
            return OrderID;
        }

        public void setOrderID(String OrderID) {
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
}
