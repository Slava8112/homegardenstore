
package telran.org.scotlandyard.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import telran.org.scotlandyard.entity.Category;
import telran.org.scotlandyard.entity.Product;
import telran.org.scotlandyard.exception.ProductNotFoundException;
import telran.org.scotlandyard.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository repository;
    private final CategoryService categoryService;

    @Override
    public Product addProduct(Long categoryId, Product product) {
        Category category = categoryService.findById(categoryId);
        product.setCategory(category);
        log.debug("Adding product: {}", product);
        return repository.save(product);
    }

    @Override
    public void deleteById(Long productId) {
        if (!repository.existsById(productId)) {
            log.error("Product with id {} not found, cannot delete", productId);
            throw new ProductNotFoundException("Product not found with id " + productId);
        }
        repository.deleteById(productId);
        log.debug("Deleted product with id {}", productId);
    }

    @Override
    public List<Product> getAllProduct() {
        log.debug("Fetching all products");
        return repository.findAll();
    }

    @Override
    public Product getById(Long productId) {
        return repository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id " + productId));
    }

    @Override
    public List<Product> findByCategoryId(Long categoryId) {
        log.debug("Fetching products for category with id {}", categoryId);
        return repository.findAllByCategoryId(categoryId);
    }
}



