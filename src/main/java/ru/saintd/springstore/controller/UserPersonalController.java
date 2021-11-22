package ru.saintd.springstore.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.saintd.springstore.controller.repr.AddressRepr;
import ru.saintd.springstore.persist.model.Address;
import ru.saintd.springstore.service.ServiceInterfaces.AddressService;
import ru.saintd.springstore.service.ServiceInterfaces.UserService;
import ru.saintd.springstore.service.model.SystemUser;

import java.util.Set;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserPersonalController {

    private final UserService userService;
    private final AddressService addressService;

    @GetMapping("/personal")
    public String userPersonalPage(Model model, @AuthenticationPrincipal User user) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!USERdetails " + user.getUsername());
        model.addAttribute("activePage", "Personal");
        model.addAttribute("user", userService.findByUserName(user.getUsername())); //TODO переписать на loadUserByUsername
        return "personal/personal";
    }

    @PostMapping("/personal/save")
    public String userPersonalSave(SystemUser user, Model model) {
        model.addAttribute("activePage", "Personal");
        userService.save(user);
        return "personal/personal";
    }

    @GetMapping("/personal/addresses")
    public String userPersonalAddresses(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("activePage", "Addresses");
        model.addAttribute("addresses", userService.findByUserName(user.getUsername()).getAddresses());
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!ADDRESSES:" + userService.findByUserName(user.getUsername()).getAddresses());
        Set<Address> addresses = userService.findByUserName(user.getUsername()).getAddresses();
        addresses.forEach(e -> System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! " + e.getName()));
        //TODO выводится только первый адрес
        return "personal/addresses";
    }

    @GetMapping("/personal/address/{id}/edit")
    public String userEditAddress(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Addresses");
        model.addAttribute("address", addressService.findById(id));
        return "personal/address_form";
    }

    @GetMapping("/personal/address/{id}/delete")
    public String userDeleteAddress(Model model, @PathVariable("id") Long id) {
        model.addAttribute("activePage", "Addresses");
        addressService.deleteById(id);
        return "redirect:/personal/addresses";
    }

    @GetMapping("/personal/address/create")
    public String userCreateAddress(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Addresses");
        model.addAttribute("address", new AddressRepr());
        return "personal/address_form";
    }

    @PostMapping("/personal/address")
    public String userUpsertAddress(Model model, RedirectAttributes redirectAttributes, AddressRepr addressRepr,
                                    @AuthenticationPrincipal User authUser) {
        model.addAttribute("activePage", "Addresses");
        ru.saintd.springstore.persist.model.User user = userService.findDbUserByUserName(authUser.getUsername());
        addressRepr.setUser(user); //TODO боже царя храни как это плохо
        try {
            addressService.save(addressRepr);
        } catch (Exception ex) {
            log.error("Problem with creating/updating address", ex);
            redirectAttributes.addFlashAttribute("error", true);
            if (addressRepr.getId() == null) {
                return "redirect:/personal/address/create";
            }
            return "redirect:/personal/address" + addressRepr.getId() + "/edit";
        }
        return "redirect:/personal/addresses";
    }

}
