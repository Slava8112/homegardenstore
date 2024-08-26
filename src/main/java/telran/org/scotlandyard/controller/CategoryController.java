package telran.org.scotlandyard.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import telran.org.scotlandyard.dto.categorydto.CategoryCreateDto;
import telran.org.scotlandyard.dto.categorydto.CategoryDto;
import telran.org.scotlandyard.entity.Category;
import telran.org.scotlandyard.entity.Product;
import telran.org.scotlandyard.service.CategoryService;
import telran.org.scotlandyard.service.converter.Converter;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    public final CategoryService categoryService;
    public final Converter<Category, CategoryDto, CategoryCreateDto> converter;

    @PostMapping
    public Category create(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String categoryId) {
        categoryService.delete(categoryId);
    }

    @GetMapping
    public List<Category> getAll() {
        return categoryService.getAll();
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable String categoryId, @RequestBody CategoryCreateDto categoryDto) {
log.debug(" Id of modified category: {}", categoryId);
        Category modifiCategory = categoryService.findById(categoryId);

        Category newCategory = converter.toEntity(categoryDto);

        newCategory.setId(modifiCategory.getId());
        newCategory.setName(modifiCategory.getName());

        Category category = categoryService.updateCategory(categoryId, newCategory);

        return category;
    }
}