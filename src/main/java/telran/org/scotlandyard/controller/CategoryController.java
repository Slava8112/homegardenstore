package telran.org.scotlandyard.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telran.org.scotlandyard.entity.Category;
import telran.org.scotlandyard.service.CategoryService;

import java.util.List;

@ApiResponses
@RestController
@RequestMapping("v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    public final CategoryService categoryService;

    @Operation(summary = "Создание новой категории")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Категория успешно создана"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос")
    })

   @PostMapping
    public Category create(@RequestBody Category category){
       return categoryService.createCategory(category);
   }

    @Operation(summary = "Удаление категории по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Категория успешно удалена"),
            @ApiResponse(responseCode = "404", description = "Категория не найдена")
    })
   @DeleteMapping("/{id}")
   public void delete(@PathVariable Long id){
       categoryService.delete(id);
   }

    @Operation(summary = "Получение всех категорий")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список всех категорий"),
            @ApiResponse(responseCode = "404", description = "Категории не найдены")
    })
   @GetMapping
    public List<Category> getAll(){
       return categoryService.getAll();
   }
    @Operation(summary = "Редактирование категории по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Категория успешно обновлена"),
            @ApiResponse(responseCode = "404", description = "Категория не найдена")
    })
    @PutMapping("/{id}")
    public Category update(@PathVariable Long id, @RequestBody Category category) {
        return categoryService.UpdateCategory(id, category); // Изменено: использование UpdateCategory
    }
}
