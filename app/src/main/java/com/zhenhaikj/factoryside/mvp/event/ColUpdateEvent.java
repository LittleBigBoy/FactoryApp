package com.zhenhaikj.factoryside.mvp.event;

import com.zhenhaikj.factoryside.mvp.bean.Product;

import java.util.List;

public class ColUpdateEvent {
    private List<Product> productList;

    public ColUpdateEvent(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
