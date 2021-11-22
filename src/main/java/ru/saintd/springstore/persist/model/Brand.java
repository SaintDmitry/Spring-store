package ru.saintd.springstore.persist.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "brands")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Brand extends AbstractIdentifiable {

    @Column(name = "name")
    private String name;

    @OneToMany(
            mappedBy = "brand",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<Product> products;
}
