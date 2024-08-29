package telran.org.scotlandyard.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import telran.org.scotlandyard.dto.categorydto.CategoryCreateDto;
import telran.org.scotlandyard.dto.categorydto.CategoryDto;
import telran.org.scotlandyard.entity.Category;
import telran.org.scotlandyard.service.CategoryService;
import telran.org.scotlandyard.service.converter.Converter;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final Converter<Category, CategoryDto, CategoryCreateDto> converter;

    @Operation(summary = "Создание новой категории")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Категория успешно создана"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос")
    })
    @PostMapping
    public Category create(@RequestBody CategoryCreateDto categoryDto) {
        Category category = converter.toEntity(categoryDto);
        return categoryService.createCategory(category);
    }

    @Operation(summary = "Удаление категории по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Категория успешно удалена"),
            @ApiResponse(responseCode = "404", description = "Категория не найдена")
    })
    @DeleteMapping("/{categoryId}")
    public void delete(@PathVariable Long categoryId) {
        categoryService.delete(categoryId);
    }

    @Operation(summary = "Получение всех категорий")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список категорий успешно получен"),
            @ApiResponse(responseCode = "404", description = "Категории не найдены")
    })
    @GetMapping
    public List<Category> getAll() {
        return categoryService.getAll();
    }

    @Operation(summary = "Обновление категории по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Категория успешно обновлена"),
            @ApiResponse(responseCode = "404", description = "Категория не найдена"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос")
    })

    @PutMapping("/{categoryId}")
    public Category updateProduct(@PathVariable Long categoryId, @RequestBody CategoryCreateDto categoryDto) {
        log.debug("Id of modified category: {}", categoryId);
        Category existingCategory = categoryService.findById(categoryId);

        if (existingCategory != null) {
            existingCategory.setName(categoryDto.getName());
            return categoryService.updateCategory(categoryId, existingCategory);
        } else {
            log.error("Category with ID {} not found", categoryId);
            return null;
        }
    }
}
