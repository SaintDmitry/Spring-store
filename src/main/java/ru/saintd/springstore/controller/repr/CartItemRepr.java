package ru.saintd.springstore.controller.repr;

import lombok.Data;

@Data
public class CartItemRepr {

    private Long productId;
    private Integer qty;
    private String size;
    private String color;
    private String pageUrl;
    private String action;

    public CartItemRepr() {
    }
}
