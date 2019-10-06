package com.zhenhaikj.factoryside.mvp.bean;

import java.io.Serializable;
import java.util.List;

public class Accessory implements Serializable {


    /**
     * Item1 : [{"Id":2,"FAccessoryID":2,"AccessoryName":"压缩机","AccessoryPrice":300,"ServicePrice":50,"Sate":"N","FProductTypeID":2,"IsUse":"Y","page":1,"limit":999,"Version":0}]
     * Item2 : 1
     */

    private String Item2;
    private List<Item1Bean> Item1;

    public String getItem2() {
        return Item2;
    }

    public void setItem2(String Item2) {
        this.Item2 = Item2;
    }

    public List<Item1Bean> getItem1() {
        return Item1;
    }

    public void setItem1(List<Item1Bean> Item1) {
        this.Item1 = Item1;
    }

    public static class Item1Bean {
        /**
         * Id : 2
         * FAccessoryID : 2
         * AccessoryName : 压缩机
         * AccessoryPrice : 300.0
         * ServicePrice : 50.0
         * Sate : N
         * FProductTypeID : 2
         * IsUse : Y
         * page : 1
         * limit : 999
         * Version : 0
         */

        private String Id;
        private String FAccessoryID;
        private String AccessoryName;
        private String AccessoryPrice;
        private String ServicePrice;
        private String Sate;
        private String FProductTypeID;
        private String IsUse;
        private String page;
        private String limit;
        private String Version;
        private int count=1;
        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getFAccessoryID() {
            return FAccessoryID;
        }

        public void setFAccessoryID(String FAccessoryID) {
            this.FAccessoryID = FAccessoryID;
        }

        public String getAccessoryName() {
            return AccessoryName;
        }

        public void setAccessoryName(String AccessoryName) {
            this.AccessoryName = AccessoryName;
        }

        public String getAccessoryPrice() {
            return AccessoryPrice;
        }

        public void setAccessoryPrice(String AccessoryPrice) {
            this.AccessoryPrice = AccessoryPrice;
        }

        public String getServicePrice() {
            return ServicePrice;
        }

        public void setServicePrice(String ServicePrice) {
            this.ServicePrice = ServicePrice;
        }

        public String getSate() {
            return Sate;
        }

        public void setSate(String Sate) {
            this.Sate = Sate;
        }

        public String getFProductTypeID() {
            return FProductTypeID;
        }

        public void setFProductTypeID(String FProductTypeID) {
            this.FProductTypeID = FProductTypeID;
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
}

