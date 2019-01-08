package com.zhenhaikj.factoryside.mvp.bean;

import java.io.Serializable;

public class UserInfo implements Serializable {

    /**
     * id : 2
     * user_id : 10000
     * nickname : null
     * truename : 又来
     * headimg : http://qiniu.emjiayuan.com/upload_api/ems/2018112016735280663
     * phone : 18767773654
     * idcard_just : http://qiniu.emjiayuan.com/upload_api/ems/2018112010153439456
     * idcard_back : http://qiniu.emjiayuan.com/upload_api/ems/2018112010877562608
     * shop_name : 兰州拉面
     * shop_address_detail : 东威大厦
     * shop_address_province : 浙江省
     * shop_address_city : 宁波市
     * shop_address_area : 镇海区
     * shop_sale : 3
     * sex : 0
     * birthday : 0000-00-00
     * update_time : 1542696022
     */

    private String id;
    private String user_id;
    private String nickname;
    private String truename;
    private String headimg;
    private String phone;
    private String idcard_just;
    private String idcard_back;
    private String shop_name;
    private String shop_address_detail;
    private String shop_address_province;
    private String shop_address_city;
    private String shop_address_area;
    private String shop_sale;
    private String shop_sale_str;
    private String sex;
    private String birthday;
    private String update_time;

    public String getShop_sale_str() {
        return shop_sale_str;
    }

    public void setShop_sale_str(String shop_sale_str) {
        this.shop_sale_str = shop_sale_str;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdcard_just() {
        return idcard_just;
    }

    public void setIdcard_just(String idcard_just) {
        this.idcard_just = idcard_just;
    }

    public String getIdcard_back() {
        return idcard_back;
    }

    public void setIdcard_back(String idcard_back) {
        this.idcard_back = idcard_back;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_address_detail() {
        return shop_address_detail;
    }

    public void setShop_address_detail(String shop_address_detail) {
        this.shop_address_detail = shop_address_detail;
    }

    public String getShop_address_province() {
        return shop_address_province;
    }

    public void setShop_address_province(String shop_address_province) {
        this.shop_address_province = shop_address_province;
    }

    public String getShop_address_city() {
        return shop_address_city;
    }

    public void setShop_address_city(String shop_address_city) {
        this.shop_address_city = shop_address_city;
    }

    public String getShop_address_area() {
        return shop_address_area;
    }

    public void setShop_address_area(String shop_address_area) {
        this.shop_address_area = shop_address_area;
    }

    public String getShop_sale() {
        return shop_sale;
    }

    public void setShop_sale(String shop_sale) {
        this.shop_sale = shop_sale;
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

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }
}
