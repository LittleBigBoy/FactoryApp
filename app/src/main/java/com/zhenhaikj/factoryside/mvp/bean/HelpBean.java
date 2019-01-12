package com.zhenhaikj.factoryside.mvp.bean;

public class HelpBean {

    /**
     * id : 5
     * question : 帮忙
     * content : <p>客服电话：4008-123337</p>
     * status : 1
     * posttime : null
     */

    private String id;
    private String question;
    private String content;
    private String status;
    private Object posttime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getPosttime() {
        return posttime;
    }

    public void setPosttime(Object posttime) {
        this.posttime = posttime;
    }
}
