package telran.org.de.scotlandyard.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import telran.org.de.scotlandyard.exception.ProductNotFoundException;
import telran.org.de.scotlandyard.service.ProductService;
import telran.org.de.scotlandyard.converter.ProductConverter;
import telran.org.de.scotlandyard.dto.productdto.ProductCreateDto;
import telran.org.de.scotlandyard.dto.productdto.ProductDto;
import telran.org.de.scotlandyard.entity.Product;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductConverter converter;

    @Operation(summary = "Добавление нового продукта в категорию")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Продукт успешно добавлен"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос")
    })
    @PostMapping
    public ResponseEntity<ProductDto> add(@RequestBody ProductCreateDto productDto) {
        Product product = converter.toEntity(productDto);
        log.debug("Created Product: {}", product);
        Product savedProduct = productService.addProduct(product);
        ProductDto responseDto = converter.toDto(savedProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
    //        Category category = categoryService.findById(Long.valueOf(productDto.getCategory()));
        // Создание и настройка сущности Product
//        Product product = new Product();
//        product.setName(productDto.getName());
//        product.setDescription(productDto.getDescription());
//        product.setPrice(productDto.getPrice());
//        product.setCategory(category);
//        product.setImage(productDto.getImage());
    // Сохранение продукта в базе данных

    @Operation(summary = "Получение списка всех продуктов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список всех продуктов"),
            @ApiResponse(responseCode = "404", description = "Продукты не найдены")
    })
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAll() {
        List<Product> products = productService.getAllProduct();
        List<ProductDto> productDtos = products.stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(productDtos);
    }

    @Operation(summary = "Изменение продукта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Продукт успешно изменен"),
            @ApiResponse(responseCode = "404", description = "Продукт не найден"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductCreateDto productDto) {
        Product product = converter.toEntity(productDto);
        Product updatedProduct = productService.updateProduct(product);
        ProductDto responseDto = converter.toDto(updatedProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
    //        // Получение существующего продукта по ID
//        Product modifiProduct = productService.getById(id);
        // Обновление данных продукта на основе входящих данных
//        modifiProduct.setName(productDto.getName());
//        modifiProduct.setDescription(productDto.getDescription());
//        modifiProduct.setPrice(productDto.getPrice());
//        // Поиск и установка категории по идентификатору из DTO
//        Category category = categoryService.findById(Long.valueOf(productDto.getCategory()));
//        modifiProduct.setCategory(category);
//        modifiProduct.setImage(productDto.getImage());
//        log.debug("Modified product {}", modifiProduct);

    @Operation(summary = "Поиск продуктов по ID категории")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Продукты по категории найдены"),
            @ApiResponse(responseCode = "404", description = "Продукты не найдены для указанной категории")
    })
    @GetMapping("/category")
    public ResponseEntity<List<ProductDto>> findByCategory(@RequestParam Long categoryId) {
        List<Product> products = productService.findByCategoryId(categoryId);
        if (products.isEmpty()) {
            throw new ProductNotFoundException("Продукты для категории с ID " + categoryId + " не найдены");
        }
        List<ProductDto> productDtos = products.stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(productDtos);
    }

    @Operation(summary = "Удаление продукта по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Продукт успешно удален"),
            @ApiResponse(responseCode = "404", description = "Продукт не найден")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Получение продукта по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Продукт найден"),
            @ApiResponse(responseCode = "404", description = "Продукт не найден")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable Long id) {
        Product product = productService.getById(id);
        ProductDto responseDto = converter.toDto(product);
        return ResponseEntity.ok(responseDto);
    }
}

