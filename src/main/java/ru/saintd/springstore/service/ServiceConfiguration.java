package ru.saintd.springstore.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.context.WebApplicationContext;
import ru.saintd.springstore.service.ServiceImpls.CartServiceImpl;
import ru.saintd.springstore.service.ServiceInterfaces.CartService;

@Configuration
public class ServiceConfiguration {

    @Bean
    @Scope(scopeName = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public CartService cartService() {
        return new CartServiceImpl();
    }

    @Bean
    public LocalValidatorFactoryBean validatorFactory () {
        return new LocalValidatorFactoryBean();
    }
}
