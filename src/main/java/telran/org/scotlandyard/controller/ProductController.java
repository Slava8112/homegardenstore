package telran.org.scotlandyard.controller;

import lombok.RequiredArgsConstructor;
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
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final Converter<Product, ProductDto, ProductCreateDto> converter;

    @PostMapping("/add")
    public ProductDto add(@PathVariable Long categoryId, @RequestBody ProductCreateDto Dto) {
        log.debug("ProductCreateDto : {}", Dto);
            Product product = converter.toEntity(Dto);
            return converter.toDto(productService.addProduct(categoryId, product));
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

    @GetMapping
    public List<Product> getAll() {
        return productService.getAllProduct();
    }

    @GetMapping("/categoryId")
    public List<Product> findAllByCategoryId(@RequestParam Long categoryId) {
        return (List<Product>) productService.findAllByCategoryId(categoryId);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@RequestBody Long productId) {
        productService.deleteById(productId);
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long productid) {
        return productService.getById(productid);
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
