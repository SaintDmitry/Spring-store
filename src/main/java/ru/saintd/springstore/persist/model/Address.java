package ru.saintd.springstore.persist.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "addresses")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Address extends AbstractIdentifiable {

    @Column(name = "name")
    private String name;

    @ManyToOne
    private User user;




}
