package ru.saintd.springstore.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.saintd.springstore.aspect.TrackTime;
import ru.saintd.springstore.controller.repr.RoleRepr;
import ru.saintd.springstore.service.ServiceInterfaces.RoleService;

@Controller
@Slf4j
@RequiredArgsConstructor
public class AdminRolesController {

    private final RoleService roleService;

    @GetMapping("/admin/roles")
    public String adminRolesPage(Model model) {
        model.addAttribute("activePage", "Roles");
        model.addAttribute("roles", roleService.findAll());
        return "admin/roles";
    }

    @GetMapping("/admin/role/{id}/edit")
    public String adminEditRole(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Roles");
        model.addAttribute("role", roleService.findById(id));
        return "admin/role_form";
    }

    @GetMapping("/admin/role/{id}/delete")
    public String adminDeleteRole(Model model, @PathVariable("id") Long id) {
        model.addAttribute("activePage", "Roles");
        roleService.deleteById(id);
        return "redirect:/admin/roles";
    }

    @GetMapping("/admin/role/create")
    public String adminCreateRole(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Roles");
        model.addAttribute("role", new RoleRepr());
        return "admin/role_form";
    }

    @TrackTime
    @PostMapping("/admin/role")
    public String adminRolesPage(Model model, RedirectAttributes redirectAttributes, RoleRepr roleRepr) {
        model.addAttribute("activePage", "Roles");
        try {
            roleService.save(roleRepr);
        } catch (Exception ex) {
            log.error("Problem with creating/updating role", ex);
            redirectAttributes.addFlashAttribute("error", true);
            if (roleRepr.getId() == null) {
                return "redirect:/admin/roles/create";
            }
            return "redirect:/admin/role/" + roleRepr.getId() + "/edit";
        }
        return "redirect:/admin/roles";
    }
}
