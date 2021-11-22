package ru.saintd.springstore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.saintd.springstore.persist.model.User;
import ru.saintd.springstore.service.ServiceInterfaces.RoleService;
import ru.saintd.springstore.service.ServiceInterfaces.UserService;
import ru.saintd.springstore.service.model.SystemUser;

@Controller
@RequiredArgsConstructor
public class AdminUserController {

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping("/admin")
    public String adminIndexPage(Model model) {
        model.addAttribute("activePage", "None");
        return "admin/index";
    }

    @GetMapping("/admin/users")
    public String adminUsersPage(Model model) {
        model.addAttribute("activePage", "Users");
        model.addAttribute("users", userService.findAll());
        return "admin/users";
    }

    @GetMapping("/admin/user/{id}/edit")
    public String adminEditUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Users");
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("roles", roleService.findAll());
        return "admin/user_form";
    }

    @GetMapping("/admin/user/{id}/delete")
    public String adminDeleteUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("activePage", "Users");
        userService.delete(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/user/create")
    public String adminCreateUser(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Users");
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.findAll());
        return "admin/user_form";
    }

    @PostMapping("/admin/user")
    public String adminUpsertUser(SystemUser user, Model model, BindingResult bindingResult) {
        model.addAttribute("activePage", "Users");
        if (bindingResult.hasErrors()) {
            return "admin/user_form";
        }
        userService.save(user);
        return "redirect:/admin/users";
    }
}
