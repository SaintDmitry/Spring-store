package ru.saintd.springstore.service.ServiceInterfaces;

import ru.saintd.springstore.controller.repr.BrandRepr;

import java.util.List;

public interface BrandService {

    List<BrandRepr> findAll();

    BrandRepr findById(Long id);

    void deleteById(Long id);

    void save(BrandRepr product);
}
