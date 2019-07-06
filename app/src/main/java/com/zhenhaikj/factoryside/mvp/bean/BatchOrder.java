package com.zhenhaikj.factoryside.mvp.bean;

import java.io.Serializable;
import java.util.List;

public class BatchOrder implements Serializable {

    private List<OrderStrBean> OrderStr;

    public List<OrderStrBean> getOrderStr() {
        return OrderStr;
    }

    public void setOrderStr(List<OrderStrBean> OrderStr) {
        this.OrderStr = OrderStr;
    }

    public static class OrderStrBean {
        /**
         * UserId : taizhenjiang
         * TypeName : 维修
         * ExcelId : 1
         * ParentCategoryName : 冰洗类
         * CategoryName : 冰箱
         * ProductType : 单门 容积X≤100
         * Province : 浙江省
         * City : 杭州市
         * Area : 西湖区
         * District : 三墩镇
         * Address : 三墩街291号
         * UserName : TZJ
         * Phone : 17681886869
         * Memo : HUANLE
         * Num : 1
         * Guarantee : 是
         */

        private String UserId;
        private String TypeName;
        private String ExcelId;
        private String ParentCategoryName;
        private String CategoryName;
        private String ProductType;
        private String Province;
        private String City;
        private String Area;
        private String District;
        private String Address;
        private String UserName;
        private String Phone;
        private String Memo;
        private String Num;
        private String Guarantee;

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public String getTypeName() {
            return TypeName;
        }

        public void setTypeName(String TypeName) {
            this.TypeName = TypeName;
        }

        public String getExcelId() {
            return ExcelId;
        }

        public void setExcelId(String ExcelId) {
            this.ExcelId = ExcelId;
        }

        public String getParentCategoryName() {
            return ParentCategoryName;
        }

        public void setParentCategoryName(String ParentCategoryName) {
            this.ParentCategoryName = ParentCategoryName;
        }

        public String getCategoryName() {
            return CategoryName;
        }

        public void setCategoryName(String CategoryName) {
            this.CategoryName = CategoryName;
        }

        public String getProductType() {
            return ProductType;
        }

        public void setProductType(String ProductType) {
            this.ProductType = ProductType;
        }

        public String getProvince() {
            return Province;
        }

        public void setProvince(String Province) {
            this.Province = Province;
        }

        public String getCity() {
            return City;
        }

        public void setCity(String City) {
            this.City = City;
        }

        public String getArea() {
            return Area;
        }

        public void setArea(String Area) {
            this.Area = Area;
        }

        public String getDistrict() {
            return District;
        }

        public void setDistrict(String District) {
            this.District = District;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String Phone) {
            this.Phone = Phone;
        }

        public String getMemo() {
            return Memo;
        }

        public void setMemo(String Memo) {
            this.Memo = Memo;
        }

        public String getNum() {
            return Num;
        }

        public void setNum(String Num) {
            this.Num = Num;
        }

        public String getGuarantee() {
            return Guarantee;
        }

        public void setGuarantee(String Guarantee) {
            this.Guarantee = Guarantee;
        }
    }
}
