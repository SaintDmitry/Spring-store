package ru.saintd.springstore.flow;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.saintd.springstore.flow.register.UserRegisterHandler;
import ru.saintd.springstore.service.ServiceInterfaces.UserService;

@Configuration
public class FlowHandlersConfiguration {
    @Bean
    public UserRegisterHandler userRegisterHandler(UserService userService) {
        return new UserRegisterHandler(userService);
    }
}
