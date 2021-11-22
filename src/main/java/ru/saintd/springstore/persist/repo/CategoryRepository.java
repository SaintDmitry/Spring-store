package ru.saintd.springstore.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.saintd.springstore.controller.repr.CategoryRepr;
import ru.saintd.springstore.persist.model.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("select new ru.saintd.springstore.controller.repr.CategoryRepr(c.id, c.name, count (p.id)) " +
            "from Category c " +
            "left join c.products p " +
            "group by c.id, c.name")
    List<CategoryRepr> getAllCategoryRepr();
}
