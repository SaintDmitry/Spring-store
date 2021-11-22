package ru.saintd.springstore.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.saintd.springstore.controller.repr.ProductRepr;
import ru.saintd.springstore.service.ServiceInterfaces.BrandService;
import ru.saintd.springstore.service.ServiceInterfaces.CategoryService;
import ru.saintd.springstore.service.ServiceInterfaces.ProductService;

import java.io.IOException;

@Controller
@Slf4j
@RequiredArgsConstructor
public class AdminProductsController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final BrandService brandService;

    @GetMapping("/admin/products")
    public String adminProductsPage(Model model) {
        model.addAttribute("activePage", "Products");
        model.addAttribute("products", productService.findAll());
        return "admin/products";
    }

    @GetMapping("/admin/product/{id}/edit")
    public String adminEditProduct(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Products");
        model.addAttribute("product", productService.findById(id));
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("brands", brandService.findAll());
        return "admin/product_form";
    }

    @GetMapping("/admin/product/{id}/delete")
    public String adminDeleteProduct(Model model, @PathVariable("id") Long id) {
        model.addAttribute("activePage", "Products");
        productService.deleteById(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/create")
    public String adminCreateProduct(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Products");
        model.addAttribute("product", new ProductRepr());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("brands", brandService.findAll());
        return "admin/product_form";
    }

    @PostMapping("/admin/product")
    public String adminUpsertProduct(Model model, RedirectAttributes redirectAttributes, ProductRepr productRepr) {
        model.addAttribute("activePage", "Products");

        try {
            productService.save(productRepr);
        } catch (Exception ex) {
            log.error("Problem with creating/updating product", ex);
            redirectAttributes.addFlashAttribute("error", true);
            if (productRepr.getId() == null) {
                return "redirect:/admin/product/create";
            }
            return "redirect:/admin/product/" + productRepr.getId() + "/edit";
        }
        return "redirect:/admin/products";
    }

    @PostMapping("/admin/product/{id}/deletepicture")
    public String adminDeleteProductPicture(@PathVariable("id") Long id,
                                            @RequestParam String pageUrl,
                                            @RequestParam Long pictureid) {

        try {
            productService.deletePictureById(id, pictureid);
        } catch (IOException e) {
            log.error("Problem with deleting product {} picture {}", id, pictureid);
            log.error("IOException", e);
        }
        return "redirect:" + pageUrl;
    }
}
