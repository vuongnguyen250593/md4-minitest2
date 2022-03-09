package cg.service.implement;

import cg.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ICategoryService implements cg.service.ICategoryService {
    @Autowired
    private ICategoryService iCategoryService;

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return iCategoryService.findAll(pageable);
    }

    @Override
    public Category findById(Long id) {
        return iCategoryService.findById(id);
    }
}
