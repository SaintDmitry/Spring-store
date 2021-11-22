package ru.saintd.springstore.service.ServiceImpls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.saintd.springstore.controller.repr.CategoryRepr;
import ru.saintd.springstore.persist.model.Category;
import ru.saintd.springstore.persist.repo.CategoryRepository;
import ru.saintd.springstore.service.ServiceInterfaces.CategoryService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryRepr> findAll() {
        return categoryRepository.getAllCategoryRepr();
    }

    @Override
    public CategoryRepr findById(Long id) {
        return new CategoryRepr(categoryRepository.findById(id).get());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void save(CategoryRepr categoryRepr) {
        Category category = categoryRepr.getId() != null ? categoryRepository.findById(categoryRepr.getId()).get()
                : new Category();
        category.setName(categoryRepr.getName());
        categoryRepository.save(category);
    }
}
