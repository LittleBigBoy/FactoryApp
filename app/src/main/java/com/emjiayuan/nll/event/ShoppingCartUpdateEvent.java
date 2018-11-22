package com.emjiayuan.nll.event;

import com.emjiayuan.nll.model.ShoppingCart;

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
