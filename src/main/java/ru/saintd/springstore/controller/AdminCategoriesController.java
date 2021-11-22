package ru.saintd.springstore.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.saintd.springstore.controller.repr.CategoryRepr;
import ru.saintd.springstore.service.ServiceInterfaces.CategoryService;

@Controller
@Slf4j
@RequiredArgsConstructor
public class AdminCategoriesController {

    private final CategoryService categoryService;

    @GetMapping("/admin/categories")
    public String adminCategoriesPage(Model model) {
        model.addAttribute("activePage", "Categories");
        model.addAttribute("categories", categoryService.findAll());
        return "admin/categories";
    }

    @GetMapping("/admin/category/{id}/edit")
    public String adminEditCategory(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Categories");
        model.addAttribute("category", categoryService.findById(id));
        return "admin/category_form";
    }

    @GetMapping("/admin/category/{id}/delete")
    public String adminDeleteCategory(Model model, @PathVariable("id") Long id) {
        model.addAttribute("activePage", "Categories");
        categoryService.deleteById(id);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/category/create")
    public String adminCreateCategory(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Categories");
        model.addAttribute("category", new CategoryRepr());
        return "admin/category_form";
    }

    @PostMapping("/admin/category")
    public String adminUpsertCategory(Model model, RedirectAttributes redirectAttributes, CategoryRepr categoryRepr) {
        model.addAttribute("activePage", "Categories");
        try {
            categoryService.save(categoryRepr);
        } catch (Exception ex) {
            log.error("Problem with creating/updating category", ex);
            redirectAttributes.addFlashAttribute("error", true);
            if (categoryRepr.getId() == null) {
                return "redirect:/admin/category/create";
            }
            return "redirect:/admin/category/" + categoryRepr.getId() + "/edit";
        }
        return "redirect:/admin/categories";
    }
}
