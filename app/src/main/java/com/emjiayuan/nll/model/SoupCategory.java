package com.emjiayuan.nll.model;

import java.io.Serializable;
import java.util.List;

public class SoupCategory implements Serializable {

    /**
     * id : 219
     * name : 测试分类22
     * parentid : null
     * image : http://qiniu.emjiayuan.com/upload_file/ems/2018070511892927785
     * delflag : 0
     * title_image : null
     * topimage : null
     * banner : http://qiniu.emjiayuan.com/upload_file/ems/2018070511202370234
     * linkid : null
     * top : 20
     * type : null
     * indexstatus : 1
     * status : 1
     * is_soup : 1
     * type_str : 汤料
     * products : []
     */

    private String id;
    private String name;
    private String parentid;
    private String image;
    private String delflag;
    private String title_image;
    private String topimage;
    private String banner;
    private String linkid;
    private String top;
    private String type;
    private String indexstatus;
    private String status;
    private String is_soup;
    private String type_str;
    private List<Soup> products;

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

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDelflag() {
        return delflag;
    }

    public void setDelflag(String delflag) {
        this.delflag = delflag;
    }

    public String getTitle_image() {
        return title_image;
    }

    public void setTitle_image(String title_image) {
        this.title_image = title_image;
    }

    public String getTopimage() {
        return topimage;
    }

    public void setTopimage(String topimage) {
        this.topimage = topimage;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIndexstatus() {
        return indexstatus;
    }

    public void setIndexstatus(String indexstatus) {
        this.indexstatus = indexstatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIs_soup() {
        return is_soup;
    }

    public void setIs_soup(String is_soup) {
        this.is_soup = is_soup;
    }

    public String getType_str() {
        return type_str;
    }

    public void setType_str(String type_str) {
        this.type_str = type_str;
    }

    public List<Soup> getProducts() {
        return products;
    }

    public void setProducts(List<Soup> products) {
        this.products = products;
    }
}
