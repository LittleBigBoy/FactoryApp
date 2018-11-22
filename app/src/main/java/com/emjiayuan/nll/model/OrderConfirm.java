package com.emjiayuan.nll.model;

import java.io.Serializable;
import java.util.List;

public class OrderConfirm implements Serializable {


    /**
     * ordercate : PT
     * vipprice : 0.33
     * vipdiscount : 99
     * couponprice : 0
     * productprice : 33
     * totalStringegral : 1
     * expressprice : 0
     * discountprice : 32.67
     * payprice : 32.67
     * expresscom : zhongtong
     * expressname : 中通快递
     * promotiontype :
     * promotionvalue : 0
     * products : [{"id":308,"buyprice":"33.00","name":"清真-青海纯洋芋粉 白色宽粉条 【7斤一捆】","price":"33.00","weight":"0.00","limitnum":"0","buynum":1,"freeshipping":"0","images":"http://qiniu.emjiayuan.com/products_headimg1525256233832","jifen":"1"}]
     * usercoupons : [{"id":"1257","userid":"1","couponid":"39","createtime":"1529889751","usestatus":"0","status":"1","title":"测试优惠券","subtitle":"测试优惠券22223333","maxmoney":"110","savemoney":"10","starttime":"2018-06-23 00:00:00","finishtime":"2018-06-30 23:59:59","starttime2":"1529683200","finishtime2":"1530374399","type":"2","usetime":"1","isuse":0},{"id":"987","userid":"1","couponid":"37","createtime":"1516072317","usestatus":"0","status":"1","title":"1.16优惠卷测试","subtitle":"测试促销优惠6555","maxmoney":"120","savemoney":"40","starttime":"2018-01-01 01:01:00","finishtime":"2019-01-01 01:01:00","starttime2":"1514739660","finishtime2":"1546275660","type":"2","usetime":"1","isuse":0},{"id":"966","userid":"1","couponid":"36","createtime":"1515571037","usestatus":"0","status":"1","title":"1月10","subtitle":"促销优惠卷","maxmoney":"150","savemoney":"50","starttime":"2018-01-01 00:00:00","finishtime":"2019-01-02 23:59:59","starttime2":"1514736000","finishtime2":"1546444799","type":"2","usetime":"1","isuse":0}]
     */

    private String ordercate;
    private String vipprice;
    private String vipdiscount;
    private String couponprice;
    private String productprice;
    private String totalintegral;
    private String expressprice;
    private String discountprice;
    private String payprice;
    private String expresscom;
    private String expressname;
    private String promotiontype;
    private String promotionvalue;
    private List<ProductsBean> products;
    private List<UsercouponsBean> usercoupons;

    public String getOrdercate() {
        return ordercate;
    }

    public void setOrdercate(String ordercate) {
        this.ordercate = ordercate;
    }

    public String getVipprice() {
        return vipprice;
    }

    public void setVipprice(String vipprice) {
        this.vipprice = vipprice;
    }

    public String getVipdiscount() {
        return vipdiscount;
    }

    public void setVipdiscount(String vipdiscount) {
        this.vipdiscount = vipdiscount;
    }

    public String getCouponprice() {
        return couponprice;
    }

    public void setCouponprice(String couponprice) {
        this.couponprice = couponprice;
    }

    public String getProductprice() {
        return productprice;
    }

    public void setProductprice(String productprice) {
        this.productprice = productprice;
    }

    public String getTotalintegral() {
        return totalintegral;
    }

    public void setTotalintegral(String totalintegral) {
        this.totalintegral = totalintegral;
    }

    public String getExpressprice() {
        return expressprice;
    }

    public void setExpressprice(String expressprice) {
        this.expressprice = expressprice;
    }

    public String getDiscountprice() {
        return discountprice;
    }

    public void setDiscountprice(String discountprice) {
        this.discountprice = discountprice;
    }

    public String getPayprice() {
        return payprice;
    }

    public void setPayprice(String payprice) {
        this.payprice = payprice;
    }

    public String getExpresscom() {
        return expresscom;
    }

    public void setExpresscom(String expresscom) {
        this.expresscom = expresscom;
    }

    public String getExpressname() {
        return expressname;
    }

    public void setExpressname(String expressname) {
        this.expressname = expressname;
    }

