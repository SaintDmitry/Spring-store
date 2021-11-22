package ru.saintd.springstore.service.ServiceImpls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.saintd.springstore.controller.repr.BrandRepr;
import ru.saintd.springstore.persist.model.Brand;
import ru.saintd.springstore.persist.repo.BrandRepository;
import ru.saintd.springstore.service.ServiceInterfaces.BrandService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Override
    public List<BrandRepr> findAll() {
        return brandRepository.findAll().stream()
                .map(BrandRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    public BrandRepr findById(Long id) {
        return new BrandRepr(brandRepository.findById(id).get());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        brandRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void save(BrandRepr brandRepr) {
        Brand brand = brandRepr.getId() != null ? brandRepository.findById(brandRepr.getId()).get()
                : new Brand();
        brand.setName(brandRepr.getName());
        brandRepository.save(brand);
    }
}
