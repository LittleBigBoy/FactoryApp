package com.zhenhaikj.factoryside.mvp.bean;

import com.zhenhaikj.factoryside.mvp.activity.Acc;

import java.io.Serializable;
import java.util.List;

public class AddOrderParams implements Serializable {
    private String phone;
    private String name;
    private String city;
    private String addstr;
    private String servicetype;
    private String guaranteetype;
    private String subCategoryID;
    private String specifications;
    private String factoryBrandName;
    private String prodModel;
    private List<Acc> partsVal;
    private String ExpressNo;
    private String parts;
    private String backAddress;
    private String postpaytype;
    private String backParts;
    private String bak;
    private String Num;
    private String ContinueIssuing;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddstr() {
        return addstr;
    }

    public void setAddstr(String addstr) {
        this.addstr = addstr;
    }

    public String getServicetype() {
        return servicetype;
    }

    public void setServicetype(String servicetype) {
        this.servicetype = servicetype;
    }

    public String getGuaranteetype() {
        return guaranteetype;
    }

    public void setGuaranteetype(String guaranteetype) {
        this.guaranteetype = guaranteetype;
    }

    public String getSubCategoryID() {
        return subCategoryID;
    }

    public void setSubCategoryID(String subCategoryID) {
        this.subCategoryID = subCategoryID;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getFactoryBrandName() {
        return factoryBrandName;
    }

    public void setFactoryBrandName(String factoryBrandName) {
        this.factoryBrandName = factoryBrandName;
    }

    public String getProdModel() {
        return prodModel;
    }

    public void setProdModel(String prodModel) {
        this.prodModel = prodModel;
    }

    public List<Acc> getPartsVal() {
        return partsVal;
    }

    public void setPartsVal(List<Acc> partsVal) {
        this.partsVal = partsVal;
    }

    public String getExpressNo() {
        return ExpressNo;
    }

    public void setExpressNo(String expressNo) {
        ExpressNo = expressNo;
    }

    public String getParts() {
        return parts;
    }

    public void setParts(String parts) {
        this.parts = parts;
    }

    public String getBackAddress() {
        return backAddress;
    }

    public void setBackAddress(String backAddress) {
        this.backAddress = backAddress;
    }

    public String getPostpaytype() {
        return postpaytype;
    }

    public void setPostpaytype(String postpaytype) {
        this.postpaytype = postpaytype;
    }

    public String getBackParts() {
        return backParts;
    }

    public void setBackParts(String backParts) {
        this.backParts = backParts;
    }

    public String getBak() {
        return bak;
    }

    public void setBak(String bak) {
        this.bak = bak;
    }

    public String getNum() {
        return Num;
    }

    public void setNum(String num) {
        Num = num;
    }

    public String getContinueIssuing() {
        return ContinueIssuing;
    }

    public void setContinueIssuing(String continueIssuing) {
        ContinueIssuing = continueIssuing;
    }
}
