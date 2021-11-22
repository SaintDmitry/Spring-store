package ru.saintd.springstore.controller.validation;

import lombok.RequiredArgsConstructor;
import ru.saintd.springstore.service.ServiceInterfaces.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class EmailNotRegisteredValidator implements ConstraintValidator<EmailNotRegistered, String> {

    private final UserService userService;

    @Override
    public void initialize(EmailNotRegistered constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return !userService.existsUserByEmail(value);
    }
}
