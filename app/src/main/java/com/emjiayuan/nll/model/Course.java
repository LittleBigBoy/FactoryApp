package com.emjiayuan.nll.model;

import java.io.Serializable;

public class Course implements Serializable {

    /**
     * id : 1
     * title : 番茄鸡蛋盖浇饭
     * subtitle : 嘻嘻
     * createtime : 1542251830
     * images : http://qiniu.emjiayuan.com/upload_file/ems/2018111516351085570
     * pasttime : 6天前
     * pastday : 11.15
     * pastyear : 2018
     */

    private String id;
    private String title;
    private String subtitle;
    private String createtime;
    private String images;
    private String pasttime;
    private String pastday;
    private String pastyear;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getPasttime() {
        return pasttime;
    }

    public void setPasttime(String pasttime) {
        this.pasttime = pasttime;
    }

    public String getPastday() {
        return pastday;
    }

    public void setPastday(String pastday) {
        this.pastday = pastday;
    }

    public String getPastyear() {
        return pastyear;
    }

    public void setPastyear(String pastyear) {
        this.pastyear = pastyear;
    }
}
