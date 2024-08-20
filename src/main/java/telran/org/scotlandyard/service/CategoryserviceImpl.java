package telran.org.scotlandyard.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import telran.org.scotlandyard.entity.Category;
import telran.org.scotlandyard.exception.CategoryNotFoundException;
import telran.org.scotlandyard.repository.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryserviceImpl implements CategoryService {

    private static final Logger log = LoggerFactory.getLogger(CategoryserviceImpl.class);

    private final CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {
        Category unit = categoryRepository.save(category);
        log.debug("New category was created {}", unit );
        return unit;
    }
    @Override
    public Category UpdateCategory(Long id, Category category) {
        Category existingCategory = findById(id);
        existingCategory.setName(category.getName());
        Category updatedCategory = categoryRepository.save(existingCategory);
        log.debug("Category with id {} was updated", id);
        return updatedCategory;
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("No category with id " + id));
        log.debug("Found category with id {}" , category);
    return category;
    }

    @Override
    public void delete(Long id){
        Category unit = findById(id);
        categoryRepository.delete(unit);

    }
}
