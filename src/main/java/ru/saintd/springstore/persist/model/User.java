package ru.saintd.springstore.persist.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString(onlyExplicitlyIncluded = true)
public class User extends AbstractIdentifiable{

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToMany(
            mappedBy = "user",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private Set<Address> addresses;

    public User() {
        this.roles = new HashSet<>();
        this.addresses = new HashSet<>();
    }

    public User(String userName, String password, String firstName, String lastName, String email) {
        this(userName, password, firstName, lastName, email, new HashSet<>(), new HashSet<>());
    }

    public User(String userName, String password, String firstName, String lastName, String email,
                Set<Role> roles, Set<Address> addresses) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
        this.addresses = addresses;
    }
}
