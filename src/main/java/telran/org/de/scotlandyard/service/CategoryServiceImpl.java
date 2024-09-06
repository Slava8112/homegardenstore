package telran.org.de.scotlandyard.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import telran.org.de.scotlandyard.entity.Category;
import telran.org.de.scotlandyard.exception.CategoryInvalidArgumentException;
import telran.org.de.scotlandyard.exception.CategoryNotFoundException;
import telran.org.de.scotlandyard.repository.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private final CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {
        Category unit = categoryRepository.save(category);
        log.debug("New category was created {}", unit);
        return unit;
    }


    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("No category with id " + categoryId));
        log.debug("Found category with id {}", category);
        return category;
    }

    @Override
    public void delete(Long categoryId) {
//        Category unit = findById(categoryId);
        if (!categoryRepository.existsById(categoryId)){
            throw new CategoryInvalidArgumentException("The category is absent");
        }
        categoryRepository.deleteById(categoryId);

    }
    @Override
    public Category updateCategory(Long categoryId, Category category) {
        Category existingCategory = findById(categoryId);
        existingCategory.setName(category.getName());
        Category updatedCategory = categoryRepository.save(existingCategory);
        log.debug("Updated category with id {}: {}", categoryId, updatedCategory);

        return updatedCategory;
    }
}
