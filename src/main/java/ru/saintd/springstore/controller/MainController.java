package ru.saintd.springstore.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.saintd.springstore.controller.repr.CartItemRepr;
import ru.saintd.springstore.controller.repr.ProductRepr;
import ru.saintd.springstore.service.ServiceInterfaces.CartService;
import ru.saintd.springstore.service.ServiceInterfaces.ProductService;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MainController {

    private final ProductService productService;
    private final CartService cartService;

    @GetMapping("/")
    public String indexPage(Model model) {
        model.addAttribute("products", productService.findAll());
        model.addAttribute("cartItems", cartService.findAllItems());
        return "index";
    }

    @GetMapping("/cart")
    public String cartPage() {
        return "cart";
    }

    @GetMapping("/checkout")
    public String checkoutPage() {
        return "checkout";
    }

    @GetMapping("/product/{id}")
    public String productPage(Model model, @PathVariable("id") Long id) {
        log.info("Product {} page", id);
        model.addAttribute("product", productService.findById(id));
        model.addAttribute("products", productService.findAll());
        return "product";
    }

    @GetMapping("/store")
    public String storePage(Model model) {
        model.addAttribute("products", productService.findAll());
        return "store";
    }

    @PostMapping("/cart/update")
    public String updateCart(CartItemRepr cartItemRepr) {
        ProductRepr productRepr = productService.findById(cartItemRepr.getProductId());
        if (productRepr != null) {
            switch (cartItemRepr.getAction()) {
                case "add":
                    cartService.addItemQty(productRepr, cartItemRepr.getQty());
                    break;
                case "qtydown":
                    cartService.removeItemQty(productRepr, cartItemRepr.getQty());
                    break;
                case "delete":
                    cartService.removeItem(productRepr);
                    break;
            }
        }
        return "redirect:" + cartItemRepr.getPageUrl();
    }
}
//TODO поставить заглушку на ERROR

//TODO Четко задать размеры изображений в админке и в магазине

//TODO Убрать лишние классы

//TODO logout

//TODO сделать корзину и заказ
