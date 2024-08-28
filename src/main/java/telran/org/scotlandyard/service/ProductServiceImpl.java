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
    public Product addProduct(
            Long categoryId, Product product) {
        final Product unit = this.repository.save(product);
        ProductServiceImpl.log.debug("The product  was edded {}", product);
        return unit;
    }

    @Override
    public Product updateProduct(Long productId, Long categoryId, Product product) {
       Product unit = this.repository.save(product);
        return unit;
    }

    @Override
    public void deleteById(final Long productId) {
        this.repository.deleteById(productId);
    }

    public List<Product> getAllProduct() {
        return this.repository.findAll();
    }

    @Override
    public Product getById(final Long id) {
        final Product product = (Product) repository.getById(id);
        return product;
    }

    @Override
    public List<Product> findAllByCategoryId(final Long categoryId) {
        return this.repository.findAllByCategoryId(categoryId);
    }
//@Override
//public List<Product> getAllDiscountprice(){
//        return repository.getAllDiscountprice();
//}


}