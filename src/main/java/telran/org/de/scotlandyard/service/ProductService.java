package telran.org.de.scotlandyard.service;

import telran.org.de.scotlandyard.entity.Product;
import java.util.List;

public interface ProductService {
    Product addProduct(Product product);
    Product updateProduct(Long productId, Product product);
    void deleteById(Long productId);
    List<Product> getAllProduct();
    Product getById(Long productId);
    List<Product> findByCategoryId(Long categoryId);
}
