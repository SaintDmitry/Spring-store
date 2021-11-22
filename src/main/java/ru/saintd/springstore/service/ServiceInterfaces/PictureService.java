package ru.saintd.springstore.service.ServiceInterfaces;

import ru.saintd.springstore.controller.repr.PictureRepr;

import java.util.List;

public interface PictureService {

    List<PictureRepr> findAll();

    PictureRepr findById(Long id);

    void deleteById(Long id);

    void save(PictureRepr product);
}
