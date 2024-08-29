package telran.org.scotlandyard.service;
import org.springframework.stereotype.Service;
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
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product addProduct(Long categoryId, Product product) {
        Category category = categoryService.findById(categoryId);
        product.setCategory(category);
        log.debug("Adding product: {}", product);
        return repository.save(product);
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long productId, Long categoryId, Product product) {
       Product unit = this.repository.save(product);
        return unit;
    public Product updateProduct(Long productId, Product product) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + productId + " not found"));
        return productRepository.save(product);
    }

    @Override
    public void deleteById(Long productId) {
        if (!repository.existsById(productId)) {
            log.error("Product with id {} not found, cannot delete", productId);
            throw new ProductNotFoundException("Product not found with id " + productId);
        }
        repository.deleteById(productId);
        log.debug("Deleted product with id {}", productId);
    public void deleteById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + productId + " not found"));
        productRepository.delete(product);
    }

    @Override
    public List<Product> getAllProduct() {
        log.debug("Fetching all products");
        return repository.findAll();
        return productRepository.findAll();
    }

    @Override
    public Product getById(Long productId) {
        return repository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id " + productId));
    public Product getById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + productId + " not found"));
    }

    @Override
    public List<Product> findByCategoryId(Long categoryId) {
        log.debug("Fetching products for category with id {}", categoryId);
        return repository.findAllByCategoryId(categoryId);

        public List<Product> findAllByCategoryId(final Long categoryId) {
        return this.repository.findAllByCategoryId(categoryId);
    public List<Product> findAllByCategoryId(Long categoryId) {
        // Логика поиска по категории
        return productRepository.findByCategoryId(Long.valueOf(String.valueOf(categoryId)));
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
}