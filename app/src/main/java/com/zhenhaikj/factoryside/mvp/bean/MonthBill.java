package com.zhenhaikj.factoryside.mvp.bean;

import java.io.Serializable;
import java.util.List;

public class MonthBill implements Serializable {

    /*
    * "code":"0",
    * "msg":"success",
    * "count":"5",
    * "data":[{"Year":2019,"month":5,"money1":0.0,"money2":2403.0},{"Year":2019,"month":4,"money1":1.0,"money2":0.0},{"Year":2019,"month":3,"money1":0.0,"money2":0.0},{"Year":2019,"month":2,"money1":0.0,"money2":0.0},{"Year":2019,"month":1,"money1":0.0,"money2":0.0}]}}}
    * */

    private String code;
    private String msg;
    private String count;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean{
        /*
        * "Year":2019,
        * "month":5,
        * "money1":0.0,
        * "money2":2403.0}
        * */
        private String Year;
        private String month;
        private String money1;
        private String money2;

        public String getYear() {
            return Year;
        }

        public void setYear(String year) {
            Year = year;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getMoney1() {
            return money1;
        }

        public void setMoney1(String money1) {
            this.money1 = money1;
        }

        public String getMoney2() {
            return money2;
        }

        public void setMoney2(String money2) {
            this.money2 = money2;
        }
    }
}
