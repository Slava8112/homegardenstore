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
    public Product addProduct(Product product) {
        Product unit = repository.save(product);
        log.debug("The product  was edded {}", product);
        return unit;
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        Product unit = repository.save(product);
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
        Product product = repository.findById(productId).get();
        return product;
    }

    @Override
    public Product findByCategoryId(Long categoryId) {
        return repository.findByCategoryId(categoryId);
    }

//    @Override
//    public List<Product> findByCategoryId(Long categoryId) {
//         List<Product> productsOfcategory = Arrays.asList(repository.findByCategoryId(categoryId));
//
//         log.debug("Variable categoryId {}", categoryId);
//
//         return  productsOfcategory;
//    }

//    @Override
//    public Product getById(Long productId) {
//    Product product = repository.findById(productId)
//            .orElseThrow(() -> new ProductNotFoundException("The Product with id  not exist " + productId));
//return product;
//}
}