package com.zhenhaikj.factoryside.mvp.event;

import com.zhenhaikj.factoryside.mvp.bean.Soup;

import java.util.List;

public class SoupUpdateEvent {
    private List<Soup> list;

    public SoupUpdateEvent(List<Soup> list) {
        this.list = list;
    }

    public List<Soup> getSoupList() {
        return list;
    }

    public void setSoupList(List<Soup> list) {
        this.list = list;
    }
}
