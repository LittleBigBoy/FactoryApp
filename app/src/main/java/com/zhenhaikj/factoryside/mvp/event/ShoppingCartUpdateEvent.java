package com.zhenhaikj.factoryside.mvp.event;

import com.zhenhaikj.factoryside.mvp.bean.ShoppingCart;

import java.util.List;

public class ShoppingCartUpdateEvent {
    private List<ShoppingCart> shoppingCartList;

    public ShoppingCartUpdateEvent(List<ShoppingCart> shoppingCartList) {
        this.shoppingCartList = shoppingCartList;
    }

    public List<ShoppingCart> getshoppingCartList() {
        return shoppingCartList;
    }

    public void setshoppingCartList(List<ShoppingCart> shoppingCartList) {
        this.shoppingCartList = shoppingCartList;
    }
}
