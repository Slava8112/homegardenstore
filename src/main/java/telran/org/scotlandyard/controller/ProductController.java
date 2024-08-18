package telran.org.scotlandyard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import telran.org.scotlandyard.entity.Product;
import telran.org.scotlandyard.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
//private final CategoryService categoryService;

    @PostMapping
    public Product add(@RequestParam Long category_id, @RequestBody Product product) {
        return productService.addProduct(category_id, product);
    }

    @GetMapping
    public List<Product> getAll() {
        return productService.getAllProduct();
    }

    @GetMapping("/categoryId")
    public List<Product> findByCategory(@RequestParam Long categoryId) {
        return (List<Product>) productService.findByCategoryId(categoryId);
    }

    @DeleteMapping
    public void deleteById(@RequestBody Long productId) {
        productService.deleteById(productId);
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long productid) {
        return productService.getById(productid);
    }
}
