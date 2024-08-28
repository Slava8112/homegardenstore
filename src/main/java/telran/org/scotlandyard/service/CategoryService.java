package telran.org.scotlandyard.service;

import telran.org.scotlandyard.entity.Category;

import java.util.List;

public interface CategoryService {

    Category createCategory(Category category);

    Category findById(Long categeryId);

    List<Category> getAll();

    void delete(Long categeryId);

    Category updateCategory(Long categoryId, Category category);
}

