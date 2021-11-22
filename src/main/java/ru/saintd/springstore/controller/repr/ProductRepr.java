package ru.saintd.springstore.controller.repr;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import ru.saintd.springstore.persist.model.Brand;
import ru.saintd.springstore.persist.model.Category;
import ru.saintd.springstore.persist.model.Picture;
import ru.saintd.springstore.persist.model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class ProductRepr {

    private Long id;

    private String name;

    private BigDecimal price;

    private String description;

    private Set<Category> categories;

    private Brand brand;

    private List<Picture> pictures;

    private MultipartFile[] newPictures;

    public ProductRepr() {
    }

    public ProductRepr(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.categories = product.getCategories();
        this.brand = product.getBrand();
        this.pictures = product.getPictures();
        this.pictures.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductRepr that = (ProductRepr) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getCategoriesAsString() {
        return getCategories()
                .stream()
                .map(Category::getName)
                .collect(Collectors.joining(", "));
    }

    public void deletePictureById(Long pictureId) {
        pictures.removeIf(picture -> picture.getId().equals(pictureId)); //TODO Нужно?
    }
}
