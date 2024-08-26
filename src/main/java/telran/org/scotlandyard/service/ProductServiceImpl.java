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
    public Product updateProduct(String productId, Product product) {
        Product unit = repository.save(product);
        return unit;
    }

    @Override
    public void deleteById(String productId) {
        repository.deleteById(productId);
    }

    public List<Product> getAllProduct() {
        return repository.findAll();
    }

    @Override
    public Product getById(String productId) {
        Product product = repository.findById(productId).get();
        return product;
    }

    @Override
    public List<Product> findAllByCategoryId(String categoryId) {
        return repository.findAllByCategoryId(categoryId);
    }
//@Override
//public List<Product> getAllDiscountprice(){
//        return repository.getAllDiscountprice();
//}


}