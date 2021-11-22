package ru.saintd.springstore.service.ServiceImpls;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.saintd.springstore.controller.repr.AddressRepr;
import ru.saintd.springstore.persist.model.Address;
import ru.saintd.springstore.persist.repo.AddressRepository;
import ru.saintd.springstore.service.ServiceInterfaces.AddressService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    @Autowired
    private final AddressRepository addressRepository;

    @Override
    public List<AddressRepr> findAll() {
        return addressRepository.findAll().stream()
                .map(AddressRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    public AddressRepr findByAddressName(String addressName) {
        Address address = addressRepository.findOneByUserName(addressName);
        return new AddressRepr(address.getId(), address.getName());
    }

    @Override
    public AddressRepr findById(Long id) {
        return new AddressRepr(addressRepository.findById(id).get());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void save(AddressRepr addressRepr) {
        Address address = addressRepr.getId() != null ? addressRepository.findById(addressRepr.getId()).get()
                : new Address();
        address.setName(addressRepr.getName());
        address.setUser(addressRepr.getUser());
        addressRepository.save(address);
        int[] nums = null;
    }
}
