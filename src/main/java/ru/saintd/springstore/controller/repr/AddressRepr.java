package ru.saintd.springstore.controller.repr;

import lombok.Data;
import ru.saintd.springstore.persist.model.Address;
import ru.saintd.springstore.persist.model.User;

@Data
public class AddressRepr {

    private Long id;

    private String name;

    private User user;

    public AddressRepr() {
    }

    public AddressRepr(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public AddressRepr(Address address) {
        this.id = address.getId();
        this.name = address.getName();
    }
}
