package ru.saintd.springstore.service.ServiceInterfaces;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.saintd.springstore.persist.model.User;
import ru.saintd.springstore.service.model.SystemUser;

import java.util.List;

public interface UserService extends UserDetailsService {

    SystemUser findById(Long id);

    SystemUser findByUserName(String username);

    User findDbUserByUserName(String username);

    boolean existsUserByEmail(String email);

    boolean save(SystemUser systemUser);

    void delete(Long id);

    List<SystemUser> findAll();
}
