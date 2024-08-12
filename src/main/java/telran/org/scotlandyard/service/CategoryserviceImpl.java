package telran.org.scotlandyard.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telran.org.scotlandyard.entity.Category;
import telran.org.scotlandyard.exception.CategoryNotFoundException;
import telran.org.scotlandyard.repository.CategoryReposit;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryserviceImpl implements CategoryService {

    private static final Logger log = LoggerFactory.getLogger(CategoryserviceImpl.class);
    @Autowired
    private CategoryReposit categoryReposit;

    @Override
    public Category createCategory(Category category) {
        Category unit = categoryReposit.save(category);
        log.debug("New category was created {}", unit );
        return unit;
    }
    @Override
    public Category UpdateCategory(Category category) {
        return null;
    }

    @Override
    public List<Category> getAll() {
        return categoryReposit.findAll();
    }

    @Override
    public Category findById(Long id){
        Category category = categoryReposit.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("No category with id " + id));
        log.debug("Found category with id {}" , category);
    return category;
    }

    @Override
    public void delete(Long id){
        Category unit = findById(id);
        categoryReposit.delete(unit);

    }
}
