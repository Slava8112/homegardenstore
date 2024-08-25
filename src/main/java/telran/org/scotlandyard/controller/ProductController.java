package telran.org.scotlandyard.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import telran.org.scotlandyard.dto.productdto.ProductCreateDto;
import telran.org.scotlandyard.dto.productdto.ProductDto;
import telran.org.scotlandyard.entity.Product;
import telran.org.scotlandyard.service.ProductService;
import telran.org.scotlandyard.service.converter.Converter;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final Converter<Product, ProductDto, ProductCreateDto> converter;

    @PostMapping
    public Product add(@RequestParam @RequestBody ProductCreateDto productDto) {
        Product product = converter.toEntity(productDto);
        ProductDto newProduct = converter.toDto(productService.addProduct(product));
        return productService.addProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable String productId, @RequestBody ProductCreateDto productDto) {

        Product modifiProduct = productService.getById(productId);

        Product newProduct = converter.toEntity(productDto);

        newProduct.setName(modifiProduct.getName());
        newProduct.setDescription(modifiProduct.getDescription());
        newProduct.setPrice(modifiProduct.getPrice());
        newProduct.setCategory(modifiProduct.getCategory());
        newProduct.setImage(modifiProduct.getImage());
log.debug("Modified product {}", newProduct);
        Product product = productService.updateProduct(productId, newProduct);

        return product;
    }

    @GetMapping
    public List<Product> getAll() {
        return productService.getAllProduct();
    }

    @GetMapping("/categoryId")
    public List<Product> findAllByCategoryId(@RequestParam String categoryId) {
        return (List<Product>) productService.findAllByCategoryId(categoryId);
    }

    @DeleteMapping
    public void deleteById(@RequestBody String productId) {
        productService.deleteById(productId);
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable String productid) {
        return productService.getById(productid);
    }

}
