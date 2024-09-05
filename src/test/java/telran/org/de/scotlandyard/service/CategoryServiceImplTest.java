package telran.org.de.scotlandyard.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import telran.org.de.scotlandyard.controller.CategoryController;
import telran.org.de.scotlandyard.entity.Category;
import telran.org.de.scotlandyard.exception.CategoryInvalidArgumentException;
import telran.org.de.scotlandyard.exception.CategoryNotFoundException;
import telran.org.de.scotlandyard.repository.CategoryRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryController categoryController;

    @Mock
    private CategoryServiceImpl categoryService;

    @Test
    void createCategory_Test() {
        Category category = new Category();
        category.setName("Test Category");

        Category result = categoryService.createCategory(category);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Category", result.getName());

        verify(categoryRepository.save(any(Category.class)));

    }

    @Test
    void getAll_Test() {
        when(categoryRepository.findAll()).thenReturn(Arrays.asList(new Category(), new Category()));
        List<Category> result = categoryService.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(categoryRepository).findAll();
    }

    @Test
    void findById_Test() {
        Long id = 1L;
        Category category = new Category();
        when(categoryRepository.findById(id)).thenReturn(Optional.of(category));
        Category result = categoryService.findById(id);
        assertNotNull(result);
        verify(categoryRepository).findById(id);
    }

    @Test
    void delete_WhenCategoryExist_Test() {
        Long id = 1L;
        when(categoryRepository.existsById(id)).thenReturn(true);
        categoryService.delete(id);
        verify(categoryRepository).deleteById(id);
    }

    @Test
    void delete_WhenCategoryNotExist_Test() {
        Long id = 1L;
        when(categoryRepository.existsById(id)).thenReturn(false);

        assertThrows(CategoryInvalidArgumentException.class, () -> categoryService.delete(id));
        verify(categoryRepository, never()).deleteById(id);
    }

    @Test
    void updateCategory_WhenCategoryNotExist_Test() {
        Long id = 1L;

        when(categoryRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(CategoryNotFoundException.class, () -> categoryService.updateCategory(id, new Category()));
    }

    @Test
    void updateCategory_WhenCategoryExist_Test() {
        Long id = 1L;

        Category category = new Category();
        when(categoryRepository.findById(id)).thenReturn(Optional.of(category));
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        Category result = categoryService.updateCategory(id, category);

        assertNotNull(result);
        assertEquals("Update Name", result.getName());
        verify(categoryRepository).save(category);
        verify(categoryRepository).findById(id);
    }

}