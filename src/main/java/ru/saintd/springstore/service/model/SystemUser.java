package ru.saintd.springstore.service.model;

import lombok.Data;
import ru.saintd.springstore.persist.model.Address;
import ru.saintd.springstore.persist.model.Role;
import ru.saintd.springstore.persist.model.User;

import java.util.HashSet;
import java.util.Set;

@Data
public class SystemUser {

    private Long id;

    private String userName;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    private Set<Role> roles;

    private Set<Address> addresses;

    public SystemUser() {
    }

    public SystemUser(String userName, String password, String firstName,
                      String lastName, String email) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = new HashSet<>();
        this.addresses = new HashSet<>();
    }

    public SystemUser(String userName, String password, String firstName,
                      String lastName, String email, Set<Role> roles, Set<Address> addresses) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
        this.addresses = addresses;
    }

    public SystemUser(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.roles = user.getRoles();
        this.addresses = user.getAddresses();
    }
}
