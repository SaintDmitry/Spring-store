package ru.saintd.springstore.service.ServiceInterfaces;

import ru.saintd.springstore.controller.repr.ProductRepr;

import java.math.BigDecimal;
import java.util.Map;

public interface CartService {

    void addItemQty(ProductRepr productRepr, int qty);

    void removeItemQty(ProductRepr productRepr, int qty);

    void removeItem(ProductRepr productRepr);

    Map<ProductRepr, Integer> findAllItems();

    Integer getItemsQty();

    BigDecimal getSubTotal();
}
