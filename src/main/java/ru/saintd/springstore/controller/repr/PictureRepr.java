package ru.saintd.springstore.controller.repr;

import lombok.Data;
import ru.saintd.springstore.persist.model.Picture;
import ru.saintd.springstore.persist.model.PictureData;
import ru.saintd.springstore.persist.model.Product;

@Data
public class PictureRepr {

    private Long id;

    private String name;

    private String contentType;

    private PictureData pictureData;

    private Product product;

    public PictureRepr() {
    }

    public PictureRepr(Long id, String name, String contentType, PictureData pictureData, Product product) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;
        this.pictureData = pictureData;
        this.product = product;
    }

    public PictureRepr(Picture picture) {
        this.id = picture.getId();
        this.name = picture.getName();
        this.contentType = picture.getContentType();
        this.pictureData = picture.getPictureData();
        this.product = picture.getProduct();
    }
}
