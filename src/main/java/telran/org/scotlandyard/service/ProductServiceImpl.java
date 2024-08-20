package telran.org.scotlandyard.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import telran.org.scotlandyard.entity.Category;
import telran.org.scotlandyard.entity.Product;
import telran.org.scotlandyard.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    public final ProductRepository repository;
    public final CategoryService categoryService;

    @Override
    public Product addProduct(Long categoryId, Product product) {
        Category category = categoryService.findById(categoryId);
        product.setCategory(category);
        Product unit = repository.save(product);
        log.debug("The product with id {} was edded prouct {}",product.getId(), product);
        return unit;
    }

    @Override
    public void deleteById(Long productId) {
        repository.deleteById(productId);
    }

    public List<Product> getAllProduct() {
        return repository.findAll();
    }

    @Override
    public Product getById(Long productId) {
        Optional<Product> product = repository.findById(productId);
        return null;
    }

    @Override
    public Product findByCategoryId(Long categoryId) {
        return repository.findByCategoryId(categoryId);
    }

}