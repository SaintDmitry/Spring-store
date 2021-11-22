package ru.saintd.springstore.persist.model;

import lombok.Data;
import javax.persistence.*;

@Data
@MappedSuperclass
public abstract class AbstractIdentifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
}
