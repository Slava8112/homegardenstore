package telran.org.scotlandyard.service;

import telran.org.scotlandyard.entity.Category;

import java.util.List;

public interface CategoryService {

Category addCategory(Category category);

Category UpdateCategory(Category category);

Category dlete(Category category);

List<Category> getAll();
}
