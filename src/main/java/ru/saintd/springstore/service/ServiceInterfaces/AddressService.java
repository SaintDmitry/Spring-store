package ru.saintd.springstore.service.ServiceInterfaces;

import ru.saintd.springstore.controller.repr.AddressRepr;

import java.util.List;

public interface AddressService {

    List<AddressRepr> findAll();

    AddressRepr findByAddressName(String addressName);

    AddressRepr findById(Long id);

    void deleteById(Long id);

    void save(AddressRepr product);
}
