package com.emjiayuan.nll.model;

import java.util.List;

public class SoupOrderConfirm {

    /**
     * discount : 8
     * discountprice : 80.00
     * unitval : 1
     * unitstr : 克
     * totalweight : 5
     * productprice : 400.00
     * costmoney : 5.00
     * totalmoney : 325.00
     * products : [{"name":"花椒","level":"优质","price":"16","weight":"2500","unit":"克","saleunit":2500}]
     */

    private String discount;
    private String discountprice;
    private String unitval;
    private String unitstr;
    private String totalweight;
    private String productprice;
    private String costmoney;
    private String totalmoney;
    private List<ProductsBean> products;

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDiscountprice() {
        return discountprice;
    }

    public void setDiscountprice(String discountprice) {
        this.discountprice = discountprice;
    }

    public String getUnitval() {
        return unitval;
    }

    public void setUnitval(String unitval) {
        this.unitval = unitval;
    }

    public String getUnitstr() {
        return unitstr;
    }

    public void setUnitstr(String unitstr) {
        this.unitstr = unitstr;
    }

    public String getTotalweight() {
        return totalweight;
    }

    public void setTotalweight(String totalweight) {
        this.totalweight = totalweight;
    }

    public String getProductprice() {
        return productprice;
    }

    public void setProductprice(String productprice) {
        this.productprice = productprice;
    }

    public String getCostmoney() {
        return costmoney;
    }

    public void setCostmoney(String costmoney) {
        this.costmoney = costmoney;
    }

    public String getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(String totalmoney) {
        this.totalmoney = totalmoney;
    }

    public List<ProductsBean> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsBean> products) {
        this.products = products;
    }

    public static class ProductsBean {
        /**
         * name : 花椒
         * level : 优质
         * price : 16
         * weight : 2500
         * unit : 克
         * saleunit : 2500
         */

        private String name;
        private String level;
        private String price;
        private String weight;
        private String unit;
        private String saleunit;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getSaleunit() {
            return saleunit;
        }

        public void setSaleunit(String saleunit) {
            this.saleunit = saleunit;
        }
    }
}
