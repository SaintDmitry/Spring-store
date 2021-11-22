package ru.saintd.springstore.service.ServiceInterfaces;

import ru.saintd.springstore.controller.repr.CategoryRepr;

import java.io.IOException;
import java.util.List;

public interface CategoryService {

    List<CategoryRepr> findAll();

    CategoryRepr findById(Long id);

    void deleteById(Long id);

    void save(CategoryRepr categoryRepr);
}
