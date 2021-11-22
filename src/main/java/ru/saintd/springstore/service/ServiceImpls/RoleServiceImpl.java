package ru.saintd.springstore.service.ServiceImpls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.saintd.springstore.controller.repr.RoleRepr;
import ru.saintd.springstore.persist.model.Role;
import ru.saintd.springstore.persist.repo.RoleRepository;
import ru.saintd.springstore.service.ServiceInterfaces.RoleService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public List<RoleRepr> findAll() {
        return roleRepository.findAll().stream()
                .map(RoleRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    public RoleRepr findById(Long id) {
        return new RoleRepr(roleRepository.findById(id).get());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void save(RoleRepr roleRepr) {
        Role role = roleRepr.getId() != null ? roleRepository.findById(roleRepr.getId()).get()
                : new Role();
        role.setName(roleRepr.getName());
        roleRepository.save(role);
    }
}
