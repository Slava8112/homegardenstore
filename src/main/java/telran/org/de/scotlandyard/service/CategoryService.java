package telran.org.de.scotlandyard.service;

import telran.org.de.scotlandyard.entity.Category;

import java.util.List;

public interface CategoryService {

    Category createCategory(Category category);

    Category findById(Long categoryId);

    List<Category> getAll();

    void delete(Long id);

    Category updateCategory(Long categoryId, Category category);

}

