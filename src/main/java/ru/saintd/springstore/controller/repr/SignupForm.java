package ru.saintd.springstore.controller.repr;

import lombok.Data;
import ru.saintd.springstore.controller.validation.EmailNotRegistered;

import javax.validation.constraints.NotBlank;

@Data
public class SignupForm {

    @NotBlank
    @EmailNotRegistered
    private String email;
}
