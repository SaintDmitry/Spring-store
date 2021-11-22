package ru.saintd.springstore.service.model;

import lombok.Data;
import ru.saintd.springstore.controller.repr.ProductRepr;

@Data
public class ProductInfoUNUSED {

    private ProductRepr product;

    private String size;

    private String color;

    public ProductInfoUNUSED() {
    }

    public ProductInfoUNUSED(ProductRepr product, String size, String color) {
        this.product = product;
        this.size = size;
        this.color = color;
    }

    enum asdasdasd {
        asdasd,
        asdasdasd,
        adsasdasdasd,
        aas
    }


}
