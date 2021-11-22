package ru.saintd.springstore.controller.repr;

import lombok.Data;
import ru.saintd.springstore.persist.model.Brand;

@Data
public class BrandRepr {

    private Long id;

    private String name;

    public BrandRepr() {
    }

    public BrandRepr(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    public BrandRepr(Brand brand) {
        this.id = brand.getId();
        this.name = brand.getName();
    }
}
