package telran.org.scotlandyard.service;

import telran.org.scotlandyard.entity.Product;

import java.util.List;

public interface ProductService {

    Product addProduct(Long categoryId, Product product);

    Product updateProduct(Long productId,Long categoryId, Product product);

    void deleteById(Long productId);

    List<Product> getAllProduct();

    Product getById(Long productId);

    //получение списка по запросу `category`, `minPrice`,
// `maxPrice`, `discount`, `sort`

    List<Product> findAllByCategoryId(Long categoryId);
 //       List<Product> getAllDiscountprice();
//
//    Product getPoductByminPrice();
//
//    Product getmaxPrice();
}

    List<Product> findByCategoryId(Long categoryId);

}

