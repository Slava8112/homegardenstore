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

    @PostMapping
    public Product add(@RequestParam Long category_id, @RequestBody Product product){
        return productService.addProduct( category_id, product);
    }
@GetMapping
    public List<Product> getAll(){
        return productService.getAllProduct();
}
@GetMapping("/{id}")
    public List<Product> list(@RequestParam Long id){
        return productService.findByCategoryId(id);
}

//@GetMapping("/{id}")
//    public  Product getById(@PathVariable Long id){
//        return productService.getById(id);
//}
}
