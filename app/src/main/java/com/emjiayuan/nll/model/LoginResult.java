package com.emjiayuan.nll.model;

import java.io.Serializable;

public class LoginResult implements Serializable {


    /**
     * id : 1
     * username : test
     * nickname : 托尔斯泰
     * password : 202cb962ac59075b964b07152d234b70
     * headimg : http://qiniu.emjiayuan.com/upload_api/ems/2018102617802172011
     * class_id : 1
     * buy_class_id : 0
     * viptime : 0
     * group_id : 0
     * discount : 100
     * sex : null
     * birthday : null
     * status : 10
     * login : 4
     * last_login_time : 1542935437
     * last_login_ip : 125.115.106.204
     * token : null
     * createtime : 1541492973
     * yue : 0
     * jifen : 0
     * isrobot : 0
     * isadmin : 0
     * register_platform : APP_IOS
     * showname : 托尔斯泰
     * info : {"id":"1","user_id":"1","nickname":"托尔斯泰","truename":"asdasdasd男","headimg":"http://res.emjiayuan.com/upload_ems/lib/app.png","phone":"13957356689","idcard_just":"http://res.emjiayuan.com/upload_ems/lib/app.png","idcard_back":"http://res.emjiayuan.com/upload_ems/lib/app.png","shop_name":"asdasdasd男","shop_address_detail":"back","shop_address_province":"1395735668","shop_address_city":"just","shop_address_area":"http","shop_sale":"0","sex":"1","birthday":"1990-01-01","update_time":"1542101601","shop_sale_str":"未知"}
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
    private String isrobot;
    private String isadmin;
    private String register_platform;
    private String showname;
    private UserInfo info;

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

    public String getIsrobot() {
        return isrobot;
    }

    public void setIsrobot(String isrobot) {
        this.isrobot = isrobot;
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

    public String getShowname() {
        return showname;
    }

    public void setShowname(String showname) {
        this.showname = showname;
    }

    public UserInfo getInfo() {
        return info;
    }

    public void setInfo(UserInfo info) {
        this.info = info;
    }
}
