package com.emjiayuan.nll.model;

public class LogisticsBean {

    /**
     * time : 2018-06-24 19:05:03
     * ftime : 2018-06-24 19:05:03
     * context : 【西安市】 快件离开 【西安中转】 发往 【南京中转部】
     */

    private String time;
    private String ftime;
    private String context;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFtime() {
        return ftime;
    }

    public void setFtime(String ftime) {
        this.ftime = ftime;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
