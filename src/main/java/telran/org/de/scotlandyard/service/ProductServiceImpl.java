package telran.org.de.scotlandyard.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import telran.org.de.scotlandyard.entity.Product;
import telran.org.de.scotlandyard.exception.ProductNotFoundException;
import telran.org.de.scotlandyard.repository.ProductRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository repository;
    private final CategoryService categoryService;// пригодится?

    public Product addProduct(Product product) {
//                Category category = categoryService.findById(product.getCategory().getId());
//        product.setCategory(category);
        log.debug("Adding product: {}", product);
        return repository.save(product);
    }


@Override
public Product updateProduct(Product product) {
    log.debug("Product will apdeted : {}", product);
//        if (product.getId() == null) {
//        throw new IllegalArgumentException("Product ID must not be null");
//    }//
    Product existingProduct = repository.findById(product.getId())
            .orElseThrow(() -> new ProductNotFoundException("Product with ID " + product.getId() + " not found"));
    existingProduct.setName(product.getName());

    log.debug("ID of Product  : {}", product.getId());
    log.debug("New name of Product : {}", existingProduct.getName());
    existingProduct.setDescription(product.getDescription());
    log.debug("New description : {}", existingProduct.getDescription());
    existingProduct.setPrice(product.getPrice());
    existingProduct.setDiscountPrice(product.getDiscountPrice());
    existingProduct.setImage(product.getImage());
    existingProduct.setCreatedAt(product.getCreatedAt());
    existingProduct.setUpdatedAt(product.getUpdatedAt());
    log.debug("Product updated : {}", existingProduct);
        existingProduct.setId(product.getId()); // для обновления,надо проверить
    return repository.save(existingProduct);
}

    @Override
    public void deleteById(Long productId) {
        Product product = repository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + " not found"));
        repository.delete(product);
    }

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
