package com.zhenhaikj.factoryside.mvp.bean;


import com.zhenhaikj.factoryside.mvp.utils.MyUtils;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {

    /**
     * id : 13084
     * order_no : c20180304100001232639
     * userid : 1
     * order_cate :
     * remark : 测试
     * address : 浙江省 宁波市 江东区 市民中心2
     * shengfen : 浙江省
     * address_province : null
     * address_name : 张先生
     * address_phone : 15012345678
     * pay_type : null
     * payment : 余额支付
     * pay_sign : null
     * pay_status : 1
     * pay_message : null
     * paytime : null
     * user_del : 0
     * status : 0
     * discountmoney : 0.00
     * totalmoney : 735.00
     * usemoney : 735.00
     * usepoints : 0
     * couponmoney : 0
     * couponid : 0
     * showmoney : 735
     * invoice : null
     * innercomment : null
     * comment : null
     * createtime : 1520177199
     * express_price : null
     * expressno : 000000
     * expresscom : qita
     * expressname : 其他
     * expressstatus : null
     * delflag : 0
     * extramoney : 0.00
     * ordertype : 3
     * isgiftorder : 0
     * ischongzhiorder : 0
     * source_platform : null
     * promotion_value :
     * order_status : 待收货
     * source_platform_str : APP
     * createdate : 2018-03-04 23:26:39
     * product_str :  伊穆家园 甄选 纯大料 整箱15斤 一箱6袋 价格：675.00 【 x 1件】 清真-伊穆家园牛肉面标准化汤料2.5斤试用装【中端型】 价格：60.00 【 x 1件】
     * product_list : [{"id":"6689","orderid":"13084","typeid":"274","buyprice":"675.00","buycount":"1","createtime":"1520177199","buytype":"0","productid":"274","name":"伊穆家园 甄选 纯大料 整箱15斤 一箱6袋 ","images":"http://qiniu.emjiayuan.com/products_headimg1524909666212","productprice":"675.00","product_str":"伊穆家园 甄选 纯大料 整箱15斤 一箱6袋 价格：675.00 【 x 1件】 "},{"id":"6690","orderid":"13084","typeid":"51","buyprice":"60.00","buycount":"1","createtime":"1520177199","buytype":"0","productid":"51","name":"清真-伊穆家园牛肉面标准化汤料2.5斤试用装【中端型】","images":"http://qiniu.emjiayuan.com/products_headimg1525257958042","productprice":"60.00","product_str":"清真-伊穆家园牛肉面标准化汤料2.5斤试用装【中端型】 价格：60.00 【 x 1件】 "}]
     * user_info : {"id":"1","username":"110","nickname":"13958243103","password":"5f93f983524def3dca464469d2cf9f3e","headimg":"http://qiniu.emjiayuan.com/upload_api/ems/2018060614681665753","class_id":"3","buy_class_id":"3","viptime":"1522406894","group_id":"3,4,5,6,8","discount":"96","sex":"0","birthday":"146747 E/------获取物流列表------1: 5200","status":"1","login":"786","last_login_time":"1529897281","last_login_ip":"125.115.189.40","token":"83296041e5ef1cecb8874b1878b04252","createtime":"1","yue":"94980.04","jifen":"4491","isnew":"1","isadmin":"1","register_platform":"","classname":"黄金","background":"http://qiniu.emjiayuan.com/class1516085096120","content":null,"showname":"139****3103"}
     */

    private String id;
    private String order_no;
    private String userid;
    private String order_cate;
    private String remark;
    private String address;
    private String shengfen;
    private String address_province;
    private String address_name;
    private String address_phone;
    private String pay_type;
    private String payment;
    private String pay_sign;
    private String pay_status;
    private String pay_message;
    private String paytime;
    private String user_del;
    private String status;
    private String discountmoney;
    private String totalmoney;
    private String usemoney;
    private String usepoints;
    private String couponmoney;
    private String couponid;
    private String showmoney;
    private String invoice;
    private String innercomment;
    private String comment;
    private String createtime;
    private String express_price;
    private String expressno;
    private String expresscom;
    private String expressname;
    private String expressstatus;
    private String delflag;
    private String extramoney;
    private String ordertype;
    private String isgiftorder;
    private String ischongzhiorder;
    private String source_platform;
    private String promotion_value;
    private String order_status;
    private String source_platform_str;
    private String createdate;
    private String product_str;
    private UserInfoBean user_info;
    private List<ProductListBean> product_list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getOrder_cate() {
        return order_cate;
    }

    public void setOrder_cate(String order_cate) {
        this.order_cate = order_cate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getShengfen() {
        return shengfen;
    }

    public void setShengfen(String shengfen) {
        this.shengfen = shengfen;
    }

    public String getAddress_province() {
        return address_province;
    }

    public void setAddress_province(String address_province) {
        this.address_province = address_province;
    }

    public String getAddress_name() {
        return address_name;
    }

    public void setAddress_name(String address_name) {
        this.address_name = address_name;
    }

    public String getAddress_phone() {
        return address_phone;
    }

    public void setAddress_phone(String address_phone) {
        this.address_phone = address_phone;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getPay_sign() {
        return pay_sign;
    }

    public void setPay_sign(String pay_sign) {
        this.pay_sign = pay_sign;
    }

    public String getPay_status() {
        return pay_status;
    }

    public void setPay_status(String pay_status) {
        this.pay_status = pay_status;
    }

    public String getPay_message() {
        return pay_message;
    }

    public void setPay_message(String pay_message) {
        this.pay_message = pay_message;
    }

    public String getPaytime() {
        return paytime;
    }

    public void setPaytime(String paytime) {
        this.paytime = paytime;
    }

    public String getUser_del() {
        return user_del;
    }

    public void setUser_del(String user_del) {
        this.user_del = user_del;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDiscountmoney() {
        return discountmoney;
    }

    public void setDiscountmoney(String discountmoney) {
        this.discountmoney = discountmoney;
    }

    public String getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(String totalmoney) {
        this.totalmoney = totalmoney;
    }

    public String getUsemoney() {
        return usemoney;
    }

    public void setUsemoney(String usemoney) {
        this.usemoney = usemoney;
    }

    public String getUsepoints() {
        return usepoints;
    }

    public void setUsepoints(String usepoints) {
        this.usepoints = usepoints;
    }

    public String getCouponmoney() {
        return couponmoney;
    }

    public void setCouponmoney(String couponmoney) {
        this.couponmoney = couponmoney;
    }

    public String getCouponid() {
        return couponid;
    }

    public void setCouponid(String couponid) {
        this.couponid = couponid;
    }

    public String getShowmoney() {
        return showmoney;
    }

    public void setShowmoney(String showmoney) {
        this.showmoney = showmoney;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getInnercomment() {
        return innercomment;
    }

    public void setInnercomment(String innercomment) {
        this.innercomment = innercomment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreatetime() {
        return MyUtils.stampToDate(createtime);
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getExpress_price() {
        return express_price;
    }

    public void setExpress_price(String express_price) {
        this.express_price = express_price;
    }

    public String getExpressno() {
        return expressno;
    }

    public void setExpressno(String expressno) {
        this.expressno = expressno;
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

    public String getExpressstatus() {
        return expressstatus;
    }

    public void setExpressstatus(String expressstatus) {
        this.expressstatus = expressstatus;
    }

    public String getDelflag() {
        return delflag;
    }

    public void setDelflag(String delflag) {
        this.delflag = delflag;
    }

    public String getExtramoney() {
        return extramoney;
    }

    public void setExtramoney(String extramoney) {
        this.extramoney = extramoney;
    }

    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }

    public String getIsgiftorder() {
        return isgiftorder;
    }

    public void setIsgiftorder(String isgiftorder) {
        this.isgiftorder = isgiftorder;
    }

    public String getIschongzhiorder() {
        return ischongzhiorder;
    }

    public void setIschongzhiorder(String ischongzhiorder) {
        this.ischongzhiorder = ischongzhiorder;
    }

    public String getSource_platform() {
        return source_platform;
    }

    public void setSource_platform(String source_platform) {
        this.source_platform = source_platform;
    }

    public String getPromotion_value() {
        return promotion_value;
    }

    public void setPromotion_value(String promotion_value) {
        this.promotion_value = promotion_value;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getSource_platform_str() {
        return source_platform_str;
    }

    public void setSource_platform_str(String source_platform_str) {
        this.source_platform_str = source_platform_str;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getProduct_str() {
        return product_str;
    }

    public void setProduct_str(String product_str) {
        this.product_str = product_str;
    }

    public UserInfoBean getUser_info() {
        return user_info;
    }

    public void setUser_info(UserInfoBean user_info) {
        this.user_info = user_info;
    }

    public List<ProductListBean> getProduct_list() {
        return product_list;
    }

    public void setProduct_list(List<ProductListBean> product_list) {
        this.product_list = product_list;
    }

    public static class UserInfoBean implements Serializable {
        /**
         * id : 1
         * username : 110
         * nickname : 13958243103
         * password : 5f93f983524def3dca464469d2cf9f3e
         * headimg : http://qiniu.emjiayuan.com/upload_api/ems/2018060614681665753
         * class_id : 3
         * buy_class_id : 3
         * viptime : 1522406894
         * group_id : 3,4,5,6,8
         * discount : 96
         * sex : 0
         * birthday : 146747 E/------获取物流列表------1: 5200
         * status : 1
         * login : 786
         * last_login_time : 1529897281
         * last_login_ip : 125.115.189.40
         * token : 83296041e5ef1cecb8874b1878b04252
         * createtime : 1
         * yue : 94980.04
         * jifen : 4491
         * isnew : 1
         * isadmin : 1
         * register_platform :
         * classname : 黄金
         * background : http://qiniu.emjiayuan.com/class1516085096120
         * content : null
         * showname : 139****3103
         */

        private String id;
        private String username;
        private String nickname;
        private String password;
        private String headimg;
        private String class_id;
        private String buy_class_id;
        private String viptime;
        private String group_id;
        private String discount;
        private String sex;
        private String birthday;
        private String status;
        private String login;
        private String last_login_time;
        private String last_login_ip;
        private String token;
        private String createtime;
        private String yue;
        private String jifen;
        private String isnew;
        private String isadmin;
        private String register_platform;
        private String classname;
        private String background;
        private String content;
        private String showname;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }

        public String getClass_id() {
            return class_id;
        }

        public void setClass_id(String class_id) {
            this.class_id = class_id;
        }

        public String getBuy_class_id() {
            return buy_class_id;
        }

        public void setBuy_class_id(String buy_class_id) {
            this.buy_class_id = buy_class_id;
        }

        public String getViptime() {
            return viptime;
        }

        public void setViptime(String viptime) {
            this.viptime = viptime;
        }

        public String getGroup_id() {
            return group_id;
        }

        public void setGroup_id(String group_id) {
            this.group_id = group_id;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getLast_login_time() {
            return last_login_time;
        }

        public void setLast_login_time(String last_login_time) {
            this.last_login_time = last_login_time;
        }

        public String getLast_login_ip() {
            return last_login_ip;
        }

        public void setLast_login_ip(String last_login_ip) {
            this.last_login_ip = last_login_ip;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getYue() {
            return yue;
        }

        public void setYue(String yue) {
            this.yue = yue;
        }

        public String getJifen() {
            return jifen;
        }

        public void setJifen(String jifen) {
            this.jifen = jifen;
        }

        public String getIsnew() {
            return isnew;
        }

        public void setIsnew(String isnew) {
            this.isnew = isnew;
        }

        public String getIsadmin() {
            return isadmin;
        }

        public void setIsadmin(String isadmin) {
            this.isadmin = isadmin;
        }

        public String getRegister_platform() {
            return register_platform;
        }

        public void setRegister_platform(String register_platform) {
            this.register_platform = register_platform;
        }

        public String getClassname() {
            return classname;
        }

        public void setClassname(String classname) {
            this.classname = classname;
        }

        public String getBackground() {
            return background;
        }

        public void setBackground(String background) {
            this.background = background;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getShowname() {
            return showname;
        }

        public void setShowname(String showname) {
            this.showname = showname;
        }
    }

    public static class ProductListBean implements Serializable {
        /**
         * id : 6689
         * orderid : 13084
         * typeid : 274
         * buyprice : 675.00
         * buycount : 1
         * createtime : 1520177199
         * buytype : 0
         * productid : 274
         * name : 伊穆家园 甄选 纯大料 整箱15斤 一箱6袋
         * images : http://qiniu.emjiayuan.com/products_headimg1524909666212
         * productprice : 675.00
         * product_str : 伊穆家园 甄选 纯大料 整箱15斤 一箱6袋 价格：675.00 【 x 1件】
         */

        private String id;
        private String orderid;
        private String typeid;
        private String buyprice;
        private String buycount;
        private String createtime;
        private String buytype;
        private String productid;
        private String name;
        private String images;
        private String productprice;
        private String product_str;
        private String jifen;
        private String giftjifen;

        public String getGiftjifen() {
            return giftjifen;
        }

        public void setGiftjifen(String giftjifen) {
            this.giftjifen = giftjifen;
        }

        public String getJifen() {
            return jifen;
        }

        public void setJifen(String jifen) {
            this.jifen = jifen;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getTypeid() {
            return typeid;
        }

        public void setTypeid(String typeid) {
            this.typeid = typeid;
        }

        public String getBuyprice() {
            return buyprice;
        }

        public void setBuyprice(String buyprice) {
            this.buyprice = buyprice;
        }

        public String getBuycount() {
            return buycount;
        }

        public void setBuycount(String buycount) {
            this.buycount = buycount;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getBuytype() {
            return buytype;
        }

        public void setBuytype(String buytype) {
            this.buytype = buytype;
        }

        public String getProductid() {
            return productid;
        }

        public void setProductid(String productid) {
            this.productid = productid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public String getProductprice() {
            return productprice;
        }

        public void setProductprice(String productprice) {
            this.productprice = productprice;
        }

        public String getProduct_str() {
            return product_str;
        }

        public void setProduct_str(String product_str) {
            this.product_str = product_str;
        }
    }
}
