package telran.org.scotlandyard.service;

import telran.org.scotlandyard.entity.Category;

import java.util.List;

public interface CategoryService {

    Category createCategory(Category category);

    Category UpdateCategory(Category category);

    Category findById(Long id);

    List<Category> getAll();

    void delete(Long id);
}
