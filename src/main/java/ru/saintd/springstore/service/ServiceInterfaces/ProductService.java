package ru.saintd.springstore.service.ServiceInterfaces;

import ru.saintd.springstore.controller.repr.ProductRepr;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    List<ProductRepr> findAll();

    ProductRepr findById(Long id);

    void deleteById(Long id);

    void save(ProductRepr productRepr) throws IOException;

    void deletePictureById(Long productId, Long pictureId) throws IOException;
}
