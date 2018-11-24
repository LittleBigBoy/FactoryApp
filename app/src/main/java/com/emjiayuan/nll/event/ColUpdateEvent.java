package com.emjiayuan.nll.event;

import com.emjiayuan.nll.model.Product;

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
