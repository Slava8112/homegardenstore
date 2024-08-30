package telran.org.de.scotlandyard.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import telran.org.de.scotlandyard.service.ProductService;
import telran.org.de.scotlandyard.converter.Converter;
import telran.org.de.scotlandyard.dto.productdto.ProductCreateDto;
import telran.org.de.scotlandyard.dto.productdto.ProductDto;
import telran.org.de.scotlandyard.entity.Product;
import telran.org.de.scotlandyard.service.CategoryService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService; // для работы с категориями
    private final Converter<Product, ProductDto, ProductCreateDto> converter;

    @Operation(summary = "Добавление нового продукта в категорию")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Продукт успешно добавлен"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос")
    })
    @PostMapping
    public Product add(@RequestBody ProductCreateDto productDto) {
        // Поиск категории по идентификатору из DTO
//        Category category = categoryService.findById(Long.valueOf(productDto.getCategory()));
//        // Создание и настройка сущности Product
//        Product product = new Product();
//        product.setName(productDto.getName());
//        product.setDescription(productDto.getDescription());
//        product.setPrice(productDto.getPrice());
//        product.setCategory(category);
//        product.setImage(productDto.getImage());
        Product product = converter.toEntity(productDto);
        log.debug("Created Product: {}", product);

        // Сохранение продукта в базе данных
        return productService.addProduct(product);
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

    @Operation(summary = "Изменение продукта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Продукт успешно изменен"),
            @ApiResponse(responseCode = "404", description = "Продукт не найден"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос")
    })
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody ProductCreateDto productDto) {
//        // Получение существующего продукта по ID
//        Product modifiProduct = productService.getById(id);
//        // Обновление данных продукта на основе входящих данных
//        modifiProduct.setName(productDto.getName());
//        modifiProduct.setDescription(productDto.getDescription());
//        modifiProduct.setPrice(productDto.getPrice());
//        // Поиск и установка категории по идентификатору из DTO
//        Category category = categoryService.findById(Long.valueOf(productDto.getCategory()));
//        modifiProduct.setCategory(category);
//        modifiProduct.setImage(productDto.getImage());
//        log.debug("Modified product {}", modifiProduct);
        // Сохранение обновленного продукта в базе данных
        Product product = converter.toEntity(productDto);
        return productService.updateProduct(id, product);
    }

    @Operation(summary = "Поиск продуктов по ID категории")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Продукты по категории найдены"),
            @ApiResponse(responseCode = "404", description = "Продукты не найдены для указанной категории")
    })
    @GetMapping("/category")
    public ResponseEntity<List<Product>> findByCategory(@RequestParam Long categoryId) {
        List<Product> products = productService.findByCategoryId(categoryId);
        return ResponseEntity.ok(products);
    }

    @Operation(summary = "Удаление продукта по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Продукт успешно удален"),
            @ApiResponse(responseCode = "404", description = "Продукт не найден")
    })
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @Operation(summary = "Получение продукта по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Продукт найден"),
            @ApiResponse(responseCode = "404", description = "Продукт не найден")
    })
    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {

        return productService.getById(id);
    }
}