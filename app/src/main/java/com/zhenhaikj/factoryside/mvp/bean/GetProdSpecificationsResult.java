package com.zhenhaikj.factoryside.mvp.bean;

import java.io.Serializable;
import java.util.List;

public class GetProdSpecificationsResult implements Serializable {

    /**
     * StatusCode : 200
     * Info : 请求(或处理)成功
     * Count : 0
     * Data : [{"Id":0,"FSpecificationsID":0,"UserID":null,"CompanyName":null,"ParentID":0,"SpecificationsID":263,"FCategoryName":"卧式冷柜≤300升","Guarantee":null,"PriceType":0,"TypeName":null,"StandardPrice":0,"FactoryPrice":0,"CreateDate":null,"Operator":null,"PlatformFee":0,"IsUse":"Y","FactoryAccessorys":null,"FactoryServices":null,"page":0,"limit":0,"Version":0}]
     */

    private int StatusCode;
    private String Info;
    private int Count;
    private List<DataBean> Data;

    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int StatusCode) {
        this.StatusCode = StatusCode;
    }

    public String getInfo() {
        return Info;
    }

    public void setInfo(String Info) {
        this.Info = Info;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int Count) {
        this.Count = Count;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * Id : 0
         * FSpecificationsID : 0
         * UserID : null
         * CompanyName : null
         * ParentID : 0
         * SpecificationsID : 263
         * FCategoryName : 卧式冷柜≤300升
         * Guarantee : null
         * PriceType : 0
         * TypeName : null
         * StandardPrice : 0.0
         * FactoryPrice : 0.0
         * CreateDate : null
         * Operator : null
         * PlatformFee : 0.0
         * IsUse : Y
         * FactoryAccessorys : null
         * FactoryServices : null
         * page : 0
         * limit : 0
         * Version : 0
         */

        private int Id;
        private int FSpecificationsID;
        private Object UserID;
        private Object CompanyName;
        private int ParentID;
        private int SpecificationsID;
        private String FCategoryName;
        private Object Guarantee;
        private int PriceType;
        private Object TypeName;
        private double StandardPrice;
        private double FactoryPrice;
        private Object CreateDate;
        private Object Operator;
        private double PlatformFee;
        private String IsUse;
        private Object FactoryAccessorys;
        private Object FactoryServices;
        private int page;
        private int limit;
        private int Version;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public int getFSpecificationsID() {
            return FSpecificationsID;
        }

        public void setFSpecificationsID(int FSpecificationsID) {
            this.FSpecificationsID = FSpecificationsID;
        }

        public Object getUserID() {
            return UserID;
        }

        public void setUserID(Object UserID) {
            this.UserID = UserID;
        }

        public Object getCompanyName() {
            return CompanyName;
        }

        public void setCompanyName(Object CompanyName) {
            this.CompanyName = CompanyName;
        }

        public int getParentID() {
            return ParentID;
        }

        public void setParentID(int ParentID) {
            this.ParentID = ParentID;
        }

        public int getSpecificationsID() {
            return SpecificationsID;
        }

        public void setSpecificationsID(int SpecificationsID) {
            this.SpecificationsID = SpecificationsID;
        }

        public String getFCategoryName() {
            return FCategoryName;
        }

        public void setFCategoryName(String FCategoryName) {
            this.FCategoryName = FCategoryName;
        }

        public Object getGuarantee() {
            return Guarantee;
        }

        public void setGuarantee(Object Guarantee) {
            this.Guarantee = Guarantee;
        }

        public int getPriceType() {
            return PriceType;
        }

        public void setPriceType(int PriceType) {
            this.PriceType = PriceType;
        }

        public Object getTypeName() {
            return TypeName;
        }

        public void setTypeName(Object TypeName) {
            this.TypeName = TypeName;
        }

        public double getStandardPrice() {
            return StandardPrice;
        }

        public void setStandardPrice(double StandardPrice) {
            this.StandardPrice = StandardPrice;
        }

        public double getFactoryPrice() {
            return FactoryPrice;
        }

        public void setFactoryPrice(double FactoryPrice) {
            this.FactoryPrice = FactoryPrice;
        }

        public Object getCreateDate() {
            return CreateDate;
        }

        public void setCreateDate(Object CreateDate) {
            this.CreateDate = CreateDate;
        }

        public Object getOperator() {
            return Operator;
        }

        public void setOperator(Object Operator) {
            this.Operator = Operator;
        }

        public double getPlatformFee() {
            return PlatformFee;
        }

        public void setPlatformFee(double PlatformFee) {
            this.PlatformFee = PlatformFee;
        }

        public String getIsUse() {
            return IsUse;
        }

        public void setIsUse(String IsUse) {
            this.IsUse = IsUse;
        }

        public Object getFactoryAccessorys() {
            return FactoryAccessorys;
        }

        public void setFactoryAccessorys(Object FactoryAccessorys) {
            this.FactoryAccessorys = FactoryAccessorys;
        }

        public Object getFactoryServices() {
            return FactoryServices;
        }

        public void setFactoryServices(Object FactoryServices) {
            this.FactoryServices = FactoryServices;
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
