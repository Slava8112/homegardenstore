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
    public Product updateProduct(@PathVariable Long productid, @RequestBody ProductCreateDto productDto) {

        Product modifiProduct = productService.getById(productid);

        Product newProduct = converter.toEntity(productDto);

        newProduct.setName(modifiProduct.getName());
        newProduct.setDescription(modifiProduct.getDescription());
        newProduct.setPrice(modifiProduct.getPrice());
        newProduct.setCategory(modifiProduct.getCategory());
        newProduct.setImage(modifiProduct.getImage());
log.debug("Modified product {}", newProduct);
       // Product product = productService.save(newProduct);


        return newProduct;
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
//@PutMapping("{id}")
//    public ResponseEntity<Employee> updateEmployee(@PathVariable long id,@RequestBody Employee employeeDetails) {
//        Employee updateEmployee = employeeRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));
//
//        updateEmployee.setFirstName(employeeDetails.getFirstName());
//        updateEmployee.setLastName(employeeDetails.getLastName());
//        updateEmployee.setEmailId(employeeDetails.getEmailId());
//
//        employeeRepository.save(updateEmployee);
//
//        return ResponseEntity.ok(updateEmployee);