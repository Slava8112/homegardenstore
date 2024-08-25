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
        log.debug("New category was created {}", unit);
        return unit;
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(String categeryId) {
        Category category = categoryRepository.findById(categeryId)
                .orElseThrow(() -> new CategoryNotFoundException("No category with id " + categeryId));
        log.debug("Found category with id {}", category);
        return category;
    }

    @Override
    public void delete(String categeryId) {
        Category unit = findById(categeryId);
        categoryRepository.delete(unit);

    }

    @Override
    public Category updateCategory(String categoryId, Category category) {
        return category;
    }
}
