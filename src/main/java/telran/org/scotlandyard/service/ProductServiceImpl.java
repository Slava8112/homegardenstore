package telran.org.scotlandyard.service;
import org.springframework.stereotype.Service;
import telran.org.scotlandyard.entity.Product;
import telran.org.scotlandyard.exception.ProductNotFoundException;
import telran.org.scotlandyard.repository.ProductRepository;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + productId + " not found"));
        return productRepository.save(product);
    }

    @Override
    public void deleteById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + productId + " not found"));
        productRepository.delete(product);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + productId + " not found"));
    }

    @Override
    public List<Product> findAllByCategoryId(Long categoryId) {
        // Логика поиска по категории
        return productRepository.findByCategoryId(Long.valueOf(String.valueOf(categoryId)));
    }
}
