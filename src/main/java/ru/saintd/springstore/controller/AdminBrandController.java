package ru.saintd.springstore.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.saintd.springstore.controller.repr.BrandRepr;
import ru.saintd.springstore.service.ServiceInterfaces.BrandService;

@Controller
@Slf4j
@RequiredArgsConstructor
public class AdminBrandController {

    private final BrandService brandService;

    @GetMapping("/admin/brands")
    public String adminBrandsPage(Model model) {
        model.addAttribute("activePage", "Brands");
        model.addAttribute("brands", brandService.findAll());
        return "admin/brands";
    }

    @GetMapping("/admin/brand/{id}/edit")
    public String adminEditBrand(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Brands");
        model.addAttribute("brand", brandService.findById(id));
        return "admin/brand_form";
    }

    @GetMapping("/admin/brand/{id}/delete")
    public String adminDeleteBrand(Model model, @PathVariable("id") Long id) {
        model.addAttribute("activePage", "Brands");
        brandService.deleteById(id);
        return "redirect:/admin/brands";
    }

    @GetMapping("/admin/brand/create")
    public String adminCreateBrand(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Brands");
        model.addAttribute("brand", new BrandRepr());
        return "admin/brand_form";
    }

    @PostMapping("/admin/brand")
    public String adminUpsertBrand(Model model, RedirectAttributes redirectAttributes, BrandRepr brandRepr) {
        model.addAttribute("activePage", "Brands");
        try {
            brandService.save(brandRepr);
        } catch (Exception ex) {
            log.error("Problem with creating/updating brand", ex);
            redirectAttributes.addFlashAttribute("error", true);
            if (brandRepr.getId() == null) {
                return "redirect:/admin/brand/create";
            }
            return "redirect:/admin/brand/" + brandRepr.getId() + "/edit";
        }
        return "redirect:/admin/brands";
    }
}
