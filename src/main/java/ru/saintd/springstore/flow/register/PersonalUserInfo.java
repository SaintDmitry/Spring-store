package ru.saintd.springstore.flow.register;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class PersonalUserInfo implements Serializable {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    public PersonalUserInfo() {
    }
}
