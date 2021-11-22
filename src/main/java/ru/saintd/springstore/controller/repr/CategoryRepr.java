package ru.saintd.springstore.controller.repr;

import lombok.Data;
import ru.saintd.springstore.persist.model.Category;

@Data
public class CategoryRepr {

    private Long id;

    private String name;

    private Long productCount;

    public CategoryRepr() {
    }

    public CategoryRepr(long id, String name, long productCount) {
        this.id = id;
        this.name = name;
        this.productCount = productCount;
    }

    public CategoryRepr(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }
}
