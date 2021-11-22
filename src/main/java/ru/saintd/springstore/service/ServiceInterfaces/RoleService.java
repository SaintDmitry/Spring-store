package ru.saintd.springstore.service.ServiceInterfaces;

import ru.saintd.springstore.controller.repr.RoleRepr;

import java.io.IOException;
import java.util.List;

public interface RoleService {

    List<RoleRepr> findAll();

    RoleRepr findById(Long id);

    void deleteById(Long id);

    void save(RoleRepr roleRepr);
}
