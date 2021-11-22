package ru.saintd.springstore.flow.register;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class UserRegisterModel implements Serializable {

    private BasicUserInfo basicUserInfo;
    private PersonalUserInfo personalUserInfo;
}
