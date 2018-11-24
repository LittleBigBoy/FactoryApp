package com.emjiayuan.nll.model;

import java.io.Serializable;

public class Privilege implements Serializable {

    /**
     * id : 2
     * class_id : 10
     * title : 客服优先
     * content : 客服优先
     * images : http://qiniu.emjiayuan.com/upload_file/ems/2018110512173072644
     * delflag : 0
     * status : 1
     */

    private String id;
    private String class_id;
    private String title;
    private String content;
    private String images;
    private String delflag;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getDelflag() {
        return delflag;
    }

    public void setDelflag(String delflag) {
        this.delflag = delflag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
