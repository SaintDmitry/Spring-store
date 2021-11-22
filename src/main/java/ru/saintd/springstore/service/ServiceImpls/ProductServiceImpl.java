package ru.saintd.springstore.service.ServiceImpls;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.saintd.springstore.controller.repr.ProductRepr;
import ru.saintd.springstore.persist.model.Picture;
import ru.saintd.springstore.persist.model.PictureData;
import ru.saintd.springstore.persist.model.Product;
import ru.saintd.springstore.persist.repo.ProductRepository;
import ru.saintd.springstore.service.ServiceInterfaces.ProductService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductRepr> findAll() {
        return productRepository.findAll().stream()
                .map(ProductRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    public ProductRepr findById(Long id) {
        return new ProductRepr(productRepository.findById(id).get());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void save(ProductRepr productRepr) throws IOException {
        Product product = productRepr.getId() != null ? productRepository.findById(productRepr.getId()).get()
                : new Product();
        product.setName(productRepr.getName());
        product.setCategories(productRepr.getCategories());
        product.setBrand(productRepr.getBrand());
        product.setPrice(productRepr.getPrice());
        product.setDescription(productRepr.getDescription());
        if (productRepr.getNewPictures() != null) {
            for (MultipartFile newPicture : productRepr.getNewPictures()) {
                log.info("Product {} file {} size {}", product.getId(),
                        newPicture.getOriginalFilename(), newPicture.getSize());

                if (product.getPictures() == null) {
                    product.setPictures(new ArrayList<>());
                }

                product.getPictures().add(new Picture(
                        newPicture.getOriginalFilename(),
                        newPicture.getContentType(),
                        new PictureData(newPicture.getBytes()),
                        product));
            }
        }
        productRepository.save(product);
    }

    public void deletePictureById(Long productId, Long pictureId) throws IOException {
        ProductRepr productRepr = findById(productId);
        productRepr.deletePictureById(pictureId);
        save(productRepr);
    }
}
