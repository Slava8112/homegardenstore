package telran.org.scotlandyard.service;

import telran.org.scotlandyard.entity.Product;

import java.util.List;

public interface ProductService {

    Product addProduct(Long categoryId, Product product);


    void deleteById(Long productId);

    List<Product> getAllProduct();

    Product getById(Long productId);

        Product findByCategoryId(Long categoryId);

}
