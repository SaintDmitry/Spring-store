package ru.saintd.springstore.controller.repr;

import lombok.Data;
import ru.saintd.springstore.persist.model.Role;

@Data
public class RoleRepr {

    private Long id;

    private String name;

    public RoleRepr() {
    }

    public RoleRepr(String name) {
        this.name = name;
    }

    public RoleRepr(Role role) {
        this.id = role.getId();
        this.name = role.getName();
    }
}
