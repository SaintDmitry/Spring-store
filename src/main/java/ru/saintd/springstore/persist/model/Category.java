package ru.saintd.springstore.persist.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Category extends AbstractIdentifiable {

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "categories")
    private List<Product> products;
}
