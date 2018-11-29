package com.emjiayuan.nll.event;

import com.emjiayuan.nll.model.Soup;

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
