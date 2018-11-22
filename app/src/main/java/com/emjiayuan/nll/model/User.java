package com.emjiayuan.nll.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User {

    /**
     * id : 3
     * username : 119
     * nickname : destijjjny
     * password : 5f93f983524def3dca464469d2cf9f3e
     * headimg :
     * class_id : 1
     * buy_class_id : 0
     * viptime : 0
     * group_id : 0
     * discount : 100
     * sex : 1
     * birthday : 1307808000
     * status : 1
     * login : 546
     * last_login_time : 1529561537
     * last_login_ip : 122.247.145.232
     * token : f33146e6c75f2ae36b3da131a3c3cccd
     * createtime : 1
     * yue : 0
     * jifen : 1500
     * isnew : 1
     * isadmin : 0
     * register_platform :
     * classname : Vip普通会员
     * background : http://qiniu.emjiayuan.com/class1516084991946
     * content : 享受普通会员优惠
     * showname : destijjjny
     * couponcount : 0
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
    private String couponcount;

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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long lt = new Long(viptime);
        if (viptime.length()==13){
            Date date = new Date(lt);
            return simpleDateFormat.format(date);
        }else{
            Date date = new Date(lt*1000);
            return simpleDateFormat.format(date);
        }
//        return MyUtils.stampToDate(viptime);
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

    public String getCouponcount() {
        return couponcount;
    }

    public void setCouponcount(String couponcount) {
        this.couponcount = couponcount;
    }
}
