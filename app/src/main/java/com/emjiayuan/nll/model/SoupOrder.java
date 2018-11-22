package com.emjiayuan.nll.model;


import com.emjiayuan.nll.utils.MyUtils;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SoupOrder implements Serializable {


    /**
     * id : 3662
     * order_no : TL2018071110835997765
     * userid : 8359
     * remark : 
     * order_name : 哦哦
     * val : 500
     * danwei : 斤
     * address : 浙江省宁波市镇海区东威大厦
     * address_province : 浙江省
     * address_name : 又来
     * address_phone : 18767773654
     * pay_type : null
     * payment : null
     * pay_sign : null
     * pay_status : 0
     * pay_message : null
     * paytime : null
     * user_del : 0
     * status : 0
     * leixing : 1
     * usemoney : 17400.00
     * usepoStrings : null
     * couponmoney : null
     * couponid : null
     * showmoney : 174.00
     * costmoney : 6.00
     * totalmoney : 180.00
     * invoice : null
     * innercomment : null
     * comment : null
     * createtime : 1531275959
     * expressprice : null
     * expressno : null
     * expresscom : null
     * expressname : null
     * expressresult : null
     * expressstatus : null
     * delflag : 0
     * extramoney : 0.00
     * ordertype : 1
     * source_platform : APP_ANDROID
     * discount : 10
     * discount_price : 0.00
     * order_status : 待付款
     * source_platform_str : 安卓
     * createdate : 2018-07-11 10:25:59
     * product_str :  草果 价格：8.64 【 重量 500克】 花椒 价格：15.60 【 重量 500克】 胡椒 价格：4.92 【 重量 500克】 八角 价格：2.64 【 重量 500克】 茴香 价格：1.32 【 重量 500克】 姜块 价格：1.68 【 重量 500克】 
     * product_list : [{"id":"35040","orderid":"3662","typeid":"3","buyprice":"8.64","buycount":"500","createtime":"1531275959","buytype":"0","soup_product_id":"3","name":"草果","images":"http://qiniu.emjiayuan.com/soup1514960385315","class":"优质","price":"8.64","area":"","top":"51","status":"1","delflag":"0","weight_jin":1,"buy_everyjin_price":"43.20","now_everyjin_price":"43.20","buy_total_price":"43.20","product_str":"草果 价格：8.64 【 重量 500克】 "},{"id":"35041","orderid":"3662","typeid":"1","buyprice":"15.60","buycount":"500","createtime":"1531275959","buytype":"0","soup_product_id":"1","name":"花椒","images":"http://qiniu.emjiayuan.com/soup1514960368242","class":"优质","price":"15.6","area":"","top":"50","status":"1","delflag":"0","weight_jin":1,"buy_everyjin_price":"78.00","now_everyjin_price":"78.00","buy_total_price":"78.00","product_str":"花椒 价格：15.60 【 重量 500克】 "},{"id":"35042","orderid":"3662","typeid ":"2","buyprice":"4.92","buycount":"500","createtime":"1531275959","buytype":"0","soup_product_id":"2","name":"胡椒","images":"http://qiniu.emjiayuan.com/soup1514960376452","class":"优质","price":"4.92","area":"","top":"49","status":"1","delflag":"0","weight_jin":1,"buy_everyjin_price":"24.60","now_everyjin_price":"24.60","buy_total_price":"24.60","product_str":"胡椒 价格：4.92 【 重量 500克】 "},{"id":"35043","orderid":"3662","typeid":"16","buyprice":"2.64","buycount":"500","createtime":"1531275959","buytype":"0","soup_product_id":"16","name":"八角","images":"http://qiniu.emjiayuan.com/soup1514960778710","class":"优质","price":"2.64","area":"","top":"48","status":"1","delflag":"0","weight_jin":1,"buy_everyjin_price":"13.20","now_everyjin_price":"13.20","buy_total_price":"13.20","product_str":"八角 价格：2.64 【 重量 500克】 "},{"id":"35044","orderid":"3662","typeid":"27","buyprice":"1.32","buycount":"500","createtime":"1531275959","buytype":"0","soup_product_id":"27","name":"茴香","images":"http://qiniu.emjiayuan.com/soup1514961007155","class":"优质","price":"1.32","area":"","top":"47","status":"1","delflag":"0","weight_jin":1,"buy_everyjin_price":"6.60","now_everyjin_price":"6.60","buy_total_price":"6.60","product_str":"茴香 价格：1.32 【 重量 500克】 "},{"id":"35045","orderid":"3662","typeid":"22","buyprice":"1.68","buycount":"500","createtime":"1531275959","buytype":"0","soup_product_id":"22","name":"姜块","images":"http://qiniu.emjiayuan.com/soup1514960856584","class":"优质","price":"1.68","area":"","top":"46","status":"1","delflag":"0","weight_jin":1,"buy_everyjin_price":"8.40","now_everyjin_price":"8.40","buy_total_price":"8.40","product_str":"姜块 价格：1.68 【 重量 500克】 "}]
     * user_info : {"id":"8359","username":"18767773654","nickname":"又来了","password":"202cb962ac59075b964b07152d234b70","headimg":"http://qiniu.emjiayuan.com/upload_api/ems/2018070615965894260","class_id":"1","buy_class_id":"2","viptime":"1562407946","group_id":"0","discount":"100","sex":null,"birthday":null,"status":"1","log E/------获取汤料订单列表结果------: in":"159","last_login_time":"1531277622","last_login_ip":"122.247.147.206","token":null,"createtime":"1526636788","yue":"97923.3","jifen":"99723","isnew":"1","isadmin":"0","register_platform":"","classname":"Vip普通会员","background":"http://qiniu.emjiayuan.com/class1516084991946","content":"享受普通会员优惠","showname":"又来了"}
     */

    private String id;
    private String order_no;
    private String userid;
    private String remark;
    private String order_name;
    private String val;
    private String danwei;
    private String address;
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
    private String leixing;
    private String usemoney;
    private String usepoStrings;
    private String couponmoney;
    private String couponid;
    private String showmoney;
    private String costmoney;
    private String totalmoney;
    private String invoice;
    private String innercomment;
    private String comment;
    private String createtime;
    private String expressprice;
    private String expressno;
    private String expresscom;
    private String expressname;
    private String expressresult;
    private String expressstatus;
    private String delflag;
    private String extramoney;
    private String ordertype;
    private String source_platform;
    private String discount;
    private String discount_price;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOrder_name() {
        return order_name;
    }

    public void setOrder_name(String order_name) {
        this.order_name = order_name;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getDanwei() {
        return danwei;
    }

    public void setDanwei(String danwei) {
        this.danwei = danwei;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getLeixing() {
        if("0".equals(leixing)){
            return "其他";
        }else if("1".equals(leixing)){
            return "汤料";
        }else if("2".equals(leixing)){
            return "面料";
        }
        return leixing;
    }

    public void setLeixing(String leixing) {
        this.leixing = leixing;
    }

    public String getUsemoney() {
        return usemoney;
    }

    public void setUsemoney(String usemoney) {
        this.usemoney = usemoney;
    }

    public String getUsepoStrings() {
        return usepoStrings;
    }

    public void setUsepoStrings(String usepoStrings) {
        this.usepoStrings = usepoStrings;
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

    public String getExpressprice() {
        return expressprice;
    }

    public void setExpressprice(String expressprice) {
        this.expressprice = expressprice;
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

    public String getExpressresult() {
        return expressresult;
    }

    public void setExpressresult(String expressresult) {
        this.expressresult = expressresult;
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

    public String getSource_platform() {
        return source_platform;
    }

    public void setSource_platform(String source_platform) {
        this.source_platform = source_platform;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(String discount_price) {
        this.discount_price = discount_price;
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
         * id : 8359
         * username : 18767773654
         * nickname : 又来了
         * password : 202cb962ac59075b964b07152d234b70
         * headimg : http://qiniu.emjiayuan.com/upload_api/ems/2018070615965894260
         * class_id : 1
         * buy_class_id : 2
         * viptime : 1562407946
         * group_id : 0
         * discount : 100
         * sex : null
         * birthday : null
         * status : 1
         * log E/------获取汤料订单列表结果------: in : 159
         * last_login_time : 1531277622
         * last_login_ip : 122.247.147.206
         * token : null
         * createtime : 1526636788
         * yue : 97923.3
         * jifen : 99723
         * isnew : 1
         * isadmin : 0
         * register_platform : 
         * classname : Vip普通会员
         * background : http://qiniu.emjiayuan.com/class1516084991946
         * content : 享受普通会员优惠
         * showname : 又来了
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
        @SerializedName("log E/------获取汤料订单列表结果------: in")
        private String _$LogEIn210; // FIXME check this code
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

        public String get_$LogEIn210() {
            return _$LogEIn210;
        }

        public void set_$LogEIn210(String _$LogEIn210) {
            this._$LogEIn210 = _$LogEIn210;
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
         * id : 35040
         * orderid : 3662
         * typeid : 3
         * buyprice : 8.64
         * buycount : 500
         * createtime : 1531275959
         * buytype : 0
         * soup_product_id : 3
         * name : 草果
         * images : http://qiniu.emjiayuan.com/soup1514960385315
         * class : 优质
         * price : 8.64
         * area : 
         * top : 51
         * status : 1
         * delflag : 0
         * weight_jin : 1
         * buy_everyjin_price : 43.20
         * now_everyjin_price : 43.20
         * buy_total_price : 43.20
         * product_str : 草果 价格：8.64 【 重量 500克】 
         * typeid  : 2
         */

        private String id;
        private String orderid;
        private String typeid;
        private String buyprice;
        private String buycount;
        private String createtime;
        private String buytype;
        private String soup_product_id;
        private String name;
        private String images;
        @SerializedName("class")
        private String classX;
        private String price;
        private String area;
        private String top;
        private String status;
        private String delflag;
        private String weight_jin;
        private String buy_everyjin_price;
        private String now_everyjin_price;
        private String buy_total_price;
        private String product_str;
        private String typeidx;

        public String getTypeidx() {
            return typeidx;
        }

        public void setTypeidx(String typeidx) {
            this.typeidx = typeidx;
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

        public String getSoup_product_id() {
            return soup_product_id;
        }

        public void setSoup_product_id(String soup_product_id) {
            this.soup_product_id = soup_product_id;
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

        public String getClassX() {
            return classX;
        }

        public void setClassX(String classX) {
            this.classX = classX;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getTop() {
            return top;
        }

        public void setTop(String top) {
            this.top = top;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDelflag() {
            return delflag;
        }

        public void setDelflag(String delflag) {
            this.delflag = delflag;
        }

        public String getWeight_jin() {
            return weight_jin;
        }

        public void setWeight_jin(String weight_jin) {
            this.weight_jin = weight_jin;
        }

        public String getBuy_everyjin_price() {
            return buy_everyjin_price;
        }

        public void setBuy_everyjin_price(String buy_everyjin_price) {
            this.buy_everyjin_price = buy_everyjin_price;
        }

        public String getNow_everyjin_price() {
            return now_everyjin_price;
        }

        public void setNow_everyjin_price(String now_everyjin_price) {
            this.now_everyjin_price = now_everyjin_price;
        }

        public String getBuy_total_price() {
            return buy_total_price;
        }

        public void setBuy_total_price(String buy_total_price) {
            this.buy_total_price = buy_total_price;
        }

        public String getProduct_str() {
            return product_str;
        }

        public void setProduct_str(String product_str) {
            this.product_str = product_str;
        }

        public String getTypeid() {
            return typeid;
        }

        public void setTypeid(String typeid) {
            this.typeid = typeid;
        }
    }
}
