package com.emjiayuan.nll.model;

import java.io.Serializable;

public class BannerItem implements Serializable {
    /*"id":"151",
    "title":"伊穆家园",
    "type":"0",
    "image":"http:\/\/qiniu.emjiayuan.com\/banner1524909936851",
    "status":"1",
    "linkid":"0",
    "top":"0",
    "postdate":null*/
    private String id;
    private String title;
    private String type;
    private String image;
    private String status;
    private String linkid;
    private String top;
    private String postdate;
    private String localtion;

    public String getLocaltion() {
        return localtion;
    }

    public void setLocaltion(String localtion) {
        this.localtion = localtion;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLinkid() {
        return linkid;
    }

    public void setLinkid(String linkid) {
        this.linkid = linkid;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getPostdate() {
        return postdate;
    }

    public void setPostdate(String postdate) {
        this.postdate = postdate;
    }
}
