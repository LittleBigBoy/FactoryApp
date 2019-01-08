package com.zhenhaikj.factoryside.mvp.bean;

import com.google.gson.annotations.SerializedName;

public class WXpayInfo {


    /**
     * appid : wxf2f4119f13622198
     * partnerid : 1364972802
     * prepayid : wx0417332670521675e11f8fa84223803832
     * package : Sign=WXPay
     * noncestr : 2QgBzgGqGuuSVwMb
     * timestamp : 1530696806
     * sign : EEE883AFA33391F3DAE36C77E506A35C
     */

    private String appid;
    private String partnerid;
    private String prepayid;
    @SerializedName("package")
    private String packageX;
    private String noncestr;
    private String timestamp;
    private String sign;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
