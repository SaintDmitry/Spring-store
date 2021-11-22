package ru.saintd.springstore.persist.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "pictures")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Picture extends AbstractIdentifiable {

    @Column(name = "name")
    private String name;

    @Column(name = "content_type")
    private String contentType;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    @JoinColumn(name = "picture_data_id")
    private PictureData pictureData;

    @ManyToOne(optional = false)
    private Product product;

    public Picture() {
    }

    public Picture(String name, String contentType, PictureData pictureData, Product product) {
        this.name = name;
        this.contentType = contentType;
        this.pictureData = pictureData;
        this.product = product;
    }
}
