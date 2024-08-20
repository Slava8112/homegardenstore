package telran.org.scotlandyard.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import telran.org.scotlandyard.entity.Product;
import telran.org.scotlandyard.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Добавление нового продукта в категорию")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Продукт успешно добавлен"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос")
    })
    @PostMapping
    public Product add(@RequestParam Long category_id, @RequestBody Product product) {
        return productService.addProduct(category_id, product);
    }

    @Operation(summary = "Получение списка всех продуктов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список всех продуктов"),
            @ApiResponse(responseCode = "404", description = "Продукты не найдены")
    })
    @GetMapping
    public List<Product> getAll() {
        return productService.getAllProduct();
    }

    @Operation(summary = "Поиск продуктов по ID категории")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Продукты по категории найдены"),
            @ApiResponse(responseCode = "404", description = "Продукты не найдены для указанной категории")
    })
    @GetMapping("/categoryId")
    public List<Product> findByCategory(@RequestParam Long categoryId) {
        return (List<Product>) productService.findByCategoryId(categoryId);
    }

    @Operation(summary = "Удаление продукта по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Продукт успешно удален"),
            @ApiResponse(responseCode = "404", description = "Продукт не найден")
    })
    @DeleteMapping
    public void deleteById(@RequestBody Long productId) {
        productService.deleteById(productId);
    }

    @Operation(summary = "Получение продукта по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Продукт найден"),
            @ApiResponse(responseCode = "404", description = "Продукт не найден")
    })
    @GetMapping("/{id}")
    public Product getById(@PathVariable Long productid) {
        return productService.getById(productid);
    }
}
