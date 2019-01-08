package com.zhenhaikj.factoryside.mvp.bean;

import com.zhenhaikj.factoryside.mvp.utils.MyUtils;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Soup implements Serializable {

    /**
     * id : 9
     * name : 桂籽
     * images : http://qiniu.emjiayuan.com/soup1514960652895
     * class : 优质
     * price : 5.34
     * area :
     * top : 10
     * status : 1
     * delflag : 0
     */

    private String id;
    private String name;
    private String images;
    @SerializedName("class")
    private String classX;
    private String price;
    private String area;
    private String top;
    private String status;
    private String delflag;
    private String num="0";
    private boolean check=false;

    public String getNum() {

        DecimalFormat df   = new DecimalFormat("######0.00");
        return MyUtils.subZeroAndDot(df.format(Double.parseDouble(num)));
    }

    public void setNum(String num) {
        this.num = num;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
