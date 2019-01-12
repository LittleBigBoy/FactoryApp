package com.zhenhaikj.factoryside.mvp.bean;

import com.zhenhaikj.factoryside.mvp.utils.MyUtils;

import java.io.Serializable;

public class News implements Serializable {

    /**
     * id : 39
     * title : lala
     * topic : null
     * summary : null
     * content : <p>ssssssssss</p>
     * createtime : 1542704658
     * category : 0
     * images : http://qiniu.emjiayuan.com/upload_file/ems/2018111516351085570
     * userid : null
     * tag : null
     * delflag : 0
     * replyscount : null
     * status : 1
     * comments : null
     */

    private String id;
    private String title;
    private String topic;
    private String summary;
    private String content;
    private String createtime;
    private String category;
    private String images;
    private String userid;
    private String tag;
    private String delflag;
    private String replyscount;
    private String status;
    private String comments;

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

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatetime() {
        return MyUtils.stampToDate(createtime);
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDelflag() {
        return delflag;
    }

    public void setDelflag(String delflag) {
        this.delflag = delflag;
    }

    public String getReplyscount() {
        return replyscount;
    }

    public void setReplyscount(String replyscount) {
        this.replyscount = replyscount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
