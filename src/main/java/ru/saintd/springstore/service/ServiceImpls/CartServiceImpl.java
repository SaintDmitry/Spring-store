package ru.saintd.springstore.service.ServiceImpls;

import lombok.extern.slf4j.Slf4j;
import ru.saintd.springstore.controller.repr.ProductRepr;
import ru.saintd.springstore.service.ServiceInterfaces.CartService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class CartServiceImpl implements CartService {

    private Map<ProductRepr, Integer> cartItems;

    public CartServiceImpl() {
        cartItems = new ConcurrentHashMap<>();
    }

    public void addItemQty(ProductRepr productRepr, int qty) {
        cartItems.put(productRepr, cartItems.getOrDefault(productRepr, 0) + qty);
    }

    @Override
    public void removeItemQty(ProductRepr productRepr, int qty) {
        int currentQty = cartItems.getOrDefault(productRepr, 0);
        if (currentQty - qty > 0) {
            cartItems.put(productRepr, currentQty - qty);
        } else {
            cartItems.remove(productRepr);
        }
    }

    @Override
    public void removeItem(ProductRepr productRepr) {
        cartItems.remove(productRepr);
    }

    @Override
    public Map<ProductRepr, Integer> findAllItems() {
        return Collections.unmodifiableMap(cartItems);
    }

    @Override
    public Integer getItemsQty() {
        return cartItems.size();
    }

    @Override
    public BigDecimal getSubTotal() {
        return cartItems.entrySet().stream()
                .map(e-> e.getKey().getPrice().multiply(new BigDecimal(e.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @PostConstruct
    public void postConstruct() {

    }

    @PreDestroy
    public void preDestroy() {

    }
}
