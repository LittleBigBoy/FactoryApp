package com.emjiayuan.nll.base;

/**
 * Created by Administrator on 2018/5/2.
 */
public class BaseResult<B> {
    private String code;
    private String message;
    private B data;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public B getData() {
        return data;
    }

    public void setData(B data) {
        this.data = data;
    }
}
