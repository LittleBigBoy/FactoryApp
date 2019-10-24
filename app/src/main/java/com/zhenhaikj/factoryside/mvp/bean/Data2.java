package com.zhenhaikj.factoryside.mvp.bean;

import java.io.Serializable;
import java.util.List;

public class Data2<T> implements Serializable {


    /**
     * Item1 : true
     * Item2 : 17
     */

    private boolean Item1;
    private T Item2;
    private Double Item3;

    public boolean isItem1() {
        return Item1;
    }

    public void setItem1(boolean item1) {
        Item1 = item1;
    }

    public T getItem2() {
        return Item2;
    }

    public void setItem2(T item2) {
        Item2 = item2;
    }

    public Double getItem3() {
        return Item3;
    }

    public void setItem3(Double item3) {
        Item3 = item3;
    }
}
