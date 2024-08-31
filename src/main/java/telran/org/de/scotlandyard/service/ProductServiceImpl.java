package telran.org.de.scotlandyard.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import telran.org.de.scotlandyard.entity.Category;
import telran.org.de.scotlandyard.entity.Product;
import telran.org.de.scotlandyard.exception.ProductNotFoundException;
import telran.org.de.scotlandyard.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository repository;
    private final CategoryService categoryService;

    @Override
    public Product addProduct(Product product) {
//        Category category = categoryService.findById(product.getCategory().getId());
//        product.setCategory(category);
//        log.debug("Adding product: {}", product);
        return repository.save(product);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {

        Product existingProduct = repository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + productId + " not found"));
        return repository.save(product);
    }

    @Override
    public void deleteById(Long productId) {

        Product product = repository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + productId + " not found"));
        repository.delete(product);
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

//    @Override
//    public List<Product> findByCategoryId(Long categoryId) {
//         List<Product> productsOfcategory = Arrays.asList(repository.findByCategoryId(categoryId));
//
//         log.debug("Variable categoryId {}", categoryId);
//
//         return  productsOfcategory;
//    }
}

//    @Override
//    public Product getById(Long productId) {
//    Product product = repository.findById(productId)
//            .orElseThrow(() -> new ProductNotFoundException("The Product with id  not exist " + productId));
//return product;
//}