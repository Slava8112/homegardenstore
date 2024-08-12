package telran.org.scotlandyard.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telran.org.scotlandyard.entity.Category;
import telran.org.scotlandyard.entity.Product;
import telran.org.scotlandyard.exception.ProductNotFoundException;
import telran.org.scotlandyard.repository.CategoryReposit;
import telran.org.scotlandyard.repository.ProductReposit;

import java.util.List;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

public final ProductReposit reposit;
public final CategoryService categoryService;

    @Override
    public Product addProduct(Long categoryId, Product product) {

        Category category = categoryService.findById(categoryId);
        product.setCategory(category);
        Product unit = reposit.save(product);
        log.debug("The product with id {} was edded prouct {}",product.getId(), product);
            return unit;
    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }

    @Override
    public Product delete(Product product) {
        return null;
    }

    public List<Product> getAllProduct() {
        return reposit.findAll();
    }

    @Override
    public List<Product> findByCategoryId(Long categoryId) {
        return (List<Product>) reposit.findByCategoryId(categoryId);
    }

//    @Override
//    public Product getById(Long id) {
//    Product product = reposit.findById(id)
//            .orElseThrow(() -> new ProductNotFoundException("The Product with id  not exist " + id));
//return product;
//}
}