package ru.saintd.springstore.flow.register;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import ru.saintd.springstore.service.ServiceInterfaces.UserService;
import ru.saintd.springstore.service.model.SystemUser;


@Slf4j
@RequiredArgsConstructor
public class UserRegisterHandler {

    private static final String FAILURE = "failure";
    private static final String SUCCESS = "success";

    private final UserService userService;

    public UserRegisterModel init() {
        return new UserRegisterModel();
    }

    public void addBasicUserInfo(UserRegisterModel userRegisterModel, BasicUserInfo basicUserInfo) {
        userRegisterModel.setBasicUserInfo(basicUserInfo);
    }

    public void addPersonalUserInfo(UserRegisterModel userRegisterModel, PersonalUserInfo personalUserInfo) {
        userRegisterModel.setPersonalUserInfo(personalUserInfo);
    }

    public String validateBasicUserInfo(BasicUserInfo basicUserInfo, MessageContext error) {
        if (!basicUserInfo.getPassword().equals(basicUserInfo.getConfirmPassword())) {
            error.addMessage(new MessageBuilder()
                    .error()
                    .source("confirmPassword")
                    .defaultText("Password is not the same as the confirmation password!")
                    .build());
            return FAILURE;
        }
        return SUCCESS;
    }

    public String validatePersonalUserInfo(PersonalUserInfo personalUserInfo, MessageContext error) {
        return SUCCESS;
    }

    public String saveAll(UserRegisterModel urm, MessageContext error) {
        try {
            SystemUser systemUser = new SystemUser(
                    urm.getBasicUserInfo().getUsername(),
                    urm.getBasicUserInfo().getPassword(),
                    urm.getPersonalUserInfo().getFirstName(),
                    urm.getPersonalUserInfo().getLastName(),
                    urm.getBasicUserInfo().getEmail());
            userService.save(systemUser);
        } catch (Exception ex) {
            log.error("", ex);
            error.addMessage(new MessageBuilder()
            .error()
            .source("email")
            .defaultText("Internal error. Can't complete registration.")
            .build());
            return FAILURE;
        }
        return SUCCESS;
    }
}
