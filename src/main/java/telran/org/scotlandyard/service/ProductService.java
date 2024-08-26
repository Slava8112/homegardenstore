package telran.org.scotlandyard.service;

import telran.org.scotlandyard.entity.Product;

import java.util.List;

public interface ProductService {

    Product addProduct(Product product);

    Product updateProduct(String productId, Product product);

    void deleteById(String productId);

    List<Product> getAllProduct();

    Product getById(String productId);

    //получение списка по запросу `category`, `minPrice`,
// `maxPrice`, `discount`, `sort`

        List<Product> findAllByCategoryId(String categoryId);
 //       List<Product> getAllDiscountprice();
//
//    Product getPoductByminPrice();
//
//    Product getmaxPrice();
}
