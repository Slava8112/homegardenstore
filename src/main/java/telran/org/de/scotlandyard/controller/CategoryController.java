package telran.org.de.scotlandyard.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import telran.org.de.scotlandyard.converter.Converter;
import telran.org.de.scotlandyard.dto.categorydto.CategoryCreateDto;
import telran.org.de.scotlandyard.dto.categorydto.CategoryDto;
import telran.org.de.scotlandyard.entity.Category;
import telran.org.de.scotlandyard.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@ApiResponses
@Slf4j
@RestController
@RequestMapping("v1/categories")
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
    public ResponseEntity<CategoryCreateDto> create(@RequestBody CategoryCreateDto categoryDto) {
        Category category = converter.toEntity(categoryDto);
        Category savedCategory = categoryService.createCategory(category);
        CategoryDto responseDto = converter.toDto(savedCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryDto);
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
            @ApiResponse(responseCode = "200", description = "Список всех категорий"),
            @ApiResponse(responseCode = "404", description = "Категории не найдены")
    })
    @GetMapping
    public List<CategoryDto> getAll() {
        return categoryService.getAll().stream()
                .map(converter::toDto).collect(Collectors.toList());
    }

    @Operation(summary = "Обновление категории по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Категория успешно обновлена"),
            @ApiResponse(responseCode = "404", description = "Категория не найдена"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос")
    })
    @PutMapping("/{categoryId}")
    public Category updateCategory(@PathVariable Long categoryId, @RequestBody CategoryCreateDto categoryDto) {
        log.debug("Id of modified category: {}", categoryId);
        Category category = converter.toEntity(categoryDto);
        return categoryService.updateCategory(categoryId,category);
    }
}
