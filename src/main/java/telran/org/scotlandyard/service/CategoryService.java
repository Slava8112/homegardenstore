package telran.org.scotlandyard.service;

import telran.org.scotlandyard.entity.Category;

import java.util.List;

public interface CategoryService {

    Category createCategory(Category category);

    Category findById(String categeryId);

    List<Category> getAll();

    void delete(String id);

    Category updateCategory(String categoryId, Category category);
}