    public String getPromotiontype() {
        return promotiontype;
    }

    public void setPromotiontype(String promotiontype) {
        this.promotiontype = promotiontype;
    }

    public String getPromotionvalue() {
        return promotionvalue;
    }

    public void setPromotionvalue(String promotionvalue) {
        this.promotionvalue = promotionvalue;
    }

    public List<ProductsBean> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsBean> products) {
        this.products = products;
    }

    public List<UsercouponsBean> getUsercoupons() {
        return usercoupons;
    }

    public void setUsercoupons(List<UsercouponsBean> usercoupons) {
        this.usercoupons = usercoupons;
    }

    public static class ProductsBean implements Serializable {
        /**
         * id : 308
         * buyprice : 33.00
         * name : 清真-青海纯洋芋粉 白色宽粉条 【7斤一捆】
         * price : 33.00
         * weight : 0.00
         * limitnum : 0
         * buynum : 1
         * freeshipping : 0
         * images : http://qiniu.emjiayuan.com/products_headimg1525256233832
         * jifen : 1
         */

        private String id;
        private String buyprice;
        private String name;
        private String price;
        private String weight;
        private String limitnum;
        private String buynum;
        private String freeshipping;
        private String images;
        private String jifen;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBuyprice() {
            return buyprice;
        }

        public void setBuyprice(String buyprice) {
            this.buyprice = buyprice;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public String getLimitnum() {
            return limitnum;
        }

        public void setLimitnum(String limitnum) {
            this.limitnum = limitnum;
        }

        public String getBuynum() {
            return buynum;
        }

        public void setBuynum(String buynum) {
            this.buynum = buynum;
        }

        public String getFreeshipping() {
            return freeshipping;
        }

        public void setFreeshipping(String freeshipping) {
            this.freeshipping = freeshipping;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public String getJifen() {
            return jifen;
        }

        public void setJifen(String jifen) {
            this.jifen = jifen;
        }
    }

    public static class UsercouponsBean implements Serializable {
        /**
         * id : 1257
         * userid : 1
         * couponid : 39
         * createtime : 1529889751
         * usestatus : 0
         * status : 1
         * title : 测试优惠券
         * subtitle : 测试优惠券22223333
         * maxmoney : 110
         * savemoney : 10
         * starttime : 2018-06-23 00:00:00
         * finishtime : 2018-06-30 23:59:59
         * starttime2 : 1529683200
         * finishtime2 : 1530374399
         * type : 2
         * usetime : 1
         * isuse : 0
         */

        private String id;
        private String userid;
        private String couponid;
        private String createtime;
        private String usestatus;
        private String status;
        private String title;
        private String subtitle;
        private String maxmoney;
        private String savemoney;
        private String starttime;
        private String finishtime;
        private String starttime2;
        private String finishtime2;
        private String type;
        private String usetime;
        private String isuse;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getCouponid() {
            return couponid;
        }

        public void setCouponid(String couponid) {
            this.couponid = couponid;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getUsestatus() {
            return usestatus;
        }

        public void setUsestatus(String usestatus) {
            this.usestatus = usestatus;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String getMaxmoney() {
            return maxmoney;
        }

        public void setMaxmoney(String maxmoney) {
            this.maxmoney = maxmoney;
        }

        public String getSavemoney() {
            return savemoney;
        }

        public void setSavemoney(String savemoney) {
            this.savemoney = savemoney;
        }

        public String getStarttime() {
            return starttime.substring(0,starttime.indexOf(" "));
        }

        public void setStarttime(String starttime) {
            this.starttime = starttime;
        }

        public String getFinishtime() {
            return finishtime.substring(0,finishtime.indexOf(" "));
        }

        public void setFinishtime(String finishtime) {
            this.finishtime = finishtime;
        }

        public String getStarttime2() {
            return starttime2;
        }

        public void setStarttime2(String starttime2) {
            this.starttime2 = starttime2;
        }

        public String getFinishtime2() {
            return finishtime2;
        }

        public void setFinishtime2(String finishtime2) {
            this.finishtime2 = finishtime2;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUsetime() {
            return usetime;
        }

        public void setUsetime(String usetime) {
            this.usetime = usetime;
        }

        public String getIsuse() {
            return isuse;
        }

        public void setIsuse(String isuse) {
            this.isuse = isuse;
        }
    }
}
