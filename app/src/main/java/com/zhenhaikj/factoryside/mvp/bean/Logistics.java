package com.zhenhaikj.factoryside.mvp.bean;

import java.io.Serializable;
import java.util.List;

public class Logistics implements Serializable {

    /*
     *  \"content\": \"预计送达\",    \"time\": \"2019-05-10 16:00:00\"
     * */

    private String content;
    private String time;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
