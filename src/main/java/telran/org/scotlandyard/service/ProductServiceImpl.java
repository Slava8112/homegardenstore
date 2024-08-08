package telran.org.scotlandyard.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telran.org.scotlandyard.entity.Product;
import telran.org.scotlandyard.repository.ProductReposit;

import java.util.List;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
@Autowired
public ProductReposit reposit;

    @Override
    public Product addProduct(Product product) {
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

    @Override
    public List<Product> getAllProduct() {

        return List.of();
    }

    @Override
    public Product getById(long id) {
        return null;
    }

}
