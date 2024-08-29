package telran.org.scotlandyard.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import telran.org.scotlandyard.dto.productdto.ProductCreateDto;
import telran.org.scotlandyard.dto.productdto.ProductDto;
import telran.org.scotlandyard.entity.Category;
import telran.org.scotlandyard.entity.Product;
import telran.org.scotlandyard.service.ProductService;
import telran.org.scotlandyard.service.converter.Converter;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("v1/products")
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final Converter<Product, ProductDto, ProductCreateDto> converter;

    @Operation(summary = "Добавление нового продукта в категорию")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Продукт успешно добавлен"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос")
    })
    @PostMapping
    public ResponseEntity<Product> add(@RequestParam Long category_id, @RequestBody Product product) {
        Product createdProduct = productService.addProduct(category_id, product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
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

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long productId, @RequestParam Long categoriId, @RequestBody ProductCreateDto Dto) {

        Product modifiProduct = productService.getById(productId);
        log.debug("Intro Dto : {}", Dto);
        Product newProduct = converter.toEntity(Dto);
        log.debug("Intro newProduct : {}", newProduct);
        modifiProduct.setName(newProduct.getName());
        modifiProduct.setDescription(newProduct.getDescription());
//        newProduct.setCategoryId(modifiProduct.getCategoryId());
        modifiProduct.setPrice(newProduct.getPrice());
        modifiProduct.setImage(newProduct.getImage());
        log.debug("Modified product {}", newProduct);
        Product product = productService.updateProduct(productId, categoriId, newProduct);
        log.debug("Product Added in SQL  : {} ", product);
        return product;
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

//    @GetMapping("/categoryId")
//    public List<Product> findAllByCategoryId(@RequestParam Long categoryId) {
//        return (List<Product>) productService.findAllByCategoryId(categoryId);
//    }



    @Operation(summary = "Удаление продукта по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Продукт успешно удален"),
            @ApiResponse(responseCode = "404", description = "Продукт не найден")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Получение продукта по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Продукт найден"),
            @ApiResponse(responseCode = "404", description = "Продукт не найден")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        Product product = productService.getById(id);
        return ResponseEntity.ok(product);
    }

//    @GetMapping
//    public List<Product> getAllDiscountprice() {
//        return getAll().stream().filter(entity -> entity.getDiscountprice() > 0)
//                .collect(Collectors.toList());
//    }

//    @GetMapping
//    public List<Product> getMinPrice {
//        return getAll().stream().filter(entity -> entity.getPrice() > 0)
//                .collect(Collectors.toList());
//    }
}
